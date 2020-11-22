package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.transaction.Transactional;

import org.hibernate.annotations.common.util.impl.Log;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.data.Booking;
import com.example.demo.data.Employee;
import com.example.demo.data.EmployeeRepository;
import com.example.demo.data.Guest;
import com.example.demo.data.GuestRepository;
import com.example.demo.data.Hotel;
import com.example.demo.data.HotelRepository;
import com.example.demo.data.Season;
import com.example.demo.data.SeasonRepository;
import com.example.demo.service.EmailService;

import antlr.collections.List;


@Controller // This means that this class is a Controller
@RequestMapping(path="/seasonsearch")
public class SeasonController {
//	@Autowired
//	private BookingRepository bookingRepository;
//	
	@Autowired
	private SeasonRepository seasonRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private GuestRepository guestRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
    private EmailService emailService;


	


	@GetMapping("/all")
	public @ResponseBody Iterable<Season> getAllSeasons() {
		// This returns a JSON or XML with the users
		return seasonRepository.findAll();
	}
	
	@GetMapping(path="/findhotels")
	public @ResponseBody Iterable<Hotel> getHotels(
			@RequestParam(value = "name", required=false) String name, 
			@RequestParam(value = "hotelid", required=false) String hotelid){
		String bid = null;
		if (name != null) {
			bid = name;
		}
		Integer gid = null;
		if (name != null) {
			gid = Integer.parseInt(hotelid);
		}
		return hotelRepository.findSpecificHotel(bid, gid);
	}
	
	@GetMapping("/hotels")
	public @ResponseBody Iterable<Hotel> getAllHotels() {
		// This returns a JSON or XML with the users
		return hotelRepository.findAll();
	}
	
	
	@RequestMapping("/list")
	public String viewHomePage(Model model) {
		Iterable<Season> listSeasons = seasonRepository.findAll();
	    model.addAttribute("listSeasons", listSeasons);
	    return "seasonsearch";
	}
	@RequestMapping("/delete/{seasonName}")
	public RedirectView deleteBooking(@PathVariable("seasonName") String seasonName) {
		Season season = seasonRepository.findById(seasonName).get();
		if (season!= null) {
			season.getHotels().forEach(hotel-> {hotel.getSeasons().remove(season);});
		}
			seasonRepository.deleteById(seasonName);

			

			return new RedirectView("/seasonsearch/list");
				
		 

	}
	

	
	@RequestMapping("/new")
	public String showNewSeasonPage(Model model) {
	    model.addAttribute("season", new Season());
	    Iterable<Hotel> listHotels = hotelRepository.findAll();
	    model.addAttribute("listHotels", listHotels);
	    return "newseason";
	}
	
	@RequestMapping("/emailsend/{seasonName}")
	public RedirectView sendEmail(@PathVariable("seasonName") String seasonName) {
		Season season = seasonRepository.findById(seasonName).get();
		SimpleMailMessage msg = new SimpleMailMessage();
        Iterable<Guest> guests = guestRepository.findAll();
        Iterable<Employee> employee = employeeRepository.findAll();
        msg.setSubject(season.getSeasonName() + " season has new hotels now");
        msg.setText("There were updates in our season " + season.getSeasonName()+ "! \n"
        		+ "Season is continued from " + season.getStartDate() + " till " + season.getEndDate() + "\n" 
        		+ " It's rate is very facinating, it's only " + season.getRate() + "!\n"
        		+ " This season is available in hotels below " + season.getHotels().toString()
        		+ " Visit the Website and learn new prices\n");

        for(Guest guest: guests) {
            msg.setTo(guest.getEmail());
            emailService.sendEmail(msg);
          }
        
        for(Employee e : employee) {
        	msg.setTo(e.getEmail());
            emailService.sendEmail(msg);
        }
	 return new RedirectView("/seasonsearch/list");
	}
	
	@RequestMapping("/save")
	public RedirectView saveSeason(@ModelAttribute("season") Season season, @ModelAttribute("listHotels") ArrayList<Hotel> hotels) { 
		
		Season seas = new Season();
		seas.setSeasonName(season.getSeasonName());
		seas.setStartDate(season.getStartDate());
		seas.setEndDate(season.getEndDate());
		seas.setRate(season.getRate());
		for (Hotel hotel : hotels) {
		
			seas.addHotel(hotel);
			
		}

		if (seas.getHotels() != null) {
			seas.getHotels().forEach(hotel-> {hotel.getSeasons().add(season);});
		}
	
		 return new RedirectView("/seasonsearch/list");
	}
	
	@RequestMapping("/savehotel/{hotelid}/{seasonName}")
	public RedirectView addHotels(@PathVariable("hotelid") Integer hotelid, @PathVariable("seasonName") String seasonName,  Model model) { 
		
		Season seas = seasonRepository.findById(seasonName).get();
		
		Hotel hotel1 = hotelRepository.findById(hotelid).get();
		
		for  ( Season season : hotel1.getSeasons()) {
			if (season.getEndDate().compareTo(seas.getStartDate())>0 && season.getStartDate().compareTo(seas.getEndDate()) <0) {
				
			}
		}
		
		seas.addHotel(hotel1);
		
		seasonRepository.save(seas);
		hotelRepository.save(hotel1);
		
		return new RedirectView("/seasonsearch/addhotels/" + seasonName);
	}
	
	@RequestMapping("/addhotels/{seasonName}")
	public String addHotels(@PathVariable("seasonName") String seasonName, @ModelAttribute("season") Season season, Model model) { 
		
		model.addAttribute("season", seasonRepository.findById(seasonName).get());
		
		Season season1 = seasonRepository.findById(seasonName).get();
		
	    model.addAttribute("listHotels",  season1.getHotels());
	    
		return "newhotels";
	}
	


}
