package com.housee.app.json;

import java.util.ArrayList;
import java.util.List;

import com.housee.app.model.KYLRequest;

public class KYLList {

	private List<KYLRequest> kylList = new ArrayList<KYLRequest>();
	private double kylFee = 0;
	public List<KYLRequest> getKylList() {
		return kylList;
	}
	public void setKylList(List<KYLRequest> kylList) {
		this.kylList = kylList;
	}
	public double getKylFee() {
		return kylFee;
	}
	public void setKylFee(double kylFee) {
		this.kylFee = kylFee;
	}
	
	
}
