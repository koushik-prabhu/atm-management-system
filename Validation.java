package com_hibernate.com_hibernate_ATM;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class Validation {
	
	private static boolean flag = true;		//if any field of signup is not correct make it false

	
	//checks if name is empty or not
	public static void validateFullName(String name, JTextField field) {
		
		if(name.equals("") || name == null) {
			field.setBorder(BorderFactory.createLineBorder(Color.red));
			flag = false;
		}	
		else
			flag = true;	
	}
	
	
	
	//checks if adharNo is empty / only number / as per standard digits
	public static long validateAdharNo(String adhar, JTextField field) {
		
		long value = 0l;
		try {
			//if adhar number digits are lesser than required:
			if(adhar.length() != 12)
				throw new NumberFormatException("adhar number must contain 12 digits!");
			
			//if user has entered alphanumeric value
			value = Long.parseLong(adhar);
			flag = true;
		}
		catch(NumberFormatException msg) {
			/*on error, change the color of the border of adhar text field
			 * set flag to false
			 */
			field.setBorder(BorderFactory.createLineBorder(Color.red));	
			flag = false;
		}
		return value;	
	}
	
	
	
	
	//checks if pan no is empty / as per standard length
	public static void validatePanNo(String pan, JTextField field) {
		if(pan.equals("") || pan.length() != 10) {
			field.setBorder(BorderFactory.createLineBorder(Color.red));
			flag = false;
		}
		else
			flag = true;
	}
	
	
	
	
	public static void validateGender(String gender) {
		
		if(gender.equals("")) {
			flag = false;
		}
		else
			flag = true;
		
	}
	
	//check if the phone number is 10 digit or not / empty or not
	public static long validatePhoneNo(String phone, JTextField field) {
		
		long value = 0l;
		
		try {
			
			if(phone.length() != 10)
				throw new NumberFormatException("Phone number must be 10 digit only!");
			//if user enters alpha numeric value, throw error
			value = Long.parseLong(phone);
			flag = true;
		}
		catch(NumberFormatException msg) {
			field.setBorder(BorderFactory.createLineBorder(Color.red));
			flag = false;
		}	
		return value;
	}
	
	
	
	
	public static void validateDob(String dob, JTextField field) {
		
		
		if(dob.equals("")) {
			field.setBorder(BorderFactory.createLineBorder(Color.red));
			flag = false;
		}	
		else
			flag = true;
	}
	
	
	
	public static void validateAddress(String address, JTextField field) {
		if(address.equals("")) {
			field.setBorder(BorderFactory.createLineBorder(Color.red));
			flag = false;
		}	
		else
			flag = true;
	}
	
	
	
	public static int validatePincode(String pincode, JTextField field) {
		int value = 0;
		
		try {
			value = Integer.parseInt(pincode);
			flag = true;
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
