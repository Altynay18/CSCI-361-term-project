package com.example.demo.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.data.Booking;
import com.example.demo.data.Dayoff;
import com.example.demo.data.Dayoff.DayoffId;
import com.example.demo.data.Room.RoomId;
import com.example.demo.data.DayoffRepository;
import com.example.demo.data.Employee;
import com.example.demo.data.EmployeeRepository;
import com.example.demo.data.Hotel;
import com.example.demo.data.HotelRepository;
import com.example.demo.data.Room;
import com.example.demo.data.Schedule;
import com.example.demo.data.ScheduleRepository;

@Controller
@RequestMapping(path = "/manager")
public class ManagerController {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private DayoffRepository dayoffRepository;
	
	public List<String> list = Arrays.asList("M-Monday", "T-Tuesday", "W-Wednesday", 
			"R-Thursday", "F-Friday", "D-Saturday", "S-Sunday");
	
	@GetMapping(path="/all")
	  public @ResponseBody Iterable<Schedule> getAllHotels() {
	    return scheduleRepository.findAll();
	  }
	
	
	@GetMapping("/search")
	public String showAll(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "hotel", required = false) String hotel, Model model) {
		model.addAttribute("hotel", hotelRepository.findNamesOnly());
		model.addAttribute("type", scheduleRepository.findTypesOnly());
		model.addAttribute("employees", scheduleRepository.findEmployeeList(id, name, surname, type, hotel));
		return "schedule";
	}
	
	@RequestMapping(value = "/dayoff", method = RequestMethod.POST)
	@ResponseBody
	public RedirectView setDayoff(@RequestParam("id") Integer id, 
			@RequestParam("day") String day, RedirectAttributes redirAttrs){
		if(day.isEmpty()) {		
			redirAttrs.addFlashAttribute("message2", "Please, fill the date"); 
		} else {
			Date date=Date.valueOf(day);
	        Dayoff d = new Dayoff();
	        DayoffId did = new DayoffId();
	        did.setEmployee_id(id);
	        did.setDay(date);
	        d.setDayoff_id(did);
	        Optional<Employee> e = scheduleRepository.findEmployee(id);
	        d.setEmployee(e.get());
	        dayoffRepository.save(d);
	        redirAttrs.addFlashAttribute("message2", "Saved!"); 
		}
		return new RedirectView("/manager/edit/" + id);
	    }
	
	@RequestMapping("/delete")
	public RedirectView deleteBooking(@RequestParam("id") Integer id, 
			@RequestParam("day") String day) {
		Date date=Date.valueOf(day);
		Dayoff d = new Dayoff();
        DayoffId did = new DayoffId();
        did.setEmployee_id(id);
        did.setDay(date);
        d.setDayoff_id(did);
        Optional<Employee> e = scheduleRepository.findEmployee(id);
        d.setEmployee(e.get());
        dayoffRepository.delete(d);
		return new RedirectView("/manager/edit/" + id);
	}
	
	@RequestMapping(value = "/salary")
	public RedirectView setSalary(RedirectAttributes redirAttrs, 
			@RequestParam("fromS") String from, 
			@RequestParam("toS") String to,
			@RequestParam("id") Integer id){
		
		if(from.isEmpty() || to.isEmpty()) {
			redirAttrs.addFlashAttribute("message3", "Please, fill the optional dates"); 
		} else {
			LocalDate f=LocalDate.parse(from);
			LocalDate t=LocalDate.parse(to);
			Schedule employee = scheduleRepository.findById(id).get();
			int workedDays=employee.getWorkedDays(f, t);
			redirAttrs.addFlashAttribute("workdays", workedDays); 
			
			Date f2 = Date.valueOf(f);
			Date t2 = Date.valueOf(t);
			int d = dayoffRepository.findNumberDayoffs(id, f2, t2).size();
			redirAttrs.addFlashAttribute("dayoffs", d); 
			redirAttrs.addFlashAttribute("froms", from); 
			redirAttrs.addFlashAttribute("tos", to); 
			
		}

		return new RedirectView("/manager/edit/" + id);
	}
	

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditBookingPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("scheduleupdate");
		Optional<Schedule> employee = scheduleRepository.findById(id);
		mav.addObject("employee", employee.get());
		mav.addObject("dayoff", dayoffRepository.findByEmployeeId(id));
		mav.addObject("monthdayoff", dayoffRepository.findThisMonthDayoffs(id).size());
		mav.addObject("weekday", list);

		return mav;
	}

	@RequestMapping("/save")
	public RedirectView saveProduct(@ModelAttribute("employee") Schedule employee, RedirectAttributes redirAttrs) {
		
		Schedule e = scheduleRepository.findById(employee.getEmployee_id()).get();
		String s = employee.getWeekdays();
		e.setEmployee_id(employee.getEmployee_id());
		e.setEmployee(employee.getEmployee());
		e.setFrom(employee.getFrom());
		e.setTo(employee.getTo());
		e.setSalary_per_hour(employee.getSalary_per_hour());
		e.setSalary_per_month(employee.getSalary_per_month());
		e.setWeekdays(s.replaceAll(",", ""));
		scheduleRepository.save(e);
		redirAttrs.addFlashAttribute("message", "Saved!");
		
		return new RedirectView("/manager/edit/" + employee.getEmployee_id());
	}

}
