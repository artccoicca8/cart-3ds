package com.alignet.app.model;

import java.io.Serializable;

public class MasterCard implements Serializable {

	/**
	 * 
	 */

	private String threeDSServerRefNumber;
	private String threeDSServerOperatorID;
	private String threeDSServerTransID;
	private String messageType;
	private String messageVersion;

	public MasterCard() {
	}
	
	public MasterCard(String threeDSServerRefNumber, String threeDSServerOperatorID, String threeDSServerTransID,
			String messageType, String messageVersion) {
		super();
		this.threeDSServerRefNumber = threeDSServerRefNumber;
		this.threeDSServerOperatorID = threeDSServerOperatorID;
		this.threeDSServerTransID = threeDSServerTransID;
		this.messageType = messageType;
		this.messageVersion = messageVersion;
	}



	public String getThreeDSServerRefNumber() {
		return threeDSServerRefNumber;
	}

	public void setThreeDSServerRefNumber(String threeDSServerRefNumber) {
		this.threeDSServerRefNumber = threeDSServerRefNumber;
	}

	public String getThreeDSServerOperatorID() {
		return threeDSServerOperatorID;
	}

	public void setThreeDSServerOperatorID(String threeDSServerOperatorID) {
		this.threeDSServerOperatorID = threeDSServerOperatorID;
	}

	public String getThreeDSServerTransID() {
		return threeDSServerTransID;
	}

	public void setThreeDSServerTransID(String threeDSServerTransID) {
		this.threeDSServerTransID = threeDSServerTransID;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageVersion() {
		return messageVersion;
	}

	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}

	private static final long serialVersionUID = -5592760421077705712L;

}
