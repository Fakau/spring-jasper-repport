package com.fakau.jasperRapport.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fakau.jasperRapport.model.Printer;
import com.fakau.jasperRapport.service.PrinterService;

@RestController
@RequestMapping("api")
public class PrinterRest {
	
	@Autowired
    private PrinterService printerService;
	
	@RequestMapping("/printers")
	private List<Printer> getAll(){
		return printerService.getAllPrinter();
	}
}
