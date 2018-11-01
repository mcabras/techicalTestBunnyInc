package com.bunny.integration.dto.bio;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class BioResponseJobs {

	private String id;
	private String category;
	private String role;
	private String contributions;
	private String fromMonth;
	private String fromYear;
	private String toMonth;
	private String toYear;
	private String location;
	
}
