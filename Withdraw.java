package com_hibernate.com_hibernate_ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Withdraw {

	private JFrame frame;
	private JPanel panel;
	private JTextField enterAmount;
	private JPanel panel_1;
	private JLabel printMessage;	//print output message of the withdraw
	private JPanel panel_2;		
	private JLabel errorMessage;	//print error messages

	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel withDraw() {
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 467, 286);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(0, 75, 467, 179);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter amount");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(143, 11, 184, 38);
		panel_1.add(lblNewLabel_1_1);
		
		enterAmount = new JTextField();
		enterAmount.setColumns(10);
		enterAmount.setBounds(109, 60, 252, 32);
		panel_1.add(enterAmount);
		
		JButton btnWithdraw = new JButton("Withdraw");
		
		//when user clicked withdraw button:
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*code inside try block might generate number format 
				* exception while converting string to int
				*/
				try {
					//get the value entered by the user and parse it to integer
					//if the entered value contains null or alphanumeric value, throw NumberFormatException
					int amt = Integer.parseInt(enterAmount.getText());
					
					//Popup window
					//Ask user to confirm withdrawal using atm pin
					String pinText = JOptionPane.showInputDialog(panel, "Enter your pin: ","Confirm", JOptionPane.WARNING_MESSAGE);
					
					//if user does not click on cancel or x button of popup:
					if(pinText != null) {

						int pin = Integer.parseInt(pinText);				
						String message = Authentication.withDrawAmount(amt, pin);
						panel_1.setVisible(false);
						panel_2.setVisible(true);
						printMessage.setText(message);
					}
					
				}
				catch(NumberFormatException exp) {
					if(enterAmount.getText().equals(""))
						errorMessage.setText("Please enter the amount!");
					else	//if entered pin is alphanumeric
						errorMessage.setText("Invalid amount! Please enter numbers only.");
					System.out.println(exp + " - Entered invalid value in the amount field!");
				}
				
			}
		});
		btnWithdraw.setBounds(177, 111, 110, 30);
		panel_1.add(btnWithdraw);
		
		errorMessage = new JLabel("");
		errorMessage.setForeground(new Color(255, 0, 0));
		errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessage.setBounds(84, 154, 301, 14);
		panel_1.add(errorMessage);
		
		JLabel lblNewLabel = new JLabel("Cash Withdraw");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(112, 22, 242, 39);
		panel.add(lblNewLabel);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(10, 85, 447, 128);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		printMessage = new JLabel("");
		printMessage.setForeground(new Color(255, 255, 255));
		printMessage.setHorizontalAlignment(SwingConstants.CENTER);
		printMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		printMessage.setBounds(85, 31, 283, 55);
		panel_2.add(printMessage);
		
		return panel;
	}
}
