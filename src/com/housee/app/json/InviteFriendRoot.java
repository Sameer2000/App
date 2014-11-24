package com.housee.app.json;

public class InviteFriendRoot {

	private Session session;
	private InviteFriendForm request = new InviteFriendForm();
    private boolean isErrFlag;
    private String errMessage;
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public InviteFriendForm getRequest() {
		return request;
	}
	public void setRequest(InviteFriendForm request) {
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
