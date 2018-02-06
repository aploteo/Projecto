package com;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.text.*;
import java.util.Date;

public class EscreverRegisto {

	private static String logFile = "C:\\frames\\logs\\logs.txt";

	public static void escreverNoLog(String s) {
		Date data = null;
		DateFormat df = null;
		String dataHora = null;
		
		try {
			FileWriter fw = new FileWriter(logFile, true);
			BufferedWriter bw = new BufferedWriter(fw);

			data = new Date();
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dataHora = df.format(data);
			bw.write(dataHora + " " + s +"\r\n");
			
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
