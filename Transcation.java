package com_hibernate.com_hibernate_ATM;

public class Transcation {
	
	private int transaction_id;
	private String transaction_type;
	private String transcation_date;
	private String transcation_time;
	private String transcation_mode;
	private long account_id;
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
	public String getTranscation_date() {
		return transcation_date;
	}
	public void setTranscation_date(String transcation_date) {
		this.transcation_date = transcation_date;
	}
	public String getTranscation_time() {
		return transcation_time;
	}
	public void setTranscation_time(String transcation_time) {
		this.transcation_time = transcation_time;
	}
	public String getTranscation_mode() {
		return transcation_mode;
	}
	public void setTranscation_mode(String transcation_mode) {
		this.transcation_mode = transcation_mode;
	}
	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	
	
	
	

}
