package com.alignet.app.model.service;

import com.alignet.app.model.VersioningRequestBean;

public interface ClientesService {

	public String postVersioning(VersioningRequestBean bean);
	public String getVersioning(VersioningRequestBean bean);
	public String postAuthentication (VersioningRequestBean bean , String body);
	public String postDS();
}
