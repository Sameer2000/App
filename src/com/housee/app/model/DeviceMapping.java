package com.housee.app.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DeviceMapping")
public class DeviceMapping {

	@Id
    @GeneratedValue
	private Long id;
	
	private String username;

	/* Android & iOS */
	private String deviceType;
	
	private Timestamp lastSent;
	
	private String deviceToken;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Timestamp getLastSent() {
		return lastSent;
	}

	public void setLastSent(Timestamp lastSent) {
		this.lastSent = lastSent;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

}
