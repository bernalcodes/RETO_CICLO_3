package com.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.web.service.HistoryServiceImpl;

@Controller
public class HistoryController {
	@Autowired
	private HistoryServiceImpl service;

	@CrossOrigin
	@GetMapping("/history/")
	public String listHistory(Model model) {
		model.addAttribute("entries", service.listAllEntries());
		return "history";
	}
}
