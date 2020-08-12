package com.alignet.app.model.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class UtilsTresDs {
	private static Logger log = LoggerFactory.getLogger(UtilsTresDs.class);
	private UtilsTresDs() {
	}
	
	
	public static String objetToString(Object object ) {
		log.info("objetToString" );
		try {
			 Gson gson = new Gson();
			 return   gson.toJson(object);
		} catch (Exception e) {
			log.error("Error ",e);
		}
		
		return null;
	}
	
	public static Object stringToObject(String cadena ) {
		log.info("objetToString" );
		try {
			 Gson gson = new Gson();
			 return gson.fromJson(cadena, Object.class);
		} catch (Exception e) {
			log.error("Error ",e);
		}
		
		return null;
	}
	
	
}
