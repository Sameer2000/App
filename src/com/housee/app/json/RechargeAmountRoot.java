package com.housee.app.json;

public class RechargeAmountRoot {
	private Session session;
	private RechargeAmountForm request = new RechargeAmountForm();
    private boolean isErrFlag;
    private String errMessage;
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public RechargeAmountForm getRequest() {
		return request;
	}
	public void setRequest(RechargeAmountForm request) {
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
