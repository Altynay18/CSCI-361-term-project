package com.example.demo.controllers;

import java.security.Timestamp;

import java.sql.Date;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.data.Booking;
import com.example.demo.data.BookingRepository;
import com.example.demo.data.Guest;
import com.example.demo.data.GuestRepository;
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
	@RequestMapping(path="/bookingform")
	    public String bookingform(
	    		//@PathVariable(value = "city", required=false) String city,
	    	    // @PathVariable(value = "country", required = false) String country,
//	    		  @RequestParam(value = "id", required = true) int id,
//	    		  @PathVariable(value = "roomtype", required = false) String roomType,
	    		 // @PathVariable(value = "capacity", required = false) String capacity,
//	    		@RequestParam(value = "from", required=false) Date from,
//	    		@RequestParam(value = "to", required = false) Date to,
//	    		@RequestParam(value = "hotelid", required = false)Integer hotelid,
//	    		@RequestParam(value = "roomid", required = false) Integer roomid,
//	    		@RequestParam(value = "roomtypeid", required = false) String roomtypeid,
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
	    	
	    	
		//Authentication loggedInUser = SecurityConfigure.getContext().getAuthentication();

	    	Booking book = new Booking();

////	    	Guest user = guestRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).get();
//	    	org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    	String currentPrincipalName = authentication.getName();
//	    	Guest user = guestRepository.findByEmail("").get();
//	    	book.setGuest(user);
	    	
	    	book.setBookingId(1000);
	    	long millis=System.currentTimeMillis();  
	    	java.sql.Date date=new java.sql.Date(millis); 
	    	book.setBookingDate(date);
	    	book.setFromDate(from);
	    	book.setToDate(to);
	    	book.setBill(bill);
	
	    	RoomId guestroom = new RoomId();
	    	guestroom.setRoom_number(roomnumber);
	    	RoomTypeId roomtypeid = new RoomTypeId();
	    	roomtypeid.setHotel_id(hotelid);
	    	roomtypeid.setRoom_type_name(roomtypename);
	    	guestroom.setRoom_type_id(roomtypeid);
	    	book.setRoom(roomrepository.findById(guestroom).get());
	    	
	    	
	    	//	    	Room room = new Room();


	    	
//	    	room_id.setRoom_number(roomid); 
//	    	Integer roomnumber = roomRepository.findById(roomid).get().getRoom_id().getRoom_number();   	
//	    	room_id.setRoom_number(roomid);
	    	
//	    	RoomType roomtype = roomTypeRepository.findById(roomid).get();

//	    	TreeSet<Integer> hotelId = hotelRepository.findHotelIdByCountryAndCity(country, city);
//	    	Integer hotelid = hotelId.first();
//	    	TreeSet<String> roomtype = roomTypeRepository.findRoomTypeByHotelId(hotelId);
//	    	
//	    	
//	  
	 
	    	model.addAttribute("booking", book);
	    	return "bookingform";
	    	}
	
	  @RequestMapping("bookingform/success")
	  public String saveProduct(@ModelAttribute("booking") Booking booking) {

				Booking book = new Booking();
				book.setBookingDate(booking.getBookingDate());
				book.setBill(booking.getBill());
				book.setBookingId(booking.getBookingId());
				book.setFromDate(booking.getFromDate());
				book.setToDate(booking.getToDate());
			
				RoomId rid = new RoomId();
				rid.setRoom_number(booking.getRoom().getRoom_id().getRoom_number());
				RoomTypeId rt = new RoomTypeId();
				rt.setHotel_id(booking.getRoom().getRoom_id().getRoom_type_id().getHotel_id());
				rt.setRoom_type_name(booking.getRoom().getRoom_id().getRoom_type_id().getRoom_type_name());
				rid.setRoom_type_id(rt);
				book.setRoom(roomrepository.findById(rid).get());
				
				//TODO: add guest info (need only id)
				
				bookingRepository.save(book);		
		  
		    return "home";
		}

}
