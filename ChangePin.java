package com_hibernate.com_hibernate_ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;



import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePin {
	private static JTextField currentPin;
	private static JTextField newPin;
	private static JTextField confirmNewPin;
	private static JButton btnChange;
	private static String message;
	private static JLabel showMessage;
	private static JPanel panel;
	private static JPanel panel_message;
	private static JPanel panel_pin;



	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel change() {

		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setLayout(null);
		panel.setBounds(26, 11, 463, 290);
		panel_pin = new JPanel();
		panel_pin.setBounds(10, 84, 439, 196);
		panel.add(panel_pin);
		panel_pin.setBackground(new Color(0, 0, 0));
		panel_pin.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Current pin : ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 11, 168, 39);
		panel_pin.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New pin :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(30, 55, 161, 39);
		panel_pin.add(lblNewLabel_1_1);
		
		currentPin = new JTextField();
		currentPin.setBounds(187, 18, 195, 28);
		panel_pin.add(currentPin);
		currentPin.setColumns(10);
		
		newPin = new JTextField();
		newPin.setColumns(10);
		newPin.setBounds(187, 61, 195, 28);
		panel_pin.add(newPin);
		
		confirmNewPin = new JTextField();
		confirmNewPin.setColumns(10);
		confirmNewPin.setBounds(187, 106, 195, 28);
		panel_pin.add(confirmNewPin);
		
		btnChange = new JButton("Change");
		
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int cPin;	//current pin
				int nPin;	//new pin
				int cNPin;	//confirm new pin
				try {
					cPin = Integer.parseInt(currentPin.getText());
					nPin = Integer.parseInt(newPin.getText());
					cNPin = Integer.parseInt(confirmNewPin.getText());
					
					//if all fields are digits then:
					
					panel_pin.setVisible(false);
					panel_message.setVisible(true);
					message = Authentication.changePin(cPin, nPin, cNPin);
					showMessage.setText(message);
					
					
					
				}
				catch(NumberFormatException exp) {
					System.out.println("Enter non numberic data!\n" + exp);
				}
				
			}
		});
		btnChange.setBounds(155, 157, 147, 28);
		panel_pin.add(btnChange);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Confirm new pin :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(0, 99, 168, 39);
		panel_pin.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel = new JLabel("Change Pin");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(99, 11, 233, 59);
		panel.add(lblNewLabel);
		
		panel_message = new JPanel();
		panel_message.setBounds(10, 84, 439, 196);
		panel.add(panel_message);
		panel_message.setBackground(new Color(0, 0, 0));
		panel_message.setLayout(null);
		
		showMessage = new JLabel("");
		showMessage.setHorizontalAlignment(SwingConstants.CENTER);
		showMessage.setForeground(new Color(255, 255, 255));
		showMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		showMessage.setBounds(10, 57, 385, 45);
		panel_message.add(showMessage);
		panel_message.setVisible(false);
		
		return panel;
	
	}
}
