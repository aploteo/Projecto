package com;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Alarme implements Runnable
{
	public void run()
	{
		JFXPanel fxPanel = new JFXPanel();
		Media som = new Media(
				new File("C:\\Java\\workspace\\Projecto\\src\\com\\sounds\\INXS - Track 05.mp3").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(som);
		mediaPlayer.play();
		try
		{
			Thread.sleep(182000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
