package com.bunny.integration.flow;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bunny.integration.constants.Constants;
import com.bunny.integration.dto.RequestDto;
import com.bunny.integration.dto.ResultDto;
import com.bunny.integration.dto.bio.BioResponseJobs;
import com.bunny.integration.dto.linkedin.LinkedInResponse;

public class GetInformationHandler implements GenericHandler<RequestDto> {
	
	@Autowired
	private RestTemplate webRestTemplate;

	@Value("${bunny.bio.token}")
	private String tokenBio;

	@Override
	public Message<ResultDto> handle(RequestDto payload, Map<String, Object> headers) {
		if (StringUtils.equals(payload.getType(), Constants.BIO)) {
			String path = UriComponentsBuilder.fromHttpUrl(payload.getUrl()).buildAndExpand(payload.getPersonId())
					.toUriString();

			HttpHeaders headersToSend = new HttpHeaders();
			headersToSend.add("X-Auth-Token", tokenBio);

			HttpEntity<?> entityBio = new HttpEntity<>(headersToSend);

			HttpEntity<BioResponseJobs> responseEntityBio = webRestTemplate.exchange(path, HttpMethod.GET, entityBio, BioResponseJobs.class);

			BioResponseJobs result = responseEntityBio.getBody();
			
			return MessageBuilder.withPayload(ResultDto.builder()
					.category(result.getCategory())
					.contributions(result.getContributions())
					.fromMonth(result.getFromMonth())
					.fromYear(result.getFromYear())
					.location(result.getLocation())
					.name(result.getName())
					.toMonth(result.getToMonth())
					.toYear(result.getToYear())
					.role(result.getRole())
					.type(Constants.BIO)
					.build())
					.copyHeaders(headers).build();
		}

		if (StringUtils.equals(payload.getType(), Constants.LINKEDIN)) {
			UriComponentsBuilder builderLinkedIn = UriComponentsBuilder
					.fromHttpUrl(payload.getUrl())
					.queryParam("format", "json");
			
			HttpHeaders headersToSend = new HttpHeaders();
			headersToSend.add("Authorization", "Bearer " +payload.getAccessToken());

			HttpEntity<?> entityLinked = new HttpEntity<>(new HttpHeaders());

			HttpEntity<LinkedInResponse> responseEntityLinked = webRestTemplate.exchange(builderLinkedIn.toUriString(),
					HttpMethod.GET, entityLinked, LinkedInResponse.class);

			LinkedInResponse result = responseEntityLinked.getBody();
			
			return MessageBuilder.withPayload(ResultDto.builder()
					.firstName(result.getFirstName())
					.headline(result.getHeadline())
					.id(result.getId())
					.lastName(result.getLastName())
					.type(Constants.LINKEDIN)
					.build())
					.copyHeaders(headers).build();
		}

		return null;
	}

}
