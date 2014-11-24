package com.housee.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.housee.app.henum.UserType;

@Entity
@Table(name="UserProfile")
public class UserProfile {

	@Id
    @GeneratedValue
	private long id;
	
	private String username;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String state;
	
	private String country;
	
	private String zipcode;
	
	private String salutation;
	
	private int age;
	
	private String gender;
	
	private String maritalStatus;
	
	private String occupation;
	
	private String address1;
	
	private String address2;
	
	private String dob;
	
	private String dobtime;
	
	private String dobplace;
	
	private String dobcountry;
	
	private String longitude;
	
	private String latitude;
	
	private boolean active;
	
	private UserType type;
	
	private Date registrationDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	private FavourateTicket favorateTicket;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDobtime() {
		return dobtime;
	}
	public void setDobtime(String dobtime) {
		this.dobtime = dobtime;
	}
	public String getDobplace() {
		return dobplace;
	}
	
	public void setDobplace(String dobplace) {
		this.dobplace = dobplace;
	}
	public String getDobcountry() {
		return dobcountry;
	}
	public void setDobcountry(String dobcountry) {
		this.dobcountry = dobcountry;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public FavourateTicket getFavorateTicket() {
		return favorateTicket;
	}
	public void setFavorateTicket(FavourateTicket favorateTicket) {
		this.favorateTicket = favorateTicket;
	}
	
}
