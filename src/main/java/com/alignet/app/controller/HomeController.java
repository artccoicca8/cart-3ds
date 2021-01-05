package com.alignet.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/initial")
	public String getIndex() {
		return "Initial Aplication";
	}

}
