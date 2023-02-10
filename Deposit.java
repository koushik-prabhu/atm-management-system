package com_hibernate.com_hibernate_ATM;

//Imports >>
import java.awt.EventQueue;



import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.hibernate.Session;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




//Custom exception class to throw exceptions while deposit
class DepositException extends Exception{
	String msg;
	DepositException(String msg){
		super(msg);
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "DepositException [msg= " + msg + "]";
	}
}



public class Deposit {

	private static JPanel panel;	//main panel
	private static JLabel errorMsg;		//area to convey messages to the user
	public static JTextField enterAmount;	//textfield to enter the amount to be deposited
	private static JButton btnProceed;
	private static JPanel panel_1;
	private static JPanel panel_2;
	private static JLabel printMessage;		//message area to show the result of the operation



	/**
	 * @wbp.parser.entryPoint
	 */
	
	/* method which contains all code of deposit ui and backend
	this method returns a JPanel which then wilol be be added to the mail panel*/
	public static JPanel getDeposit() {
		
		panel = new JPanel();	//main panel
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(26, 11, 443, 290);
		panel.setLayout(null);

		/* when user clicks proceed button,
		 * 1. Popup appears to enter the pin of the user
		 * 2. on successful validation, database updated
		 * 3. even if transaction fails, proper message is conveyed to the user
		 */
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(23, 80, 398, 190);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
				
		JLabel lblNewLabel_1 = new JLabel("Enter the amount");
		lblNewLabel_1.setBounds(128, 23, 138, 30);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
				
		enterAmount = new JTextField();
		enterAmount.setBounds(70, 64, 252, 32);
		panel_1.add(enterAmount);
		enterAmount.addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {	
			errorMsg.setText("");
		}
		});
		
		
		enterAmount.setColumns(10);
				
		btnProceed = new JButton("Proceed");
		btnProceed.setBounds(147, 113, 96, 30);
		panel_1.add(btnProceed);
				
		errorMsg = new JLabel("");
		errorMsg.setBounds(70, 165, 252, 14);
		panel_1.add(errorMsg);
		errorMsg.setForeground(new Color(255, 0, 0));
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
				
		JLabel lblNewLabel = new JLabel("Cash Deposit");
		lblNewLabel.setBounds(114, 24, 208, 44);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
		panel_2 = new JPanel();
		panel_2.setVisible(false);
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(0, 90, 443, 141);
		panel.add(panel_2);
		panel_2.setLayout(null);
				
		printMessage = new JLabel("");
		printMessage.setForeground(new Color(255, 255, 255));
		printMessage.setHorizontalAlignment(SwingConstants.CENTER);
		printMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		printMessage.setBounds(10, 24, 423, 80);
		panel_2.add(printMessage);
				
		//onClick of proceed button:
		btnProceed.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
									
			try {	
				/*get the value from the textField and parse to integer
				this might generate numberFormatException if user enter alphanumeric value or null
				*/
				int amt = Integer.parseInt(enterAmount.getText());
														
				//if less than minimum amount is added then print valid message:
				if(amt < 100) {
					errorMsg.setText("Amount has to be greater than 100!");	
					throw new DepositException("Minimum amount to be deposited is 100");
				}
				//if amount added is greater than limit then:
				if(amt > 49000) {
					errorMsg.setText("Amount has to be lesser than 20000!");
					throw new DepositException("Deposit limit is 20000! Please go the bank for higher amount");
				}
							
				//If valid deposit amount is entered then
									
				Session session = Connection.getSessionFactoryObject().openSession();
							
				//Fetch details of current user
				Account account = (Account) session.get(Account.class, HomePage.currentUser);
							
				//Ask user to confirm deposit using atm pin
				String pinText = JOptionPane.showInputDialog(panel, "Enter your pin: ","Confirm", JOptionPane.WARNING_MESSAGE);
				int pin = 0;
							
				//if user does not click on cancel or x button of popup:
				if(pinText != null) {
					pin = Integer.parseInt(pinText);
							
					panel_1.setVisible(false);
							
					/*create a new transaction object
					Two results might be generated here
					*1. entered correct pin = transaction completed
					*2. entered wrong pin   = transaction failedddd */
					Transaction newTransaction = new Transaction();
					Account acc = new Account();
					acc.setAccNo(HomePage.currentUser);
							
					//In both cases, these fields are common
					newTransaction.setTransaction_type("deposit");
					newTransaction.setTransaction_date((LocalDate.now().toString()));
					newTransaction.setTransaction_time(LocalTime.now() + "");
					newTransaction.setTransaction_mode("atm-machine-456532");
					newTransaction.setTransaction_amount(amt);
					newTransaction.setAccount(acc);
					session.beginTransaction();
					//check if entered pin matches the record in the database
					//if record found then:
					if(pin == account.getAccPin()) {
								
						//if entered pin is correct then status is completed and
						//deposited amount is added to the total balance
						newTransaction.setTransaction_status("completed");
						newTransaction.setBalance(account.getAmount() + amt);
						session.save(newTransaction);
								
						//update the amount value of account table 
						account.setAmount(account.getAmount() + amt);
						session.saveOrUpdate(account);
								
						printMessage.setText("Amount deposited successfully!");
						panel_2.setVisible(true);
					}
					else {
								
						//if wrong pin entered by the user then:
						//set the status to failed and store in the transaction table
						newTransaction.setTransaction_status("failed");
						newTransaction.setBalance(account.getAmount());	
						session.save(newTransaction);
						//print error message on the atm screen
						panel_2.setVisible(true);
						printMessage.setText("You have entered invalid pin!");
						throw new DepositException("Invalid pin!");
					}
					//AtLast commit all the changes
					session.getTransaction().commit();
				}
			}
			
			//string to int conversion errors
			catch(NumberFormatException exp) {
				System.out.println(exp + "\nInvalid input! Cannot convert to integer.");
							
				//if pressed proceed without entering anything
				if(enterAmount.getText().equals(""))
					errorMsg.setText("Please enter an amount!");
				else	//if entered value is alphanumeric
					errorMsg.setText("Invalid amount! Please enter numbers only");
			}
						//if entered value of deposit is lesser or greater than limit
			catch(DepositException exp) {
				System.out.println(exp);
			}	
		}
		});

		return panel;
	}

}
