package com.example.demo.controllers;

import java.security.Principal;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.data.Guest;
import com.example.demo.data.GuestRepository;

@Controller
@RequestMapping(path="/profile")
public class ProfileController {
	
	@Autowired
	private GuestRepository guestRepository;
	
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Guest> currentUserName(Principal principal) {
    	String email = principal.getName();
    	return guestRepository.findByEmail(email);
    }
}
