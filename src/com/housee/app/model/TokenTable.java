package com.housee.app.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TokenTable")
public class TokenTable {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	private String Type;
	
	private String Token;
	
	private Timestamp Time;
	
	private String userName;
	
	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	private int Hit;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}


	public Timestamp getTime() {
		return Time;
	}

	public void setTime(Timestamp time) {
		Time = time;
	}

	public int getHit() {
		return Hit;
	}

	public void setHit(int hit) {
		Hit = hit;
	}
	
	
}
