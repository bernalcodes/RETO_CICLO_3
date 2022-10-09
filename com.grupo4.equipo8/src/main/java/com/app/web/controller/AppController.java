package com.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	@GetMapping(value = { "/", "/index" })
	public String viewHomePage() {
		return "index";
	}
}