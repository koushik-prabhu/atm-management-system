package com_hibernate.com_hibernate_ATM;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transaction_id;
	private String transaction_type;
	private String transaction_date;
	private String transaction_time;
	private String transaction_status;
	private String transaction_mode;
	private int balance;
	private int transaction_amount;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
		public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getTransaction_time() {
		return transaction_time;
	}
	public void setTransaction_time(String transaction_time) {
		this.transaction_time = transaction_time;
	}
	public String getTransaction_status() {
		return transaction_status;
	}
	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}
	public String getTransaction_mode() {
		return transaction_mode;
	}
	public void setTransaction_mode(String transaction_mode) {
		this.transaction_mode = transaction_mode;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(int transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", transaction_type=" + transaction_type
				+ ", transaction_date=" + transaction_date + ", transaction_time=" + transaction_time
				+ ", transaction_status=" + transaction_status + ", transaction_mode=" + transaction_mode + ", balance="
				+ balance + ", transaction_amount=" + transaction_amount + ", account=" + account + "]";
	}
	
	
	
	
	
	
	
}
