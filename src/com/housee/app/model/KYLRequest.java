package com.housee.app.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.housee.app.henum.KYLStatus;
import com.housee.app.henum.KYLType;

@Entity
@Table(name="KYLRequest")
public class KYLRequest {

	
	@Id
    @GeneratedValue
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserProfile userProfile;
	
	private KYLType kylType;
	
	private KYLStatus kylStatus;
	
	private Timestamp reqDateTime;
	
	private String reqStrDateTime;
	
	private String timeZone;
	
	@Column(length = 1000)
	private String request;
	
	@Column(length = 1000)
	private String suggestion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public KYLType getKylType() {
		return kylType;
	}

	public void setKylType(KYLType kylType) {
		this.kylType = kylType;
	}

	public KYLStatus getKylStatus() {
		return kylStatus;
	}

	public void setKylStatus(KYLStatus kylStatus) {
		this.kylStatus = kylStatus;
	}

	public Timestamp getReqDateTime() {
		return reqDateTime;
	}

	public void setReqDateTime(Timestamp reqDateTime) {
		this.reqDateTime = reqDateTime;
	}

	public String getReqStrDateTime() {
		return reqStrDateTime;
	}

	public void setReqStrDateTime(String reqStrDateTime) {
		this.reqStrDateTime = reqStrDateTime;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
}
