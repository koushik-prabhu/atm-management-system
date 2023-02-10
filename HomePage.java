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
import javax.swing.UIManager;
import java.awt.Cursor;

public class HomePage{
	
	JFrame frame;	//main frame
	JPanel panel_welcome;	//welcome panel
	JPanel panel_main;	//panel for all operations
	JPanel panel;
	
	public static long currentUser;

	/**
	 * Launch the application.
	 * Entry point
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage(10000000001l);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * When user logs in, his atm number acts as a session variable throughout the application
	 */
	public HomePage(long atmNo) {
		currentUser = atmNo;
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
		frame.setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(72, 11, 489, 312);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_main = new JPanel();
		panel_main.setBounds(0, 0, 489, 312);
		panel.add(panel_main);
		panel_main.setBackground(new Color(0, 0, 0));
		panel_main.setLayout(null);
		panel_main.setVisible(false);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(38, 36, 158, 46);
		panel_main.add(btnDeposit);
		
		/*On click of deposit option
		 * 1. replace deposit panel with home panel
		 * 2. get the amount from the user and save into the database
		 */
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_main.setVisible(false);
				panel_welcome.setVisible(false);
				panel.add(Deposit.getDeposit());
			}
		});
		
		
		
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(279, 36, 158, 46);
		panel_main.add(btnWithdraw);
		
		/*On click of withdraw option
		 * 1. replace withdraw panel with home panel
		 * 2. get the pin from the user and perform withdrawal operation
		 */
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_main.setVisible(false);
				panel_welcome.setVisible(false);
				panel.add(new Withdraw().withDraw());	
			}
		});
		
		
		
		
		JButton btnBalanceEnquiry = new JButton("Balance Enquiry");
		btnBalanceEnquiry.setBounds(38, 118, 158, 46);
		panel_main.add(btnBalanceEnquiry);
		
		/*On click of balanceEnquiry option
		 * 1. replace balanceQnquiry panel with home panel
		 * 2. get the pin from the user and fetch the balance
		 */
		btnBalanceEnquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_main.setVisible(false);
				panel_welcome.setVisible(false);
				panel.add(BalanceEnquiry.getBalanceEnquiry());	
			}
		});
	
		
		
		
		JButton btnChangePin = new JButton("Change Pin");
		btnChangePin.setBounds(38, 200, 158, 46);
		panel_main.add(btnChangePin);
		
		/*On click of change pin option
		 * 1. replace change pin panel with home panel
		 * 2. get the pin, new pin from the user, and validate
		 */
		btnChangePin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_main.setVisible(false);
				panel_welcome.setVisible(false);
				panel.add(ChangePin.change());	
			}
		});
	
		
		JButton btnFastCash = new JButton("Fast Cash");
		btnFastCash.setBounds(279, 200, 158, 46);
		panel_main.add(btnFastCash);
		
		/*On click of balanceEnquiry option
		 * 1. replace fast cash panel with home panel
		 * 2. get the pin from the user and perform withdraw operation
		 */
		btnFastCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_main.setVisible(false);
				panel_welcome.setVisible(false);
				panel.add(new FastCash().fastCash());
				
			}
		});
		
		
		
		
		JButton btnMiniStatement = new JButton("Mini Statement");
		btnMiniStatement.setBounds(279, 118, 158, 46);
		panel_main.add(btnMiniStatement);
		
		/*On click of mini statement option
		 * 1. replace mini statement panel with home panel
		 * 2. get the pin from the user and fetch transaction details
		 */
		btnMiniStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_main.setVisible(false);
				panel_welcome.setVisible(false);
				panel.add(MiniStatement.miniStatement());
			}
		});
		
		
	
		panel_welcome = new JPanel();
		panel_welcome.setBounds(71, 52, 353, 211);
		panel.add(panel_welcome);
		panel_welcome.setBackground(new Color(0, 0, 0));
		panel_welcome.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(35, 32, 290, 54);
		panel_welcome.add(lblNewLabel_1);
		
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
		
		JLabel lblNewLabel_1_1 = new JLabel("Union Bank ATM");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(35, 82, 290, 54);
		panel_welcome.add(lblNewLabel_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 11, 609, 519);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 609, 519);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\koush\\Downloads\\2cgc_7ssh_211223.jpg"));
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnNewButton.setBorder(null);
		btnNewButton.setForeground(new Color(248, 199, 199));
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 18));
		btnNewButton.setBounds(358, 382, 39, 33);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				panel_main.setVisible(true);
				panel_welcome.setVisible(false);
				panel.removeAll();
				panel.add(panel_main);
				
			}
		});
		btnNewButton.setBackground(new Color(255, 0, 0));
	}
	

}
