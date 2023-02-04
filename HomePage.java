package com_hibernate.com_hibernate_ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage{
	
	JFrame frame;
	JPanel panel_welcome;
	JPanel panel_main;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
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
	public HomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 640, 567);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(72, 11, 489, 312);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_welcome = new JPanel();
		panel_welcome.setBackground(new Color(0, 0, 0));
		panel_welcome.setBounds(70, 50, 353, 211);
		panel.add(panel_welcome);
		panel_welcome.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(35, 32, 290, 54);
		panel_welcome.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Union Bank ATM");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(35, 82, 290, 54);
		panel_welcome.add(lblNewLabel_1_1);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_welcome.setVisible(false);
				panel_main.setVisible(true);
				
			}
		});
		btnContinue.setBackground(new Color(128, 128, 128));
		btnContinue.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnContinue.setBounds(121, 154, 114, 32);
		panel_welcome.add(btnContinue);
		
		panel_main = new JPanel();
		panel_main.setBackground(new Color(0, 0, 0));
		panel_main.setBounds(10, 11, 469, 290);
		panel.add(panel_main);
		panel_main.setLayout(null);
		panel_main.setVisible(false);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(38, 36, 158, 46);
		panel_main.add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(279, 36, 158, 46);
		panel_main.add(btnWithdraw);
		
		JButton btnBalanceEnquiry = new JButton("Balance Enquiry");
		btnBalanceEnquiry.setBounds(38, 118, 158, 46);
		panel_main.add(btnBalanceEnquiry);
		
		JButton btnChangePin = new JButton("Change Pin");
		btnChangePin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChangePin.setBounds(38, 200, 158, 46);
		panel_main.add(btnChangePin);
		
		JButton btnNewButton_2_1_1 = new JButton("Fast Cash");
		btnNewButton_2_1_1.setBounds(279, 200, 158, 46);
		panel_main.add(btnNewButton_2_1_1);
		
		JButton btnMiniStatement = new JButton("Mini Statement");
		btnMiniStatement.setBounds(279, 118, 158, 46);
		panel_main.add(btnMiniStatement);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\koush\\Downloads\\2cgc_7ssh_211223.jpg"));
		lblNewLabel.setBounds(0, 11, 609, 519);
		frame.getContentPane().add(lblNewLabel);
	}
}