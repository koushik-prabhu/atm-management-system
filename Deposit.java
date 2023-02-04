package com_hibernate.com_hibernate_ATM;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.hibernate.Session;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class DepositException extends Exception{
	String msg;
	DepositException(String msg){
		super(msg);
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "DepositException [msg=" + msg + "]";
	}
	
}
public class Deposit {

	private static JLabel errorMsg;
	public static JTextField enterAmount;



	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel getDeposit() {
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(26, 11, 414, 277);
		panel.setLayout(null);

		
		JLabel lblNewLabel_1 = new JLabel("Enter the amount");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(141, 87, 138, 30);
		panel.add(lblNewLabel_1);
		
		enterAmount = new JTextField();
		enterAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				errorMsg.setText("");
			}
		});
		enterAmount.setBounds(109, 128, 193, 32);
		panel.add(enterAmount);
		enterAmount.setColumns(10);
		
		JButton btnProceed = new JButton("Proceed");

		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {	
					int amt = Integer.parseInt(enterAmount.getText());
					
					if(amt < 100) {
						errorMsg.setText("Amount has to be greater than 100!");
						throw new DepositException("Minimum amount to be deposited is 100");
					}
					if(amt > 49000) {
						errorMsg.setText("Amount has to be lesser than 20000!");
						throw new DepositException("Deposit limit is 20000! Please go the bank for higher amount");
					}
					
					//If valid deposit amount is entered then
					Session session = Connection.getSessionFactoryObject().openSession();
					
					
					
				}
				catch(NumberFormatException exp) {
					System.out.print("Depsoit amount is not valid\n" + exp);
					
					if(enterAmount.getText().equals(""))
						errorMsg.setText("Please enter an amount!");
					else
						errorMsg.setText("Invalid amount! Please enter numbers only");
				}
				catch(DepositException exp) {
					System.out.print(exp);
					
					
				}
			
				
			}
		});
		btnProceed.setBounds(161, 182, 96, 30);
		panel.add(btnProceed);
		
		JLabel lblNewLabel = new JLabel("Cash Deposit");
		lblNewLabel.setBounds(108, 24, 208, 44);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		errorMsg = new JLabel("");
		errorMsg.setForeground(new Color(255, 0, 0));
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setBounds(81, 235, 252, 14);
		panel.add(errorMsg);
		
		return panel;
	}

}
