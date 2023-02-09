package com_hibernate.com_hibernate_ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Custom exception class
class InvalidPin extends Exception{
	String msg;
	InvalidPin(String msg){
		super(msg);
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "InvalidPin [msg= " + msg + "]";
	}
}



public class BalanceEnquiry {
	
	private static JTextField enterPin;	//textField to enter pin
	private static JPanel panel_1;
	private static JLabel printMessage;	//output the operation status
	private static JPanel panel;	//main panel
	private static JLabel errorMessage;


	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel getBalanceEnquiry() {
		
		panel = new JPanel(); // main panel
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 479, 288);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(28, 103, 424, 174);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter your pin:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(119, 11, 184, 38);
		panel_1.add(lblNewLabel_1_1);
		
		enterPin = new JTextField();
		enterPin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				errorMessage.setText("");
			}
		});
		enterPin.setColumns(10);
		enterPin.setBounds(84, 60, 252, 30);
		panel_1.add(enterPin);
		
		JButton btnCheck = new JButton("Check");
		
		//On click of the click button:
		btnCheck.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
			/*code inside try block might generate number format 
			* exception while converting string to int
			*/
			try {
				//printMessage.setVisible(true);
				//panel_1.setVisible(false);

				//get the value entered by the user and parse it to integer
				//if the entered value contains null or alphanumeric value, throw NumberFormatException
				int pin = Integer.parseInt(enterPin.getText());
					
				Session session = Connection.getSessionFactoryObject().openSession();
					
				//If account number is not present in the database - ObjectNotFound Exception 
				Account account = (Account) session.load(Account.class, HomePage.currentUser);
				
				
				printMessage.setVisible(true);
				panel_1.setVisible(false);
				
				if(pin == account.getAccPin())	//If pin entered by the user matches the record then:
					printMessage.setText("Balance: " + account.getAmount());
				else	//If pin entered by the user mismatches the record then:
					printMessage.setText("You have entered incorrect pin!");
			}
			//string to int conversion errors
			catch(NumberFormatException exp) {
				printMessage.setText("Invalid pin!!");
				
				if(enterPin.getText().equals(""))
					errorMessage.setText("Please enter the pin!");
				else	//if entered pin is alphanumeric
					errorMessage.setText("Invalid amount! Please enter numbers only");
				System.out.print(exp);
			}
			catch(ObjectNotFoundException exp) {
					System.out.print(exp);
			}
			catch(Exception exp) {
					
			}	
		}
		});
		
		btnCheck.setBounds(153, 104, 113, 30);
		panel_1.add(btnCheck);
		
		errorMessage = new JLabel("");
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessage.setForeground(new Color(255, 0, 0));
		errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorMessage.setBounds(84, 149, 252, 14);
		panel_1.add(errorMessage);
		
		JLabel lblNewLabel = new JLabel("Balance Enquiry");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(120, 34, 237, 40);
		panel.add(lblNewLabel);
		
		printMessage = new JLabel("Please wait...");
		printMessage.setVisible(false);
		printMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		printMessage.setForeground(new Color(255, 255, 255));
		printMessage.setHorizontalAlignment(SwingConstants.CENTER);
		printMessage.setBackground(new Color(240, 240, 240));
		printMessage.setBounds(54, 125, 382, 52);
		panel.add(printMessage);
		
		return panel;
		
	}
}
