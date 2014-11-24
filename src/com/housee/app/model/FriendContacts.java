package com.housee.app.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="friendcontact")
public class FriendContacts {

	@Id
	@GeneratedValue
	private long id;
	private String username;
	private String friendname;
	private String friendemail;
	private String friendphone;
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
	public String getFriendname() {
		return friendname;
	}
	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}
	public String getFriendemail() {
		return friendemail;
	}
	public void setFriendemail(String friendemail) {
		this.friendemail = friendemail;
	}
	public String getFriendphone() {
		return friendphone;
	}
	public void setFriendphone(String friendphone) {
		this.friendphone = friendphone;
	}
	
	
}

