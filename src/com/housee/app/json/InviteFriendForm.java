package com.housee.app.json;

import java.util.ArrayList;
import java.util.List;

public class InviteFriendForm {

	private String userId;
	private List<Friend> frnddata = new ArrayList<Friend>();

	public List<Friend> getFrnddata() {
		return frnddata;
	}

	public void setFrnddata(List<Friend> frnddata) {
		this.frnddata = frnddata;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}

