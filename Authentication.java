package com_hibernate.com_hibernate_ATM;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JLabel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Authentication {
	
	public static boolean loginValidation(long accNo, int pin) {
		
		SessionFactory sf = Connection.getSessionFactoryObject();
		Session session = sf.openSession();
		Account account = session.get(Account.class, accNo);
		return (account == null)? false: true;	
	}
	
	
	public static String changePin(int cPin, int nPin, int cNPin) {
		String message = "";
		
		//open session
		Session session = Connection.getSessionFactoryObject().openSession();
		Account account = session.get(Account.class, HomePage.currentUser);		//get the pin from the user
		

		message = (account != null)? "Pin changed successfully!" : "Incorrect pin!";
		message = (nPin != cNPin)? "pin mismatched!" : message;
		
		if(message.equals("Pin changed successfully!")) {
			account.setAccPin(nPin);
			session.beginTransaction();
			session.saveOrUpdate(account);
			session.getTransaction().commit();
		}
		return message;
		
	}
	
	
	/*All the authentication regarding withdrawal activity goes here:
	 * Functions:
	 * 1. check if amount entered is < or > total available balance
	 * 2. check if pin entered by the user matches the record
	 * 3. update database <even if transaction fails for insufficient amount>
	 */
	public static String withDrawAmount(int amt, int pin) {
		String message = "";
		
		Session session = Connection.getSessionFactoryObject().openSession();
		Account account = (Account) session.get(Account.class, HomePage.currentUser);	//fetch the details of the current user
		
		//if account found with the associated account number then:
		if(account != null) {
			//if pin matches the record then:
			if(pin == account.getAccPin()) {
				Transaction newTransaction = new Transaction();
				session.beginTransaction();
				//if total balance is greater than withdrawal amount then only allow to withdraw:
				if(account.getAmount() > (amt + 1000)) {

						newTransaction.setTransaction_type("withdraw");
						newTransaction.setTransaction_date((LocalDate.now().toString()));
						newTransaction.setTransaction_time(LocalTime.now() + "");
						newTransaction.setTransaction_mode("atm-machine-456532");
						newTransaction.setTransaction_amount(amt);
						newTransaction.setAccount(account);
						account.setAmount(account.getAmount() - amt);
						newTransaction.setTransaction_status("completed");
						newTransaction.setBalance(account.getAmount() - amt);
						session.save(newTransaction);
						
						message = "transaction completed! please collect your cash.";

				}
				else {	//when amount entered exceeds the total balance
					newTransaction.setTransaction_status("failed");
					newTransaction.setBalance(account.getAmount());
					session.save(newTransaction);
					message = "insufficient balance!";
					
					//<note: database is still updated>
				}
				session.getTransaction().commit();
			}
			else {
				message = "incorrect pin! transaction failed!";
			}
		}
		
		return message;
	}
}
