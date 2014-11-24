package com.housee.app.json;

public class GameRoot {

	private Session session;
	private GameForm request = new GameForm();
    private boolean isErrFlag;
    private String errMessage;
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public GameForm getRequest() {
		return request;
	}
	public void setRequest(GameForm request) {
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
