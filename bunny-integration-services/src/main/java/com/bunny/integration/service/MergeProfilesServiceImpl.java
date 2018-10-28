package com.bunny.integration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bunny.integration.constants.Constants;
import com.bunny.integration.dto.RequestDto;
import com.bunny.integration.dto.ResultDto;
import com.bunny.integration.flow.MergeGateway;

@Service
public class MergeProfilesServiceImpl implements IMergeProfilesService {
	
	@Autowired
	private MergeGateway mergeGateway;
	
	@Value("${bunny.bio.urlapi}")
	private String urlBio;
	
	@Value("${bunny.linkedin.urlapi}")
	private String urlLinked;

	@Override
	public ResultDto mergeProfiles(String token, String profileId) {
		
		List<RequestDto> lstRequest = new ArrayList<>();
		RequestDto objRequestBio = RequestDto.builder()
				.personId(profileId)
				.type(Constants.BIO)
				.url(urlBio).build();
		lstRequest.add(objRequestBio);
		
		RequestDto objRequestLinked = RequestDto.builder()
				.accessToken(token)
				.type(Constants.LINKEDIN)
				.url(urlLinked).build();
		lstRequest.add(objRequestLinked);
		
		
		return mergeGateway.mergeMessageGateway(lstRequest);
	}

}
