package com.greatlearning.customer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
	@RequestMapping("/")
	public String showHomePage() {
		return "home";
	}
}
