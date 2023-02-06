package com_hibernate.com_hibernate_ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FastCash implements ActionListener {

	static JPanel panel;
	private static JButton btnSeven;
	private static JButton btnEight;
	private static JButton btnFive;
	private static JButton btnSix;
	private static JButton btnThree;
	private static JButton btnFour;
	private static JButton btnOne;
	private static JButton btnTwo;
	private static JPanel panel_1;
	private JPanel panel_2;
	private JLabel printMessage;

	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel fastCash() {
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setLayout(null);
		panel.setBounds(26, 11, 454, 285);
		
		JLabel lblNewLabel = new JLabel("Fast Cash");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(112, 11, 224, 39);
		panel.add(lblNewLabel);
					
					panel_1 = new JPanel();
					panel_1.setBounds(0, 57, 452, 228);
					panel.add(panel_1);
					panel_1.setBackground(new Color(0, 0, 0));
					panel_1.setLayout(null);
					
					btnOne = new JButton("100");
					btnOne.addActionListener(this);
					
						btnOne.setBounds(40, 11, 145, 32);
						panel_1.add(btnOne);
						btnTwo = new JButton("200");
						
								
								btnTwo.setBounds(260, 11, 145, 32);
								panel_1.add(btnTwo);
								
								btnThree = new JButton("500");
								btnThree.setBounds(40, 66, 145, 32);
								panel_1.add(btnThree);
								
								btnFive = new JButton("2000");
								btnFive.setBounds(40, 119, 145, 32);
								panel_1.add(btnFive);
								
								btnSeven = new JButton("5000");
								btnSeven.setBounds(40, 172, 145, 32);
								panel_1.add(btnSeven);
								
								btnFour = new JButton("1000");
								btnFour.setBounds(260, 66, 145, 32);
								panel_1.add(btnFour);
								
								btnSix = new JButton("2500");
								btnSix.setBounds(260, 119, 145, 32);
								panel_1.add(btnSix);
								
								btnEight = new JButton("10000");
								btnEight.setBounds(260, 172, 145, 32);
								panel_1.add(btnEight);
								
								panel_2 = new JPanel();
								panel_2.setBackground(new Color(0, 0, 0));
								panel_2.setBounds(0, 93, 454, 105);
								panel.add(panel_2);
								panel_2.setLayout(null);
								
								printMessage = new JLabel("");
								printMessage.setForeground(new Color(255, 255, 255));
								printMessage.setHorizontalAlignment(SwingConstants.CENTER);
								printMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
								printMessage.setBounds(86, 11, 279, 68);
								panel_2.add(printMessage);
		
		
		return panel;
	}

	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
		
		try {
			int amt = Integer.parseInt(button.getText());
			String pinText = JOptionPane.showInputDialog(panel, "Enter your pin: ","Confirm", JOptionPane.WARNING_MESSAGE);
			int pin = Integer.parseInt(pinText);
			
			String message = Authentication.withDrawAmount(amt, pin);
			
			panel_1.setVisible(false);
			panel_2.setVisible(true);
			printMessage.setText(message);
		}
		catch(NumberFormatException exp) {
			System.out.print(exp);
		}
		
	}

}
