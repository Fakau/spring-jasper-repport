package com.fakau.jasperRapport.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterName;

import org.omg.CORBA_2_3.portable.OutputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.fakau.jasperRapport.model.Personne;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;


@Service
public class PersonneService {
	
	public List<Personne> getAll() {
		List<Personne> personnes =new ArrayList<Personne>();
		Personne personne1=new Personne();
		Personne personne2=new Personne();
		Personne personne3=new Personne();
		
		personne1.setId(1L);
		personne2.setId(2L);
		personne3.setId(3L);
		
		personne1.setName("Kafka");
		personne2.setName("Clerel");
		personne3.setName("Barbara");
		
		personne1.setEmail("laurentkafka@gmail.com");
		personne2.setEmail("clerel@gmail.com");
		personne3.setEmail("barbara@gmail.com");
		
		personne1.setPrenom("Laurent");
		personne2.setPrenom("Lange");
		personne3.setPrenom("Lazard");
		
		personnes.add(new Personne());
		personnes.add(personne1);
		personnes.add(personne2);
		personnes.add(personne3);
		personnes.add(personne1);
		personnes.add(personne2);
		personnes.add(personne3);
		personnes.add(personne1);
		personnes.add(personne2);
		personnes.add(personne3);
		personnes.add(personne1);
		personnes.add(personne2);
		personnes.add(personne3);
		personnes.add(personne1);
		personnes.add(personne2);
		personnes.add(personne3);
		personnes.add(personne1);
		personnes.add(personne2);
		personnes.add(personne3);
		personnes.add(personne2);
		personnes.add(personne3);
		personnes.add(personne1);
		personnes.add(personne2);
		personnes.add(personne3);
		
		
		return personnes;
	}
	public JasperPrint createJasperPrint() {
		JRBeanCollectionDataSource jRBeanCollectionDataSource =new JRBeanCollectionDataSource(getAll());
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("ds", jRBeanCollectionDataSource);
		
		try {
		 InputStream inputStream = new ClassPathResource("jasper/person_list.jasper").getInputStream();		 
		 JasperReport jasperReport= (JasperReport) JRLoader.loadObject(inputStream);
		 JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parameters, jRBeanCollectionDataSource);
		 return jasperPrint;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	public String generatePDF(JasperPrint jasperPrint) {
		try {
			String homeDirectory = System.getProperty("user.home");
			String outPutFile = homeDirectory +  "/Desktop/personne_liste.pdf";
			FileOutputStream outputStream=new FileOutputStream(new File(outPutFile));
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			outputStream.close();
			return "file created";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	 }
	public String directPrint(JasperPrint jasperPrint, String printerName) throws JRException {
		PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		  printRequestAttributeSet.add(MediaSizeName.ISO_A4);
		  if (jasperPrint.getOrientationValue() == net.sf.jasperreports.engine.type.OrientationEnum.LANDSCAPE) {
		   printRequestAttributeSet.add(OrientationRequested.LANDSCAPE);
		  } else {
		   printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
		  }
		  PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
		  printServiceAttributeSet.add(new PrinterName(printerName, null));

		  JRPrintServiceExporter exporter = new JRPrintServiceExporter();
		  SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
		  configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
		  configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
		  configuration.setDisplayPageDialog(false);
		  configuration.setDisplayPrintDialog(false);

		  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		  exporter.setConfiguration(configuration);
          exporter.exportReport();
          return "";
	 }
	
}
