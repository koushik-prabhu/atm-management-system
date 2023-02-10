package com_hibernate.com_hibernate_ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MiniStatement {

	private static JPanel panel;	//main panel for miniStatment
	private static JTable table;	//table to print the statement
	private static JTextField enterPin;
	private static JScrollPane sp;
	private static JPanel panel_message;
	private static JPanel panel_pin;
	static Object[][] rows = new Object[5][6];
	static String[] columns = {"SL.", "Trans.Id", "Date&Time", "type", "status", "bal"};	//table attribute names
	private static JLabel printMessage;	//to output the message of the operation
	private static JLabel errorMessage;	//to print errors in the panel1


	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel miniStatement() {
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 486, 288);
		panel.setLayout(null);
		
		panel_pin = new JPanel();
		panel_pin.setLayout(null);
		panel_pin.setBackground(Color.BLACK);
		panel_pin.setBounds(34, 90, 418, 176);
		panel.add(panel_pin);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter your pin:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(114, 11, 184, 38);
		panel_pin.add(lblNewLabel_1_1);
		
		enterPin = new JTextField();
		enterPin.setColumns(10);
		enterPin.setBounds(79, 60, 252, 32);
		panel_pin.add(enterPin);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				errorMessage.setText("");
			}
		});
		
		//when user clicks on check button:
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*code inside try block might generate number format
				* exception while converting string to int
				*/
				try {
					
					/*get the value entered by the user and parse it to integer
					*if the entered value contains null or alphanumeric value, throw NumberFormatException
					*/
					int pin = Integer.parseInt(enterPin.getText());
		
					//opening a session to get the details from the database
					Session session = Connection.getSessionFactoryObject().openSession();
					
					//Loading the user data with the help of atmno 
					Account account = (Account) session.load(Account.class, HomePage.currentUser);
					
					panel_pin.setVisible(false);
					//check if the entered pin matches the record in the database
					if(pin != account.getAccPin()) {

						panel_message.setVisible(true);
						printMessage.setText("You have entered invalid pin!");
						throw new Exception("Invalid pin");
					}

					Query query = session.createQuery("from Transaction where account_id = :id");
					query.setParameter("id", HomePage.currentUser);
					query.setMaxResults(5);
					ArrayList list = (ArrayList) query.list();
					
					
					//Map all the values from database to 2d array
					for(int i = 0; i < list.size(); i++) {
						Transaction action = (Transaction) list.get(list.size() - 1 - i);
							rows[i][0] = i + 1;
							rows[i][1] = action.getTransaction_id();
							rows[i][2] = action.getTransaction_date() + "\n" + action.getTransaction_time();
							rows[i][3] = action.getTransaction_type();
							rows[i][4] = action.getTransaction_status();
							rows[i][5] = action.getBalance();

					}
					sp.setVisible(true);


					
					
				}
				catch(NumberFormatException exp) {
					System.out.print(exp);
					if(enterPin.getText().equals(""))
						errorMessage.setText("Please enter the pin!");
					else	//if entered pin is alphanumeric
						errorMessage.setText("Invalid amount! Please enter numbers only");
				}
				catch(ObjectNotFoundException exp) {
					System.out.print(exp);
				}
				catch(Exception exp) {
					
				}
				
			}
		});
		btnCheck.setBounds(149, 107, 115, 30);
		panel_pin.add(btnCheck);
		
		errorMessage = new JLabel("");
		errorMessage.setForeground(new Color(255, 0, 0));
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorMessage.setBounds(0, 148, 418, 14);
		panel_pin.add(errorMessage);
		
		JLabel lblNewLabel = new JLabel("Mini Statement");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(124, 11, 236, 55);
		panel.add(lblNewLabel);
		
		table = new JTable(rows, columns);
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(0, 0, 0));
		
		sp = new JScrollPane(table);
		sp.setLocation(0, 92);
	    sp.setSize(486, 196);
	    table.setFillsViewportHeight(true);
	    sp.setVisible(false);
   
		panel.add(sp);
		
		panel_message = new JPanel();
		panel_message.setBackground(new Color(0, 0, 0));
		panel_message.setBounds(0, 110, 486, 54);
		panel.add(panel_message);
		panel_message.setLayout(null);
		
		printMessage = new JLabel("");
		printMessage.setBackground(new Color(0, 0, 0));
		printMessage.setForeground(new Color(255, 255, 255));
		printMessage.setHorizontalAlignment(SwingConstants.CENTER);
		printMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		printMessage.setBounds(0, 0, 486, 54);
		panel_message.add(printMessage);
		
		return panel;
	}
}
