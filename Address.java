package com_hibernate.com_hibernate_ATM;
import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
	
    @Id
    @Column(name = "id")
    private long id;
	
    @Column(name = "addressline")
	private String address;
	private int pincode;
	private String country;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		return "Address [id=" + id + ", address=" + address + ", pincode=" + pincode + ", country=" + country + "]";
	}

}
