package com.tikal.aeronautikal.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


	@Controller

	public class Test {
	    
	    @RequestMapping(value = "/test")
	    @ResponseBody
	    public String fecha() {
	        return "Hoy es: " + LocalDate.now()
	                .format(DateTimeFormatter.ISO_DATE);
	    }
	}