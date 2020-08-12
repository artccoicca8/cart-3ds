//package com.alignet.app;
//
//import java.io.InputStream;
//import java.security.KeyStore;
//
//import javax.net.ssl.KeyManager;
//import javax.net.ssl.KeyManagerFactory;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.TrustManagerFactory;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
////@Configuration
//@Component
//public class SSLConfiguration {
//	private static Logger log = LoggerFactory.getLogger(SSLConfiguration.class);
//	// @Value("${ose.ssl.keystore}")
//	public String fileLocation = "classpath:3DSC-PRD-SVR-V210-ALIGNET_S.A.C-58990.jks";
//
////	@Value("${ose.ssl.keystore-password}")
//	public String keyStorePass = "3DSC-PRD-SVR-V210-ALIGNET_S.A.C-58990";
//
//	private static final String CLASSPATH_LOC = "classpath:";
//
//	@Bean
//	@Scope("singleton")
//	public KeyStore getKS() {
//		log.info("************ getKS ");
//		InputStream is = null;
//		KeyStore ks;
//		try {
//			if (fileLocation.startsWith(CLASSPATH_LOC)) {
//				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileLocation.substring(CLASSPATH_LOC.length()));
//			}
//
//			ks = KeyStore.getInstance("JKS");
//			ks.load(is, keyStorePass.toCharArray());
//			is.close();
//			return ks;
//		} catch (Exception e) {
//			throw new RuntimeException("Error al abrir el keystore");
//		}
//
//	}
//
//	@Bean
//	public SSLContext createSSLContext(TrustManager trustManager, KeyManager keyManager) {
//		log.info("************ createSSLContext ");
//		try {
//			SSLContext c = SSLContext.getInstance("TLSv1.2");
//			c.init(new KeyManager[] { keyManager }, new TrustManager[] { trustManager }, null);
//			return c;
//
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Bean
//	@Scope("singleton")
//	public KeyManagerFactory getKeyManager(@Autowired KeyStore ks) {
//		log.info("************ getKeyManager ");
//		KeyManagerFactory kmf = null;
//		try {
//			kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//			kmf.init(ks, keyStorePass.toCharArray());
//
//		} catch (Exception e) {
//			throw new RuntimeException("Error al crear KeyManagerFactory");
//		}
//		return kmf;
//	}
//
//	@Bean
//	@Scope("singleton")
//	public TrustManagerFactory getTrustManager(@Autowired KeyStore ks) {
//		log.info("************ getTrustManager ");
//		TrustManagerFactory tmf = null;
//		try {
//			tmf = TrustManagerFactory.getInstance("X509");
//			tmf.init(ks);
//
//		} catch (Exception e) {
//			throw new RuntimeException("Error al crear TrustManagerFactory");
//		}
//		return tmf;
//	}
//
//	@Bean
//	public TrustManager createTrustManager(TrustManagerFactory tmf) {
//		log.info("************ createTrustManager ");
//		return tmf.getTrustManagers()[0];
//	}
//	
//	@Bean
//	public KeyManager createKeyManager(KeyManagerFactory kmf) {
//		log.info("************ createKeyManager ");
//		return kmf.getKeyManagers()[0]; 
//	}
//
//}
