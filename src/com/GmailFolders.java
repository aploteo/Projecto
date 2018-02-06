package com;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;

public class GmailFolders {
	public static void main(String[] args) throws Exception {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imap");
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		store.connect("imap.gmail.com", "melaninex@gmail.com", "melaninex");
		System.out.println(store);

		Folder[] f = store.getDefaultFolder().list("*");
		for(Folder fd:f)
		    System.out.println(fd.getName());
	}
}
