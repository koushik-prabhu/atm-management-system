package com_hibernate.com_hibernate_ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Login implements FocusListener{

	
	//Swing components
	private JFrame frame;
	private JTextField atmPin;	//atm pin textfield
	private JTextField atmNumber;	//atm number textfield
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnContinue;	//continue button to homepage
	private JButton btnNewUser;	//signup button
	private JButton btnClear;	//clear button to make fields null (atm number and pin textfield)
	private JLabel errorMsg;	//span element to display error messages regarding login

	
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setAutoRequestFocus(false);
		frame.setVisible(true);
		frame.setBounds(100, 100, 754, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 327, 354);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(UIManager.getColor("Button.foreground"));
		lblNewLabel.setIcon(new ImageIcon("M:\\logo001.png"));
		lblNewLabel.setBounds(43, 0, 274, 332);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(445, 7, 167, 53);
		frame.getContentPane().add(lblNewLabel_1);
		
		atmPin = new JTextField();
		atmPin.addFocusListener(this);
			
	
		atmPin.setForeground(new Color(0, 0, 0));
		atmPin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		atmPin.setBounds(412, 157, 262, 30);
		frame.getContentPane().add(atmPin);
		atmPin.setColumns(10);

		
		atmNumber = new JTextField();
		atmNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		atmNumber.setForeground(new Color(0, 0, 0));
		atmNumber.addFocusListener(this);
		
	
		atmNumber.setColumns(10);
		atmNumber.setBounds(412, 96, 262, 30);
		frame.getContentPane().add(atmNumber);
		
		lblNewLabel_2 = new JLabel("ATM Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(412, 70, 98, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("ATM Pin");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(412, 132, 98, 27);
		frame.getContentPane().add(lblNewLabel_3);
		
		btnContinue = new JButton("Continue");
		
		
		/*Action to be performed when continue button pressed 
		 * 1. check for null and validation 
		 * 2. check if credentials exists in the databse
		 */
		
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				final long atmNum;
				int atmMpin = 0;

				//Value entered by the user might be string or null
				try {
					atmNum = Long.parseLong(atmNumber.getText());
					atmMpin = Integer.parseInt(atmPin.getText());
					
					if(Authentication.loginValidation(atmNum, atmMpin) == true) {	//Check if login data present in database
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									new HomePage(atmNum);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});

						frame.dispose();
					}
					else {

						errorMsg.setText("No record found! please signup");	//if no data found, print error message
					}
				}
				
				catch(NumberFormatException msg) {
					errorMsg.setText("*Please enter valid credentials*");
				}
				
				
			}
		});

		btnContinue.setBounds(412, 235, 116, 37);
		frame.getContentPane().add(btnContinue);
		
		btnNewUser = new JButton("New user? Create one.");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Thread t = new Thread(new SignUp());
				t.start();	
				frame.dispose();
		
			}
		});
		btnNewUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewUser.setBounds(412, 288, 262, 37);
		frame.getContentPane().add(btnNewUser);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				atmNumber.setText("");
				atmPin.setText("");
				
			}
		});
		btnClear.setBounds(550, 235, 116, 37);
		frame.getContentPane().add(btnClear);
		
		errorMsg = new JLabel("");
		errorMsg.setForeground(new Color(255, 0, 0));
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setBounds(417, 198, 249, 26);
		frame.getContentPane().add(errorMsg);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void focusGained(FocusEvent e) {
		errorMsg.setText("");
		
	}

	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
}
