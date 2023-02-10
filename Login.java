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
	private JTextField atmNumber;	//ATM number text field
	private JLabel lblNewLabel_2;
	private JButton btnContinue;	//continue button to home page
	private JButton btnNewUser;	//sign up button
	private JButton btnClear;	//clear button to make fields null (ATM number and pin text field)
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
		lblNewLabel_1.setBounds(447, 24, 167, 53);
		frame.getContentPane().add(lblNewLabel_1);

		
		atmNumber = new JTextField();
		atmNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		atmNumber.setForeground(new Color(0, 0, 0));
		atmNumber.addFocusListener(this);
		
	
		atmNumber.setColumns(10);
		atmNumber.setBounds(412, 133, 262, 37);
		frame.getContentPane().add(atmNumber);
		
		lblNewLabel_2 = new JLabel("ATM Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(412, 101, 98, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnContinue = new JButton("Continue");
		
		
		/*Action to be performed when continue button pressed 
		 * 1. check for null and validation 
		 * 2. check if credentials exists in the database
		 */
		
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				final long atmNum;
				int atmMpin = 0;

				/*code inside try block might generate number format 
				* exception while converting string to int
				*/
				try {
					//get the value entered by the user and parse it to integer
					//if the entered value contains null or alphanumeric value, throw NumberFormatException
					atmNum = Long.parseLong(atmNumber.getText());
					
					//Check if login data present in database
					if(Authentication.loginValidation(atmNum, atmMpin) == true) {	
						
						/*If record found then open new frame
						 * and close the current frame (Login)
						 */
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									new HomePage(atmNum);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});

						//Delete current frame (Login)
						frame.dispose();
					}
					else {	//if ATM number not found in the database:
						errorMsg.setText("No record found! please signup");	//if no data found, print error message
					}
				}
				catch(NumberFormatException msg) {
					errorMsg.setText("*Please enter valid credentials*");
				}
				
				
			}
		});

		btnContinue.setBounds(412, 181, 116, 37);
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
		btnNewUser.setBounds(412, 260, 262, 37);
		frame.getContentPane().add(btnNewUser);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				atmNumber.setText("");
				
			}
		});
		btnClear.setBounds(558, 181, 116, 37);
		frame.getContentPane().add(btnClear);
		
		errorMsg = new JLabel("");
		errorMsg.setForeground(new Color(255, 0, 0));
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setBounds(422, 223, 249, 26);
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

	//when cursor floats on the text field then remove the error message
	public void focusGained(FocusEvent e) {
		errorMsg.setText("");
		
	}

	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
}
