package com.alignet.app.model;

import java.io.Serializable;

public class VersioningObject implements Serializable{
 
	
	private String acquirerMerchantID;
	private String acctNumber;
	
	public VersioningObject() {
	}
	
	public VersioningObject(String acquirerMerchantID, String acctNumber) {
		this.acquirerMerchantID = acquirerMerchantID;
		this.acctNumber = acctNumber;
	}



	public String getAcquirerMerchantID() {
		return acquirerMerchantID;
	}
	public void setAcquirerMerchantID(String acquirerMerchantID) {
		this.acquirerMerchantID = acquirerMerchantID;
	}
	public String getAcctNumber() {
		return acctNumber;
	}
	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}
	
	
	private static final long serialVersionUID = 5871680675641024477L;
}
