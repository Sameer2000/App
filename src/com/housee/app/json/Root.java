package com.housee.app.json;

public class Root {

	private Session session;
    private boolean isErrFlag;
    private String errMessage;
    private UserInfo request;
    
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public UserInfo getRequest() {
		return request;
	}

	public void setRequest(UserInfo request) {
		this.request = request;
	}

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

	
}
