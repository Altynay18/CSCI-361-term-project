package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.data.Hotel;
import com.example.demo.data.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class MainController {
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Hotel> hotels=hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "home";
    }
	
	  @RequestMapping("/mainpage")
    public String showmainpage(Model model) {
        model.addAttribute("title", "Main page");
        return "mainpage";
    }

	@RequestMapping(path = "/search")
	public String search(Model model) {
		model.addAttribute("title", "Search Service");
		return "search";
	}
	
	@RequestMapping(path = "/bookingsearch")
	public String bookingsearch(Model model) {
		model.addAttribute("title", "BookingSearch Service");
		return "bookingsearch";
	}

/*
    @RequestMapping(path="/registration")
    public String registration(Model model) {
        model.addAttribute("title", "Register Page");
        return "registration";
    }

    @RequestMapping(path="/login")
    public String login(Model model) {
        model.addAttribute("title", "Login Page");
        return "login";
    }
	
	 @RequestMapping(path="/userlogin")
   	 public String userlogin(Model model) {
    	    model.addAttribute("title", "User Login Page");
    	    return "userlogin";
    }*/


    @RequestMapping(path="/profile")
    public String profile(Model model) {
        model.addAttribute("title", "Profile");
        return "profile";
    }

  @RequestMapping("/bookingform")
  public String bookingformpage() {
      return "bookingform";
  }
  @RequestMapping("/bookingform/sucess")
  public String bookcomplete() {
      return "successbook";
  }



}
