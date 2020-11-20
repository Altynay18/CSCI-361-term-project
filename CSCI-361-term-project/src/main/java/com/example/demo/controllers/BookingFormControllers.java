package com.example.demo.controllers;

import java.security.Timestamp;

import java.sql.Date;
import java.time.Period;
import java.util.Locale.Category;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.data.Booking;
import com.example.demo.data.BookingRepository;
import com.example.demo.data.CategoryRepository;
import com.example.demo.data.Guest;
import com.example.demo.data.GuestRepository;
import com.example.demo.data.RoleRepository;
import com.example.demo.data.Room;
import com.example.demo.data.Room.RoomId;
import com.example.demo.data.RoomRepository;
import com.example.demo.data.RoomType;
import com.example.demo.data.RoomType.RoomTypeId;
import com.example.demo.data.RoomTypeRepository;
@Controller

public class BookingFormControllers {
	@Autowired
	GuestRepository guestRepository;
	@Autowired
	RoomTypeRepository roomTyperepository;
	@Autowired
	RoomRepository roomrepository;
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	RoleRepository roleRepository;
	@RequestMapping(path="/bookingform")
	public String bookingform(
			@RequestParam(value = "from-date", required=false) Date from,
			@RequestParam(value = "to-date", required = false) Date to,
			@RequestParam(value = "hotel_id", required = false)Integer hotelid,
			@RequestParam(value = "room_number", required = false) Integer roomnumber,
			@RequestParam(value = "room_type", required = false) String roomtypename,
			@RequestParam(value = "bill", required = false) Integer bill,
			@RequestParam(value = "capacity", required = false) Integer capacity,
			@RequestParam(value = "roomid", required = false) RoomId roomid,
			Model model

			) {

		Booking book = new Booking();
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.isAuthenticated()) {
			org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			Guest user = guestRepository.findByEmail(currentPrincipalName).get();
			book.setGuest(user);
		}

		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis); 
		book.setBookingDate(date);
		book.setFromDate(from);
		book.setToDate(to);
		Period period = Period.between(from.toLocalDate(),to.toLocalDate());
		bill = bill*period.getDays();
		book.setBill(bill);
		RoomId guestroom = new RoomId();
		guestroom.setRoom_number(roomnumber);
		RoomTypeId roomtypeid = new RoomTypeId();
		roomtypeid.setHotel_id(hotelid);
		roomtypeid.setRoom_type_name(roomtypename);
		guestroom.setRoom_type_id(roomtypeid);
		book.setRoom(roomrepository.findById(guestroom).get());
		model.addAttribute("booking", book);
		return "bookingform";
	}


	@RequestMapping(path ="bookingform/success")
	public String redirectWithUsingRedirectView(@ModelAttribute("booking") Booking booking) {

		Booking book = new Booking();
		book.setBookingDate(booking.getBookingDate());
		book.setBill(booking.getBill());
		book.setBookingId(booking.getBookingId());
		book.setFromDate(booking.getFromDate());
		book.setToDate(booking.getToDate());
	
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.isAuthenticated()) {
			book.setGuest(booking.getGuest());
		}else {
		String guestmail = booking.getGuest().getEmail();
		if(guestRepository.findByEmail(guestmail).isEmpty()) {
			com.example.demo.data.Category basic = categoryRepository.findByCategory("basic");
			booking.getGuest().setCategory(basic);
			booking.getGuest().setRole(roleRepository.findByRoleName("guest"));
			guestRepository.save(booking.getGuest());
		}else {
			booking.setGuest(guestRepository.findByEmail(guestmail).get());
		}
		}


		Period period = Period.between(booking.getFromDate().toLocalDate(),booking.getToDate().toLocalDate());
		booking.setPeriod(period.getDays());
		book.setGuest(booking.getGuest());
		book.setPeriod(8);
		book.setRoom(booking.getRoom());

		bookingRepository.save(book);		

		return "redirect:/ ";
	}

}
