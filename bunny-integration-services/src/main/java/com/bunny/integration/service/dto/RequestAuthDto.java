package com.bunny.integration.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestAuthDto {
	
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("expires_in")
	private int expires;

}
