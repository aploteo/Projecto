package com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.log.WebcamLogConfigurator;

public class CapturarWebCamComAlarme
{
	public static void main(String[] args)
	{
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(640, 480));
		webcam.open();
		String fileDraw;
		// int contagem = 1;

		WebcamLogConfigurator.configure("logback.xml");

		BufferedWriter bw = null;

		try
		{
			bw = new BufferedWriter(new FileWriter("C:\\frames\\logs\\log20171227.txt"));
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

		DateAndTime.criarDiretorios();

		ByteArrayOutputStream baos = null;

		Alarme alarme = new Alarme();
		Thread soarAlarme = new Thread(alarme);

		Color c = null;
		int red = 0;
		int green = 0;
		int blue = 0;
		boolean flag = true;
		int intervalo = 1000;

		while (true)
		{
			BufferedImage image = webcam.getImage();

			c = new Color(image.getRGB(410, 1));
			red = c.getRed();
			green = c.getGreen();
			blue = c.getBlue();

			if (red < 32 && green < 32 && blue < 32 && flag)
			{
				soarAlarme.start();
				flag = false;
				intervalo = 3000;
			}

			// String fileC = "C:\\Tnlenterprises\\Timelapse\\frames\\20171211\\"
			// + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(new Date()) +
			// ".jpg";
			String fileC = "C:\\frames\\20171227\\" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(new Date())
					+ ".jpg";
			String fileD = "D:\\frames\\20171227\\" + fileC.substring(19);
			Graphics2D graphics = image.createGraphics();
			Font font = new Font("ARIAL", Font.PLAIN, 17);
			graphics.setFont(font);
			fileDraw = fileD.substring(19, 29).replaceAll("-", "/") + " "
					+ fileD.substring(30, 38).replaceAll("-", ":");
			graphics.drawString(fileDraw, 475, 470);
			image.flush();

			try
			{
				ImageIO.write(image, "jpg", new File(fileC));
				// ImageIO.write(image, "jpg", new File(fileD));
				baos = new ByteArrayOutputStream();
				ImageIO.write(image, "jpg", baos);
				baos.flush();
				bw.write(fileC);
				bw.newLine();
				bw.flush();
			} catch (IOException e)
			{
				String s = "Excepção: " + e.toString() + "  Classe e linha: " + e.getStackTrace()[0];
				EscreverRegisto.escreverNoLog(s);
			}

			// byte[] imageInByte = baos.toByteArray();

			// if (contagem == 5) {
			// Send.em(imageInByte, fileC.substring(44));
			// contagem = 1;
			// } else
			// contagem++;

			try
			{
				Thread.sleep(intervalo);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
				String s = "Excepção: " + e.toString() + "  Classe e linha: " + e.getStackTrace()[0];
				EscreverRegisto.escreverNoLog(s);
			}
		}
	}
}