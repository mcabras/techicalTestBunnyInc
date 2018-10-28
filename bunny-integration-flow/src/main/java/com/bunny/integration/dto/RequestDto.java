package com.bunny.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDto {

	private String personId;
	private String url;
	private String type;
	private String accessToken;

}
