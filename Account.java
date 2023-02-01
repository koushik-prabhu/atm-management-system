package com_hibernate.com_hibernate_ATM;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	private long accNo;
	private int accPin;
	
	public int getAccPin() {
		return accPin;
	}
	public void setAccPin(int accPin) {
		this.accPin = accPin;
	}
	private String fullName;
	private long adharNo;
	private String panNo;
	private String gender;
	private long phoneNo;
	
	private String address;
	private String dob;
	private int pincode;
	private String country;
	public long getAccNo() {
		return accNo;
	}
	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public long getAdharNo() {
		return adharNo;
	}
	public void setAdharNo(long adharNo) {
		this.adharNo = adharNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", fullName=" + fullName + ", adharNo=" + adharNo + ", panNo=" + panNo
				+ ", gender=" + gender + ", phoneNo=" + phoneNo + ", address=" + address + ", dob=" + dob + ", pincode="
				+ pincode + ", country=" + country + "]";
	}

}
