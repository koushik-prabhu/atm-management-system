package com_hibernate.com_hibernate_ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class NewUser {
	

	private JFrame frame;
	private JTextField name;
	private JTextField adhar;
	private JTextField pan;
	private JTextField phone;
	private JTextField address;
	private JTextField dob;
	private JTextField pincode;
	
	private static String country_names[] = {"India", "America", "England"};	//Country drop down list
	private static long currentAccountNo = 10000000000l;	//assigns new account number to new customer
	private static SessionFactory sessionFactory;	//sessionfactory reference
	




	public NewUser() {
		initialize();
		sessionFactory = Connection.getSessionFactoryObject();
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("select max(accNo) from Account");
		
		ArrayList list = (ArrayList) query.list();
		
		if(list.get(0) != null)
			currentAccountNo = (Long) list.get(0);

		session.close();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 739, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New User");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(270, 7, 188, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(56, 72, 612, 337);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Full Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 11, 81, 23);
		panel.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(10, 35, 226, 28);
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Adhar No.");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(10, 74, 81, 23);
		panel.add(lblNewLabel_1_1);
		
		adhar = new JTextField();
		adhar.setColumns(10);
		adhar.setBounds(10, 96, 226, 28);
		panel.add(adhar);
		
		JLabel lblNewLabel_1_2 = new JLabel("PAN No.");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(10, 134, 81, 23);
		panel.add(lblNewLabel_1_2);
		
		pan = new JTextField();
		pan.setColumns(10);
		pan.setBounds(10, 158, 226, 28);
		panel.add(pan);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Gender:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2_1.setBounds(10, 209, 81, 23);
		panel.add(lblNewLabel_1_2_1);
		
		
		ButtonGroup gender = new ButtonGroup();
		
		final JRadioButton male = new JRadioButton("Male");
		male.setBounds(80, 210, 70, 23);
		panel.add(male);
		
		final JRadioButton female = new JRadioButton("Female");
		female.setBounds(152, 210, 70, 23);
		panel.add(female);
		
		gender.add(male); 
		gender.add(female);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Phone Number");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2_2.setBounds(10, 245, 91, 23);
		panel.add(lblNewLabel_1_2_2);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(10, 269, 226, 28);
		panel.add(phone);
		
		JLabel lblNewLabel_1_3 = new JLabel("Address");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(352, 11, 81, 23);
		panel.add(lblNewLabel_1_3);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(352, 35, 226, 28);
		panel.add(address);
		
		JLabel label_dob = new JLabel("Date of birth");
		label_dob.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_dob.setBounds(352, 74, 81, 23);
		panel.add(label_dob);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Terms and conditions*");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3_2.setBounds(352, 271, 135, 23);
		panel.add(lblNewLabel_1_3_2);
		
		final JCheckBox termsAndCon = new JCheckBox("");
		termsAndCon.setBounds(494, 269, 99, 28);
		panel.add(termsAndCon);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Pincode");
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3_1_1.setBounds(352, 134, 81, 23);
		panel.add(lblNewLabel_1_3_1_1);
		
		dob = new JTextField();
		dob.setColumns(10);
		dob.setBounds(352, 96, 226, 28);
		panel.add(dob);
		
		JLabel lblNewLabel_1_3_1_1_1 = new JLabel("Country");
		lblNewLabel_1_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3_1_1_1.setBounds(352, 197, 81, 23);
		panel.add(lblNewLabel_1_3_1_1_1);
		
		final JComboBox country = new JComboBox(country_names);
		country.setBounds(352, 220, 226, 28);
		panel.add(country);
		
		pincode = new JTextField();
		pincode.setColumns(10);
		pincode.setBounds(352, 158, 226, 28);
		panel.add(pincode);
		
		JButton btnNewAccount = new JButton("Create Account");
		
		
		//When clicked on create new account
		btnNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String gen = "";
				if(male.isSelected()) 
					gen = "male";
				if(female.isSelected())
					gen=  "female";
				
				Account newAccount = new Account();
				
				//New User DB details
				newAccount.setAccNo(currentAccountNo++);
				newAccount.setFullName(name.getText());
				newAccount.setAdharNo(Long.parseLong(adhar.getText()));
				newAccount.setPhoneNo(Long.parseLong(phone.getText()));
				newAccount.setPhoneNo(Integer.parseInt(pincode.getText()));
				newAccount.setPanNo(pan.getText());
				newAccount.setAddress(address.getText());
				newAccount.setDob(dob.getText());
				newAccount.setCountry(country_names[country.getSelectedIndex()]);
				newAccount.setGender(gen);
	
				boolean terms = termsAndCon.isSelected();
				
				Session session = sessionFactory.openSession();
				
				Query query = session.createQuery("from Account where adharNo = :adhar_id or panNo = :pan_id");
				query.setParameter("adhar_id", Long.parseLong(adhar.getText()));
				query.setParameter("pan_id", pan.getText());
				

				
				List list = query.list();
				
				if(list == null) {
					
					Session session1 = sessionFactory.openSession();
					session1.beginTransaction();
					session1.save(newAccount);
					session1.getTransaction().commit();
				}
				else {
					System.out.print("already there");
				}
	


			}
				
			
		});
		btnNewAccount.setBounds(520, 21, 110, 30);
		frame.getContentPane().add(btnNewAccount);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Login();
				frame.dispose();
				
			}
		});
		btnGoBack.setBounds(68, 21, 110, 30);
		frame.getContentPane().add(btnGoBack);
	}
}
