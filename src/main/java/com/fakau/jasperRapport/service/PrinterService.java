package com.fakau.jasperRapport.service;

import java.util.ArrayList;
import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.springframework.stereotype.Service;

import com.fakau.jasperRapport.model.Printer;

@Service
public class PrinterService {

	public List<Printer> getAllPrinter(){
		List<Printer> printers = new ArrayList<Printer>();
		PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
		if(services.length > 0) {
			for(int i=0; i<services.length; i++) {
				Printer printer=new Printer();
				printer.setPrinterName(services[i].getName());
				printers.add(printer);
			}
		}
		return printers;
	}
}
