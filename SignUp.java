package com_hibernate.com_hibernate_ATM;

import java.util.concurrent.ThreadLocalRandom;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class SignUp implements KeyListener, Runnable {

	private JFrame frame;
	private JTextField Jname;
	private JTextField Jadhar;
	private JTextField Jpan;
	private JTextField Jphone;
	private JTextField Jaddress;
	private JTextField dob;
	private JTextField Jpincode;
	
	private static String country_names[] = {"India", "America", "England"};	//Country drop down list
	private static long currentAccountNo = 10000000000l;	//assigns new account number to new customer
	private static SessionFactory sessionFactory;	//sessionfactory reference
	private final Action action = new SwingAction();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUp() {
		initialize();
		
		
	}
	
	public void run() {
		sessionFactory = Connection.getSessionFactoryObject();
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("select max(accNo) from Account");
		
		ArrayList list = (ArrayList) query.list();
		
		if(list.get(0) != null)
			currentAccountNo = (Long) list.get(0);
		
		System.out.print(currentAccountNo);

		session.close();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 739, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
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
		
		Jname = new JTextField();
		Jname.setBounds(10, 35, 226, 28);
		panel.add(Jname);
		Jname.setColumns(10);
		Jname.addKeyListener(this);
		
		JLabel lblNewLabel_1_1 = new JLabel("Adhar No.");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(10, 74, 81, 23);
		panel.add(lblNewLabel_1_1);
		
		Jadhar = new JTextField();
		Jadhar.setColumns(10);
		Jadhar.setBounds(10, 96, 226, 28);
		panel.add(Jadhar);
		
		JLabel lblNewLabel_1_2 = new JLabel("PAN No.");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(10, 134, 81, 23);
		panel.add(lblNewLabel_1_2);
		
		Jpan = new JTextField();
		Jpan.setColumns(10);
		Jpan.setBounds(10, 158, 226, 28);
		panel.add(Jpan);
		
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
		
		Jphone = new JTextField();
		Jphone.setColumns(10);
		Jphone.setBounds(10, 269, 226, 28);
		panel.add(Jphone);
		
		JLabel lblNewLabel_1_3 = new JLabel("Address");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(352, 11, 81, 23);
		panel.add(lblNewLabel_1_3);
		
		Jaddress = new JTextField();
		Jaddress.setColumns(10);
		Jaddress.setBounds(352, 35, 226, 28);
		panel.add(Jaddress);
		
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
		
		Jpincode = new JTextField();
		Jpincode.setColumns(10);
		Jpincode.setBounds(352, 158, 226, 28);
		panel.add(Jpincode);
		
		JButton btnNewAccount = new JButton("Create Account");
		
		
		//When clicked on create new account
		btnNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String gen = "";
				if(male.isSelected()) 
					gen = "male";
				if(female.isSelected())
					gen=  "female";
				
				//newAccount.setAccNo(currentAccountNo++);
				String fullName = Jname.getText();
				String adharNo = Jadhar.getText();
				String phoneNo = Jphone.getText();
				String pinCode = Jpincode.getText();
				
				String panNo = Jpan.getText();
				String add = Jaddress.getText();
				String date_of_birth = dob.getText();
				String countrySelected = country_names[country.getSelectedIndex()];
				
				//System.out.print(ThreadLocalRandom.current().nextInt(50));
				
				
				
				Validation.validateFullName(fullName, Jname);	//validate fullname (void)
				long temp_adhar = Validation.validateAdharNo(adharNo, Jadhar);	//validate adhar number (long)
				long temp_phone = Validation.validatePhoneNo(phoneNo, Jphone);
				int temp_pin = Validation.validatePincode(pinCode, Jpincode);
				Validation.validatePanNo(panNo, Jpan);
				Validation.validateAddress(add, Jaddress);
				
				
				if(Validation.proceed()) {	//if all fields are correct then:
					
					Session session = sessionFactory.openSession();
					
					//check if adhar or pan id already registred 
					Query query = session.createQuery("from Account where adharNo = :adhar_id or panNo = :pan_id");
					query.setParameter("adhar_id", temp_adhar);	//bind parameters
					query.setParameter("pan_id", panNo);
	
					List list = query.list();	//get data into a list
					
					if(list.isEmpty()) {	//if no record found with entered adharNo or panNo
						
						//Crrate object of account and set value using setters
						Account newAccount = new Account();
						Address newAddress = new Address();
						
						long temp_accNo = ++currentAccountNo;
						newAccount.setAccNo(temp_accNo);
						
						newAccount.setFullName(fullName);
						newAccount.setAdharNo(temp_adhar);
						newAccount.setPhoneNo(temp_phone);
						
						newAddress.setPincode(temp_pin);
						newAccount.setPanNo(panNo);
						newAddress.setAddress(add);
						newAccount.setDob(date_of_birth);
						newAddress.setCountry(country_names[country.getSelectedIndex()]);
						newAccount.setGender(gen);
						newAccount.setAmount(2000);
						
						
						//Generate random number between 1000 and 9999 as pin number
						int mPin = ThreadLocalRandom.current().nextInt(1000, 9999);
						newAccount.setAccPin(mPin);
						
						Session session1 = sessionFactory.openSession();	//open new session to save object into databse
						session1.beginTransaction();
						session1.persist(newAccount);
						newAddress.setId(temp_accNo);
						newAccount.setAddress(newAddress);
						session1.save(newAccount);
						session1.getTransaction().commit();	
						
						JOptionPane.showMessageDialog(frame,"Atm No : " + currentAccountNo + "\nAtm Pin: " + mPin, "Please Note!", JOptionPane.WARNING_MESSAGE);
						
						new Login();
						frame.dispose();
					}
					else {	//if adharNo or panNo already exist
						
						
					}
		
				}
				else {
					System.out.print("error");
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

	public void keyTyped(KeyEvent e) {
		
		JTextField field = (JTextField) e.getComponent();
		field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
