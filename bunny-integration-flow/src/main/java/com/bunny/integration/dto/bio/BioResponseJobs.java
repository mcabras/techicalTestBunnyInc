package com.bunny.integration.dto.bio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BioResponseJobs {

	private Long id;
	private String category;
	private String name;
	private String role;
	private String contributions;
	private String fromMonth;
	private String fromYear;
	private String toMonth;
	private String toYear;
	private String location;

}
