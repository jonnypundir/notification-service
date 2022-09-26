package com.pundir.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestClient {

	@Autowired
	protected RestTemplate restTemplate;

	public <T> ResponseEntity<T> doPost(String serviceURL, HttpEntity<?> httpEntity, Class<T> cls) {
		log.info("POST request for url : {} ",serviceURL);
		log.debug("httpEntity : " + httpEntity);
		ResponseEntity<T> responseEntity = restTemplate.exchange(serviceURL, HttpMethod.POST, httpEntity, cls);
		log.debug("responseEntity : " + responseEntity);
		return responseEntity;
	}

	public <T> ResponseEntity<T> doPatch(String serviceURL, HttpEntity<?> httpEntity, Class<T> cls) {
		log.info("PATCH request for url : {} ",serviceURL);
		log.debug("httpEntity : " + httpEntity);

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		RestTemplate patchRestTemplate = new RestTemplate(requestFactory);
		ResponseEntity<T> responseEntity = patchRestTemplate.exchange(serviceURL, HttpMethod.PATCH, httpEntity, cls);
		log.debug("responseEntity : " + responseEntity);
		return responseEntity;
	}

	public <T> ResponseEntity<T> doPut(String serviceURL, HttpEntity<?> httpEntity, Object body, Class<T> cls) {
		log.info("PUT request for url : {} ",serviceURL);
		log.debug("httpEntity : " + httpEntity);
		ResponseEntity<T> responseEntity = restTemplate.exchange(serviceURL, HttpMethod.POST, httpEntity, cls);
		log.debug("responseEntity : " + responseEntity);
		return responseEntity;
	}

	public <T> ResponseEntity<T> doGet(String serviceURL, HttpEntity<?> httpEntity,  Class<T> cls) {
		log.info("GET request for url : {} ",serviceURL);
		log.debug("httpEntity : " + httpEntity);
		ResponseEntity<T> responseEntity = restTemplate.exchange(serviceURL, HttpMethod.GET, httpEntity, cls);
		log.debug("responseEntity : " + responseEntity);
		return responseEntity;
	}
}
