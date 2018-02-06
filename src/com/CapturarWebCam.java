package com;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import com.github.sarxos.webcam.Webcam;

public class CapturarWebCam {

	public static void main(String[] args) 
	{
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(640, 480));
		webcam.open();
		String fileDraw;
//		int contagem = 1;

		DateAndTime.criarDiretorios();

		ByteArrayOutputStream baos = null;

		while (true) 
		{
			BufferedImage image = webcam.getImage();
			String fileC = "C:\\Tnlenterprises\\Timelapse\\frames\\20171117\\"
					+ new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(new Date()) + ".jpg";
			String fileD = "D:\\frames\\20171117\\" + fileC.substring(44);
			Graphics2D graphics = image.createGraphics();
			Font font = new Font("ARIAL", Font.BOLD, 17);
			graphics.setFont(font);
			fileDraw = fileD.substring(19, 29).replaceAll("-", "/") + " "
					+ fileD.substring(30, 38).replaceAll("-", ":");
			graphics.drawString(fileDraw, 475, 470);
			image.flush();

			try {
				ImageIO.write(image, "jpg", new File(fileC));
				ImageIO.write(image, "jpg", new File(fileD));
				baos = new ByteArrayOutputStream();
				ImageIO.write(image, "jpg", baos);
				baos.flush();
			} catch (IOException e) {
				String s = "Excepção: " + e.toString() + "  Classe e linha: " + e.getStackTrace()[0];
		    	  EscreverRegisto.escreverNoLog(s);
			}
			
//			byte[] imageInByte = baos.toByteArray();
			
//			if (contagem == 5) {
//				Send.em(imageInByte, fileC.substring(44));
//				contagem = 1;
//			} else
//				contagem++;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				String s = "Excepção: " + e.toString() + "  Classe e linha: " + e.getStackTrace()[0];
		    	  EscreverRegisto.escreverNoLog(s);
			}
		}
	}
}