package com.fakau.jasperRapport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fakau.jasperRapport.service.PersonneService;

@RestController
@RequestMapping("api")
public class PersonneRest {
	@Autowired
	private PersonneService PersonneService;
	
	@GetMapping("/test")
	public String test() {
		return "this is a test";
	}
	@GetMapping("/print")
	public String generatePDF() {
		return PersonneService.generatePDF();
	}
}
