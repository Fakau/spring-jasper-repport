package com.fakau.jasperRapport.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fakau.jasperRapport.model.Personne;
import com.fakau.jasperRapport.service.PersonneService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("api")
public class PersonneRest {
	@Autowired
	private PersonneService personneService;
	
	@GetMapping("/listpdf")
	public String generatePDF() {
		return personneService.generatePDF(personneService.createJasperPrint());
	}
	@GetMapping("/direct-print")
	public String directPrint(@RequestParam("printer") String printerName) {
		try {
			return personneService.directPrint(personneService.createJasperPrint(), printerName);
		} catch (JRException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	@GetMapping("/person")
	public List<Personne> getAllPersonne() {
		return personneService.getAll();
	}
}
