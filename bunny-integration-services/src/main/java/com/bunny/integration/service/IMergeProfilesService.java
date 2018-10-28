package com.bunny.integration.service;

import com.bunny.integration.dto.ResultDto;

public interface IMergeProfilesService {
	
	ResultDto mergeProfiles(String token, String profileId);

}
