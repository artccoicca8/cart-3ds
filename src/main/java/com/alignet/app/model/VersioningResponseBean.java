package com.alignet.app.model;

import java.io.Serializable;

public class VersioningResponseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1194578714765618726L;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "VersioningResponseBean [msg=" + msg + "]";
	}

	
}
