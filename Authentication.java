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
}
