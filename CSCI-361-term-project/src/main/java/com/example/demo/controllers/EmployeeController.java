package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
// this controller is for employee login handler
@Controller

public class EmployeeController {

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ModelAndView employee() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee"); // resources/template/login.html
        return modelAndView;
    }



}
