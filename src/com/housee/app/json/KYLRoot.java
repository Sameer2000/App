package com.housee.app.json;

public class KYLRoot {

	private Session session;
	private KYLForm request = new KYLForm();
    private boolean isErrFlag;
    private String errMessage;

    public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public KYLForm getRequest() {
		return request;
	}
	public void setRequest(KYLForm request) {
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
