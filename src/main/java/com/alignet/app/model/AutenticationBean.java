package com.alignet.app.model;

import java.io.Serializable;

public class AutenticationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1897923080735040231L;
	private String messageType;
	private String acctNumber;
	private String cardExpiryDate;
	private String deviceChannel;
	private String messageCategory;
	private String messageVersion;
	private String threeDSRequestorAuthenticationInd;
	private String acquirerBIN;
	private String acquirerMerchantID;
	private String cardholderName;
	private String purchaseAmount;
	private String purchaseCurrency;
	private String purchaseExponent;
	private String purchaseDate;
	private String browserAcceptHeader;
	private String browserIP;
	private String browserJavaEnabled;
	private String browserLanguage;
	private String browserColorDepth;
	private String browserScreenHeight;
	private String browserScreenWidth;
	private String browserTZ;
	private String browserUserAgent;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}

	public String getCardExpiryDate() {
		return cardExpiryDate;
	}

	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}

	public String getDeviceChannel() {
		return deviceChannel;
	}

	public void setDeviceChannel(String deviceChannel) {
		this.deviceChannel = deviceChannel;
	}

	public String getMessageCategory() {
		return messageCategory;
	}

	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}

	public String getMessageVersion() {
		return messageVersion;
	}

	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}

	public String getThreeDSRequestorAuthenticationInd() {
		return threeDSRequestorAuthenticationInd;
	}

	public void setThreeDSRequestorAuthenticationInd(String threeDSRequestorAuthenticationInd) {
		this.threeDSRequestorAuthenticationInd = threeDSRequestorAuthenticationInd;
	}

	public String getAcquirerBIN() {
		return acquirerBIN;
	}

	public void setAcquirerBIN(String acquirerBIN) {
		this.acquirerBIN = acquirerBIN;
	}

	public String getAcquirerMerchantID() {
		return acquirerMerchantID;
	}

	public void setAcquirerMerchantID(String acquirerMerchantID) {
		this.acquirerMerchantID = acquirerMerchantID;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getPurchaseCurrency() {
		return purchaseCurrency;
	}

	public void setPurchaseCurrency(String purchaseCurrency) {
		this.purchaseCurrency = purchaseCurrency;
	}

	public String getPurchaseExponent() {
		return purchaseExponent;
	}

	public void setPurchaseExponent(String purchaseExponent) {
		this.purchaseExponent = purchaseExponent;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getBrowserAcceptHeader() {
		return browserAcceptHeader;
	}

	public void setBrowserAcceptHeader(String browserAcceptHeader) {
		this.browserAcceptHeader = browserAcceptHeader;
	}

	public String getBrowserIP() {
		return browserIP;
	}

	public void setBrowserIP(String browserIP) {
		this.browserIP = browserIP;
	}

	public String getBrowserJavaEnabled() {
		return browserJavaEnabled;
	}

	public void setBrowserJavaEnabled(String browserJavaEnabled) {
		this.browserJavaEnabled = browserJavaEnabled;
	}

	public String getBrowserLanguage() {
		return browserLanguage;
	}

	public void setBrowserLanguage(String browserLanguage) {
		this.browserLanguage = browserLanguage;
	}

	public String getBrowserColorDepth() {
		return browserColorDepth;
	}

	public void setBrowserColorDepth(String browserColorDepth) {
		this.browserColorDepth = browserColorDepth;
	}

	public String getBrowserScreenHeight() {
		return browserScreenHeight;
	}

	public void setBrowserScreenHeight(String browserScreenHeight) {
		this.browserScreenHeight = browserScreenHeight;
	}

	public String getBrowserScreenWidth() {
		return browserScreenWidth;
	}

	public void setBrowserScreenWidth(String browserScreenWidth) {
		this.browserScreenWidth = browserScreenWidth;
	}

	public String getBrowserTZ() {
		return browserTZ;
	}

	public void setBrowserTZ(String browserTZ) {
		this.browserTZ = browserTZ;
	}

	public String getBrowserUserAgent() {
		return browserUserAgent;
	}

	public void setBrowserUserAgent(String browserUserAgent) {
		this.browserUserAgent = browserUserAgent;
	}

	@Override
	public String toString() {
		return "AutenticationBean [messageType=" + messageType + ", acctNumber=" + acctNumber + ", cardExpiryDate="
				+ cardExpiryDate + ", deviceChannel=" + deviceChannel + ", messageCategory=" + messageCategory
				+ ", messageVersion=" + messageVersion + ", threeDSRequestorAuthenticationInd="
				+ threeDSRequestorAuthenticationInd + ", acquirerBIN=" + acquirerBIN + ", acquirerMerchantID="
				+ acquirerMerchantID + ", cardholderName=" + cardholderName + ", purchaseAmount=" + purchaseAmount
				+ ", purchaseCurrency=" + purchaseCurrency + ", purchaseExponent=" + purchaseExponent
				+ ", purchaseDate=" + purchaseDate + ", browserAcceptHeader=" + browserAcceptHeader + ", browserIP="
				+ browserIP + ", browserJavaEnabled=" + browserJavaEnabled + ", browserLanguage=" + browserLanguage
				+ ", browserColorDepth=" + browserColorDepth + ", browserScreenHeight=" + browserScreenHeight
				+ ", browserScreenWidth=" + browserScreenWidth + ", browserTZ=" + browserTZ + ", browserUserAgent="
				+ browserUserAgent + "]";
	}
	
	

}
