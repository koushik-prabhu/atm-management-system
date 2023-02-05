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
	private static JTextField enterAmount;
	private static JPanel panel_1;
	private static JLabel printMessage;


	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel getBalanceEnquiry() {
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 486, 288);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(36, 97, 418, 159);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter your pin:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(121, 11, 184, 38);
		panel_1.add(lblNewLabel_1_1);
		
		enterAmount = new JTextField();
		enterAmount.setColumns(10);
		enterAmount.setBounds(121, 60, 190, 30);
		panel_1.add(enterAmount);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					printMessage.setVisible(true);
					panel_1.setVisible(false);

					int pin = Integer.parseInt(enterAmount.getText());
					
					Session session = Connection.getSessionFactoryObject().openSession();
					
					Account account = (Account) session.load(Account.class, HomePage.currentUser);

					printMessage.setText("Amount: " + account.getAmount());

										
					
				}
				catch(NumberFormatException exp) {
					printMessage.setText("Invalid pin!!");
					System.out.print(exp);
				}
				catch(ObjectNotFoundException exp) {
					System.out.print(exp);
				}
				catch(Exception exp) {
					
				}
				
			}
		});
		btnCheck.setBounds(162, 107, 115, 30);
		panel_1.add(btnCheck);
		
		JLabel lblNewLabel = new JLabel("Balance Enquiry");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(126, 40, 237, 40);
		panel.add(lblNewLabel);
		
		printMessage = new JLabel("Please wait...");
		printMessage.setVisible(false);
		printMessage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		printMessage.setForeground(new Color(255, 255, 255));
		printMessage.setHorizontalAlignment(SwingConstants.CENTER);
		printMessage.setBackground(new Color(240, 240, 240));
		printMessage.setBounds(120, 125, 254, 52);
		panel.add(printMessage);
		
		return panel;
		
	}
}
