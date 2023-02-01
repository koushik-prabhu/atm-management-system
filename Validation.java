package com_hibernate.com_hibernate_ATM;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class Validation {
	
	private static boolean flag = true;

	public static void validateFullName(String name, JTextField field) {
		
		if(name.equals("") || name == null) {
			field.setBorder(BorderFactory.createLineBorder(Color.red));
			flag = false;
		}		
	}
	
	public static long validateAdharNo(String adhar, JTextField field) {
		
		long value = 0l;
		try {
			value = Long.parseLong(adhar);
		}
		catch(NumberFormatException msg) {
			field.setBorder(BorderFactory.createLineBorder(Color.red));
			flag = false;
		}
		
		return value;	
	}
	
	public static void validatePanNo(String pan, JTextField field) {
		if(pan.equals("") || pan == null) {
			field.setBorder(BorderFactory.createLineBorder(Color.red));
			flag = false;
		}	
	}
	public static void validateGender(String gender) {
		
	}
	
	public static long validatePhoneNo(String phone, JTextField field) {
		
		long value = 0l;
		
		try {
			value = Long.parseLong(phone);
		}
		catch(NumberFormatException msg) {
			field.setBorder(BorderFactory.createLineBorder(Color.red));
			flag = false;
		}	
		return value;
	}
	
	
	public static void validateAddress(String address, JTextField field) {
		if(address.equals("") || address == null) {
			field.setBorder(BorderFactory.createLineBorder(Color.red));
			flag = false;
		}	
	}
	public static int validatePincode(String pincode, JTextField field) {
		int value = 0;
		
		try {
			value = Integer.parseInt(pincode);
		}
		catch(NumberFormatException msg) {
			field.setBorder(BorderFactory.createLineBorder(Color.red));
			flag = false;
		}	
		return value;
	}
	public static boolean proceed() {
		return flag;
	}
	
}
