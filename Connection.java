package com_hibernate.com_hibernate_ATM;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection {
	
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactoryObject() {
		if(sessionFactory == null) {
			Configuration config = new Configuration();
			config.configure("hibernate.cfg.xml");
			config.addAnnotatedClass(Account.class);
			config.addAnnotatedClass(Address.class);
			config.addAnnotatedClass(Transaction.class);
			sessionFactory = config.buildSessionFactory();
		}	
		return sessionFactory;
	}
}
