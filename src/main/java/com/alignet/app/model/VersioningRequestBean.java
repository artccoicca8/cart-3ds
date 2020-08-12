package com.alignet.app.model;

import java.io.Serializable;

public class VersioningRequestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1194578714765618726L;
	private String acquirerMerchantID;
	private String acctNumber;
	private String transbank;
	private String urlEnviroment;
	private String method;
	private String body;
	private String parks;
	private String versioningDataJsonParse;
	private String inputVersioning;
	private String keyText;
	private String threedsServerTransId;

	
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

	public String isTransbank() {
		return transbank;
	}

	public void setTransbank(String transbank) {
		this.transbank = transbank;
	}

	public String getUrlEnviroment() {
		return urlEnviroment;
	}

	public void setUrlEnviroment(String urlEnviroment) {
		this.urlEnviroment = urlEnviroment;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getVersioningDataJsonParse() {
		return versioningDataJsonParse;
	}

	public void setVersioningDataJsonParse(String versioningDataJsonParse) {
		this.versioningDataJsonParse = versioningDataJsonParse;
	}

	public String getInputVersioning() {
		return inputVersioning;
	}

	public void setInputVersioning(String inputVersioning) {
		this.inputVersioning = inputVersioning;
	}

	public String getKeyText() {
		return keyText;
	}

	public void setKeyText(String keyText) {
		this.keyText = keyText;
	}

	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTransbank() {
		return transbank;
	}

	public String getParks() {
		return parks;
	}

	public void setParks(String parks) {
		this.parks = parks;
	}

	
	public String getThreedsServerTransId() {
		return threedsServerTransId;
	}

	public void setThreedsServerTransId(String threedsServerTransId) {
		this.threedsServerTransId = threedsServerTransId;
	}

	@Override
	public String toString() {
		return "VersioningRequestBean [acquirerMerchantID=" + acquirerMerchantID + ", acctNumber=" + acctNumber
				+ ", transbank=" + transbank + ", urlEnviroment=" + urlEnviroment + ", method=" + method + ", body="
				+ body + ", parks=" + parks + ", versioningDataJsonParse=" + versioningDataJsonParse
				+ ", inputVersioning=" + inputVersioning + ", keyText=" + keyText + ", threedsServerTransId="
				+ threedsServerTransId + "]";
	}

	 

 
 

	
	
}
