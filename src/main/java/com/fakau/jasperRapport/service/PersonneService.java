package com.fakau.jasperRapport.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.sf.jasperreports.engine.util.JRLoader;


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
		return personnes;
	}
	public String generatePDF() {
		String homeDirectory = System.getProperty("user.home");
		String outPutFile = homeDirectory + File.pathSeparatorChar + "personne_liste.pdf";
		JRBeanCollectionDataSource jRBeanCollectionDataSource =new JRBeanCollectionDataSource(getAll());
		JRDataSource jRDataSource =new JREmptyDataSource();
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("ds", jRBeanCollectionDataSource);
		try {
		 InputStream in = getClass().getClassLoader().getResourceAsStream("jasper_rapport/personne_list.jasper");
		 System.out.println(in.toString());
		 JasperReport jasperReport= (JasperReport) JRLoader.loadObject(in);
		 JasperPrint jasperPrint=
				 JasperFillManager.
				 fillReport(jasperReport, parameters, jRDataSource);
		 FileOutputStream outputStream=new FileOutputStream(new File(outPutFile));
		 JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		} catch (Exception e) {
			e.printStackTrace();
			return "error: " + e.getMessage();
		}
		return "file created";
	}
}
