package com;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DateAndTime {
	public static void criarDiretorios() {
		
		LocalDate date = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	    LocalTime time = LocalTime.parse(new SimpleDateFormat("HH:mm:ss.SSSSSSSSS").format(new Date()));
	    
	    boolean dirCriado = false;
	    
	    if (time.isAfter(LocalTime.parse("12:00:00.999999999")) && time.isBefore(LocalTime.parse("23:59:59.999999999"))) {
	    	date = date.plusDays(1);
	    }
	    System.out.println(date + " " + time);
	    String dateC = "C:/frames/" + date.toString().replaceAll("-", "");
//	    String dateD = "D:/frames/" + date.toString().replaceAll("-", "");
	    
	    File newDir = new File(dateC);
	    
	    try {
	    	dirCriado = newDir.mkdir();
	    	if (dirCriado == false) {
	    		String s = "O diretório " + newDir.toString() + " não foi criado";
	      	  	EscreverRegisto.escreverNoLog(s);
	    	}
	    } catch(Exception e) {
	    	String s = "Excepção: " + e.toString() + "  Classe e linha: " + e.getStackTrace()[0];
	    	EscreverRegisto.escreverNoLog(s);
	    }
	    
//	    newDir = new File(dateD);
//	    
//		try {
//			dirCriado = newDir.mkdir();
//			if (dirCriado == false) {
//	    		String s = "O diretório " + newDir.toString() + " não foi criado";
//	      	  	EscreverRegisto.escreverNoLog(s);
//	    	}
//		} catch (Exception e) {
//			String s = "Excepção: " + e.toString() + "  Classe e linha: " + e.getStackTrace()[0];
//			EscreverRegisto.escreverNoLog(s);
//		}
	}
}
