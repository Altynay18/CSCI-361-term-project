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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(path="/findhotels")
	public @ResponseBody Iterable<Hotel> getHotels(
			@RequestParam(value = "name", required=false) String name, 
			@RequestParam(value = "hotelid", required=false) String hotelid){
		String bid = null;
		if (name != null) {
			bid = name;
		}
		Integer gid = null;

		if (hotelid != null) {
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
		
//		if (seas.getHotels() != null) {
//			seas.getHotels().forEach(hotel-> {hotel.addSeasons(seas);});
			seas.addHotel(hotel1);
//			System.out.printf(seas.getHotels()+"\n");
//		} else {
//			seas.addHotel(hotel1);
////			System.out.printf(seas.getHotels()+"");
//		}
//		if(hotel1.getSeasons() != null) {
			hotel1.addSeasons(seas);
//		hotel1.getSeasons().forEach(season -> {season.addHotel(hotel1);});
//		System.out.printf(hotel1.getSeasons()+"\n");
//		} else {
//			hotel1.addSeasons(seas);
////			System.out.printf(hotel1.getSeasons()+"");
//		}
//		System.out.printf(seas.getSeasonName()+"\n");
//		seas.setSeasonName(seas.getSeasonName());
		seasonRepository.save(seas);
		hotelRepository.save(hotel1);
		
		

	

//
//		seasonRepository.save(seas);
		return new RedirectView("/seasonsearch/list");
	}
	
	@RequestMapping("/addhotels/{seasonName}")
	public String addHotels(@PathVariable("seasonName") String seasonName, @ModelAttribute("season") Season season, Model model) { 
		model.addAttribute("season", seasonRepository.findById(seasonName).get());
	    Iterable<Hotel> listHotels = hotelRepository.findAll();
	    model.addAttribute("listHotels", listHotels);
		
//		Season seas = seasonRepository.findById(seasonName).get();
//		for (Hotel hotel : hotels) {
//		
//			seas.addHotel(hotel);
//			
//		}
//		seasonRepository.save(seas);
//		if (seas.getHotels() != null) {
//			seas.getHotels().forEach(hotel-> {hotel.getSeasons().add(seas);});
//		}
	
		return "newhotels";
	}
	


}
