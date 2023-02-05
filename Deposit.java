package com_hibernate.com_hibernate_ATM;

import java.awt.EventQueue;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	private static JPanel panel;
	private static JLabel errorMsg;
	public static JTextField enterAmount;



	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel getDeposit() {
		
		panel = new JPanel();
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
					
					//Fetch details of current user
					Account account = (Account) session.get(Account.class, HomePage.currentUser);
					
					//Ask user to confirm deposit using atm pin
					String pinText = JOptionPane.showInputDialog(panel, "Enter your pin: ","Confirm", JOptionPane.WARNING_MESSAGE);
					int pin = Integer.parseInt(pinText);
					
					
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
					newTransaction.setTransaction_mode("deposit-machine-456532");
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
						
						System.out.print("done");
				
					}
					else {
						
						//if wrong pin entered by the user then:
						//set the status to failed and store in the transaction table
						newTransaction.setTransaction_status("failed");
						newTransaction.setBalance(account.getAmount());
						session.save(newTransaction);
						//print error message on the atm screen
						
						enterAmount.setText("");
						errorMsg.setText("Invalid pin");
						System.out.print("Invalid pin entered");
					}
					
					//AtLast commit all the changes
					session.getTransaction().commit();

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
