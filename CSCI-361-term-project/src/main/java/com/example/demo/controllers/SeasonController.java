package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.data.Booking;
import com.example.demo.data.Hotel;
import com.example.demo.data.HotelRepository;
import com.example.demo.data.Season;
import com.example.demo.data.SeasonRepository;

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


	


	@GetMapping("/all")
	public @ResponseBody Iterable<Season> getAllSeasons() {
		// This returns a JSON or XML with the users
		return seasonRepository.findAll();
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
		

		seasonRepository.save(seas);
		if (seas.getHotels() != null) {
			seas.getHotels().forEach(hotel-> {hotel.getSeasons().add(season);});
		}
	
		 return new RedirectView("/seasonsearch/list");
	}
	
	@RequestMapping("/addhotels/{seasonName}")
	public RedirectView addHotels(@PathVariable("seasonName") String seasonName, @ModelAttribute("listHotels") ArrayList<Hotel> hotels) { 
		
		Season seas = seasonRepository.findById(seasonName).get();
		for (Hotel hotel : hotels) {
		
			seas.addHotel(hotel);
			
		}
		seasonRepository.save(seas);
		if (seas.getHotels() != null) {
			seas.getHotels().forEach(hotel-> {hotel.getSeasons().add(seas);});
		}
	
		 return new RedirectView("/seasonsearch/list");
	}
	


}
