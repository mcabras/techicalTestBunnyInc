package com.bunny.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDto {

	private String category;
	private String name;
	private String role;
	private String contributions;
	private String fromMonth;
	private String fromYear;
	private String toMonth;
	private String toYear;
	private String location;

	private String id;
	private String firstName;
	private String headline;
	private String lastName;
	
	private String type;
	private String errorMessage;

}
