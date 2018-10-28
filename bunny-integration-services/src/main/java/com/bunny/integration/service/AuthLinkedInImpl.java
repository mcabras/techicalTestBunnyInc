package com.bunny.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bunny.integration.service.dto.RequestAuthDto;

@Service
public class AuthLinkedInImpl implements IAuthLinkedIn {

	@Autowired
	private RestTemplate webRestTemplate;

	@Value("${bunny.linkedin.urlauth}")
	private String urlauth;
	
	@Value("${bunny.linkedin.responsetype}")
	private String responseType;
	
	@Value("${bunny.linkedin.redirecturl}")
	private String redirectUrl;
	
	@Value("${bunny.linkedin.clientid}")
	private String clientId;
	
	@Value("${bunny.linkedin.state}")
	private String state;
	
	@Value("${bunny.linkedin.urlaccess}")
	private String urlAccess;
	
	@Value("${bunny.linkedin.clientsecret}")
	private String clientSecret;

	@Override
	public String authenticationLinkedUrl() {

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(urlauth).queryParam("response_type", responseType)
				.queryParam("redirect_uri", redirectUrl)
				.queryParam("client_id", clientId)
				.queryParam("state", state);

		return builder.toUriString();
	}

	@Override
	public String accessToken(String code) {

		UriComponentsBuilder accessTokenBuilder = UriComponentsBuilder
				.fromHttpUrl(urlAccess)
				.queryParam("redirect_uri", redirectUrl)
				.queryParam("client_id", clientId)
				.queryParam("code", code)
				.queryParam("grant_type", "authorization_code")
				.queryParam("client_secret", clientSecret);

		HttpEntity<?> entity2 = new HttpEntity<>(new HttpHeaders());

		HttpEntity<RequestAuthDto> responseEntity2 = webRestTemplate.exchange(accessTokenBuilder.toUriString(), HttpMethod.POST,
				entity2, RequestAuthDto.class);

		return responseEntity2.getBody().getAccessToken();
	}

}
