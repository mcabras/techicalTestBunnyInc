package com.bunny.integration.dto.linkedin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkedInResponse {

	private String firstName;
	private String headline;
	private String id;
	private String lastName;

}
