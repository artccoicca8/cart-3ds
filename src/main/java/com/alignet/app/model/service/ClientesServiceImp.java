package com.alignet.app.model.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.alignet.app.model.MasterCard;
import com.alignet.app.model.VersioningObject;
import com.alignet.app.model.VersioningRequestBean;
import com.google.gson.Gson;

@Service
public class ClientesServiceImp implements ClientesService {

	private static Logger log = LoggerFactory.getLogger(ClientesServiceImp.class);

	private Gson gson;

//	@Autowired
//	private SSLContext  sslContext ; 
	@Override
	public String postDS() {
		log.info("postDS ");
		// -Djavax.net.debug=ssl
		try {

			CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext()).build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(client);

			RestTemplate restTemplate = new RestTemplate(requestFactory);
			String url = "https://3ds2.directory.mastercard.com/3ds/ds2/svc";
			MasterCard request = new MasterCard("", "", "", "PReq", "2.1.0");
			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

			log.info("Result = " + response.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@Override
	public String postVersioning(VersioningRequestBean bean) {
		log.info("postVersioning ");

		RestTemplate clienteRest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("key", bean.getKeyText());
		headers.set("ALG-API-VERSION", "1172945160");

		VersioningObject versioningObject = new VersioningObject(bean.getAcquirerMerchantID(), bean.getAcctNumber());

		HttpEntity<VersioningObject> request = new HttpEntity<>(versioningObject, headers);

		ResponseEntity<String> result = clienteRest.postForEntity(bean.getUrlEnviroment(), request, String.class);
		log.info("repuesta {} ", result.getBody());
		return result.getBody();
	}

	@Override
	public String postAuthentication(VersioningRequestBean bean, String body) {
		log.info("postVersioning ");

		gson = new Gson();
		RestTemplate clienteRest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("key", bean.getKeyText());
		headers.set("ALG-API-VERSION", "1172945160");
		headers.set("threeDSServerTransID", bean.getThreedsServerTransId());

		HttpEntity<String> request = new HttpEntity<>(body, headers);

		ResponseEntity<String> result = clienteRest.postForEntity(bean.getUrlEnviroment(), request, String.class);
		log.info("repuesta {} ", result.getBody());
		return result.getBody();
	}

	private static SSLContext sslContext( )	throws GeneralSecurityException, IOException {
		  String password = "3DSC-PRD-SVR-V210-ALIGNET_S.A.C-58990";
//		  String keystoreFile = "classpath:3DSC-PRD-SVR-V210-ALIGNET_S.A.C-58990.jks";
		  String keystoreFile = "src/main/resources/3DSC-PRD-SVR-V210-ALIGNET_S.A.C-58990.jks";
		
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		try (InputStream in = new FileInputStream(keystoreFile)) { 
			keystore.load(in, password.toCharArray());
		}
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keystore, password.toCharArray());

		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(keystore);

		SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
		sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

		return sslContext;
	}

}
