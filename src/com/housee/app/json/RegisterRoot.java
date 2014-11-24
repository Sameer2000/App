package com.housee.app.json;

public class RegisterRoot {
	
	private Session session;
	private UserRegistrationForm request = new UserRegistrationForm();
    private boolean isErrFlag;
    private String errMessage;

	public boolean isErrFlag() {
		return isErrFlag;
	}
	public void setErrFlag(boolean isErrFlag) {
		this.isErrFlag = isErrFlag;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public UserRegistrationForm getRequest() {
		return request;
	}
	public void setRequest(UserRegistrationForm request) {
		this.request = request;
	}

	
}
