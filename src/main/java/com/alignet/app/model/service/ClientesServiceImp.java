package com.alignet.app.model.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
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
//			String url = "https://3ds2.directory.mastercard.com/3ds/ds2/svc"; //mastercard
			String url = "https://ds-amexsafekey.americanexpress.com/payments/digital/v2/safekey_ds/preparations"; //mastercard
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
		log.info("key {} ", bean.getKeyText()); 

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
	public String getVersioning(VersioningRequestBean bean) {
		log.info("getVersioning ");

		RestTemplate clienteRest = new RestTemplate();

		String baseUrl = bean.getUrlEnviroment().concat("/").concat(bean.getAcquirerMerchantID()).concat("/")
				.concat(bean.getAcctNumber());

		log.info("getVersioning - baseUrl {} ", baseUrl);
		log.info("key {} ", bean.getKeyText()); 
		URI uri;
		try {
			uri = new URI(baseUrl);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", bean.getKeyText());
			headers.set("ALG-API-VERSION", "1172945160");

			HttpEntity<VersioningObject> request = new HttpEntity<>(null, headers);

			ResponseEntity<String> result = clienteRest.exchange(uri, HttpMethod.GET, request, String.class);
			log.info("repuesta {} ", result.getBody());
			return result.getBody();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public String postAuthentication(VersioningRequestBean bean, String body) {
		log.info("postAuthentication ");

		log.info("body {} ", body);
		log.info("bean {} ", bean.toString());

		gson = new Gson();
		RestTemplate clienteRest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("key", bean.getKeyText());
		headers.set("ALG-API-VERSION", "1172945160");
		headers.set("threeDSServerTransID", bean.getThreedsServerTransId());

		HttpEntity<String> request = new HttpEntity<>(body, headers);

		ResponseEntity<String> result = clienteRest.postForEntity(bean.getUrlEnviroment(), request, String.class);
		// log.info("repuesta {} ", result.getBody());

		log.info("Estatus " + result.getStatusCode().value());

		return result.getBody();
	}

	private static SSLContext sslContext() throws GeneralSecurityException, IOException {
//		String password = "3DSC-PRD-SVR-V210-ALIGNET_S.A.C-58990"; // mastercard
		String password = "mpi.safekey.amex.3dsecure.alignet.io"; //amex
//		  String keystoreFile = "classpath:3DSC-PRD-SVR-V210-ALIGNET_S.A.C-58990.jks";
//		String keystoreFile = "src/main/resources/3DSC-PRD-SVR-V210-ALIGNET_S.A.C-58990.jks";
		String keystoreFile = "src/main/resources/amex.jks";

		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		try (InputStream in = new FileInputStream(keystoreFile)) {
			keystore.load(in, password.toCharArray());
		}
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keystore, password.toCharArray());

		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(keystore);

		SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
		sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

		return sslContext;
	}

}
