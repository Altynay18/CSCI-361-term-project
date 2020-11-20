package com.example.demo.controllers;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.data.Booking;
import com.example.demo.data.BookingRepository;
import com.example.demo.data.Hotel;
import com.example.demo.data.Room;
import com.example.demo.data.RoomRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path="/bookingsearch") // This means URL's start with /search (after Application path)
public class BookingSearchController {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private RoomRepository roomRepository;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Booking> getAllUsers() {
		// This returns a JSON or XML with the users
		System.out.println("All");
		return bookingRepository.findBookings();
	}
	
	@GetMapping(path="/room")
	public @ResponseBody Iterable<Room> getRooms() {
		// This returns a JSON or XML with the users
		return roomRepository.findAll();
	}
	
	@GetMapping(path="/booking")
	public @ResponseBody Iterable<Booking> getBookings(@RequestParam(value = "bookingid", required=false) 
	String bookingid, @RequestParam(value = "guestid", required=false) String guestid, 
	@RequestParam(value = "email", required=false) String email) {
		Integer bid = null;
		Integer gid = null;
		if (bookingid != null) {
			bid = Integer.parseInt(bookingid);
		}
		if (guestid != null) {
			gid = Integer.parseInt(guestid);
		}
		return bookingRepository.findSpecificBooking(bid, gid, email);
	}
	
	@RequestMapping("/delete/{id}")
	public RedirectView deleteBooking(@PathVariable("id") Integer id) {
		
		Booking booking =bookingRepository.findById(id).get();
		booking.getRoom().getBookings().remove(booking);
		bookingRepository.deleteById(id);
		return new RedirectView("/bookingsearch");
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditBookingPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("bookingupdate");
	    Optional<Booking> booking = bookingRepository.findById(id);
	    mav.addObject("booking", booking);
	     
	    return mav;
	}
	@RequestMapping("/save")
	public RedirectView saveProduct(@ModelAttribute("booking") Booking booking) {
		if (booking.getBookingId() != null) {
			Booking book = bookingRepository.findById(booking.getBookingId()).get();
			Room room =  roomRepository.findById(book.getRoom().getRoom_id()).get();

				
				book.setBookingDate(booking.getBookingDate());
				book.setBill(booking.getBill());
				book.setBookingId(booking.getBookingId());
				book.setFromDate(booking.getFromDate());
				book.setToDate(booking.getToDate());
				book.setGuest(booking.getGuest());
				
				book.setPeriod(booking.getPeriod());
				
				
				room.setRoom_id(room.getRoom_id());
				room.setBookings(room.getBookings());
				book.setRoom(room);
				
			
				roomRepository.save(room);
				
				bookingRepository.save(book);
			
		}
	
			  
				
//			
			
//			book.getRoom().addBooking(book);
			
//			roomRepository.save(book.getRoom());

//			room.addBooking(book);

//		bookingRepository.save(booking);
	    return new RedirectView("/bookingsearch");
	}
}
