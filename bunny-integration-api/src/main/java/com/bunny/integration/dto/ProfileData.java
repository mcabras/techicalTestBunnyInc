package com.bunny.integration.dto;

import javax.inject.Named;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Named
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileData {
	
	private String personId;

}
