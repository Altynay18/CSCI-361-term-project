package com.example.demo.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.data.Booking;
import com.example.demo.data.BookingRepository;
import com.example.demo.data.Employee;
import com.example.demo.data.Guest;
import com.example.demo.data.GuestRepository;

@Controller
@RequestMapping(path="/profile")
public class ProfileController {
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Guest> currentUserName(Principal principal) {
    	String email = principal.getName();
    	return guestRepository.findByEmail(email);
    }
   
    
    @PostMapping("{gid}/remove/{bid}")
    public String bookingDelete(@PathVariable(value="gid") int gid, 
    		@PathVariable(value="bid") int bid, Model model){
        Optional<Booking> booking=bookingRepository.findById(bid);
        bookingRepository.delete(booking.get());
        return "redirect:/profile/{gid}";
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public String myBookings(@PathVariable("id") int id, Model model){
        Iterable<Booking> booking=bookingRepository.findByGuestId(id);
        model.addAttribute("mybooking", booking);
        return "my-bookings";
    }
    
}
