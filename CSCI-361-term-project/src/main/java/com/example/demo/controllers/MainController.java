package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main Page");
        return "home";
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
    }


    @RequestMapping(path="/profile")
    public String profile(Model model) {
        model.addAttribute("title", "Profile");
        return "profile";
    }
    
    @RequestMapping(path="/seasonsearch")
    public String seasonseacrh(Model model) {
        return "seasonsearch";
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
