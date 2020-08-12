package com.alignet.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.alignet.app.model.VersioningRequestBean;
import com.alignet.app.model.service.ClientesService;
import com.google.gson.Gson;

@Controller
public class CartController {

	private Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private ClientesService service;

	@GetMapping({ "/", "home" })
	public String index(Model model) {
		model.addAttribute("message", "Carrito de Pruebas 3DS ");
		return "home";
	}

	@PostMapping("/api/versioning")
	public ResponseEntity<?> getVersioningViaAjax(@Valid @RequestBody VersioningRequestBean versioningRequestBean) {

		String rq = versioningRequestBean.toString();
		String resultVersioning = "" ; 
		logger.info("versioningRequestBean : {} ", rq);

		try {
			resultVersioning = service.postVersioning(versioningRequestBean);
//			service.postDS();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(resultVersioning);

	}

	@PostMapping("/api/authentication")
	public ResponseEntity<?> getAuthenticationViaAjax(@RequestHeader Map<String, String> headers,
			@RequestBody String bodyRequestBean) {

		logger.info("getAuthenticationViaAjax()");

		headers.forEach((key, value) -> {
			logger.info(String.format("Header '%s' = %s", key, value));
		});

		VersioningRequestBean requestBean = new VersioningRequestBean();
		requestBean.setKeyText(headers.get("key"));
		requestBean.setUrlEnviroment(headers.get("urlenviroment"));
		requestBean.setThreedsServerTransId(headers.get("threedsservertransid"));

		logger.info("resq : {} ", bodyRequestBean);
		logger.info("resq : {} ", requestBean);

		String resultAutentication = service.postAuthentication(requestBean, bodyRequestBean);

		return ResponseEntity.ok(resultAutentication);

	}

	@PostMapping("/api/authenticationPgcq")
	public ResponseEntity<?> getAuthenticationPgcqViaAjax(@RequestHeader Map<String, String> headers,
			@RequestBody String bodyRequestBean) {

		logger.info("getAuthenticationViaAjax()");

		headers.forEach((key, value) -> {
			logger.info(String.format("Header '%s' = %s", key, value));
		});

		VersioningRequestBean requestBean = new VersioningRequestBean();
		requestBean.setKeyText(headers.get("key"));
		requestBean.setUrlEnviroment(headers.get("urlenviroment"));
		requestBean.setThreedsServerTransId(headers.get("threedsservertransid"));

		logger.info("resq : {} ", bodyRequestBean);

		String resultAutentication = service.postAuthentication(requestBean, bodyRequestBean);

		return ResponseEntity.ok(resultAutentication);

	}

	@GetMapping("indexMulti")
	public String indexMulti(Model model) {
		model.addAttribute("message", "Carrito de Pruebas 3DS ");
		return "indexMulti";
	}

	@GetMapping("result")
	public String getResult(Model model) {
		return "result";
	}

	@PostMapping(path = "/index", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_XHTML_XML_VALUE })
	public String result(
			@RequestParam(name = "transStatus", required = false) String transStatus,
			@RequestParam(name = "threeDSServerTransID", required = false) String threeDSServerTransID,
			@RequestParam(name = "acsTransID", required = false) String acsTransID,
			@RequestParam(name = "eci", required = false) String eci,
			@RequestParam(name = "vci", required = false) String vci, Model model) {
		logger.info("challengeCompletionInd  ===  {} ", transStatus);
		logger.info("threeDSServerTransID ===  {} ", threeDSServerTransID);
		logger.info("acsTransID ===  {} ", acsTransID);

		model.addAttribute("threeDSServerTransID", threeDSServerTransID);
		model.addAttribute("acsTransID", acsTransID);
		model.addAttribute("transStatus", transStatus);
		model.addAttribute("eci", eci);
		model.addAttribute("vci", vci);

		return "result";
	}

}
