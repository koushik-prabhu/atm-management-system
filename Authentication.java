package com_hibernate.com_hibernate_ATM;

import javax.swing.JLabel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Authentication {
	
	public static boolean loginValidation(long accNo, int pin) {
		
		SessionFactory sf = Connection.getSessionFactoryObject();
		Session session = sf.openSession();
		Account account = session.get(Account.class, accNo);
		return (account == null)? false: true;	
	}
	
	
	public static String changePin(int cPin, int nPin, int cNPin) {
		String message = "";
		
		//open session
		Session session = Connection.getSessionFactoryObject().openSession();
		Account account = session.get(Account.class, HomePage.currentUser);		//get the pin from the user
		

		message = (account != null)? "Pin changed successfully!" : "Incorrect pin!";
		message = (nPin != cNPin)? "pin mismatched!" : message;
		
		if(message.equals("Pin changed successfully!")) {
			account.setAccPin(nPin);
			session.beginTransaction();
			session.saveOrUpdate(account);
			session.getTransaction().commit();
		}
		return message;
		
	}
}
