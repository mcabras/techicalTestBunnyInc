package com.bunny.integration.controller;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bunny.integration.dto.ProfileData;
import com.bunny.integration.dto.ResultDto;
import com.bunny.integration.service.IAuthLinkedIn;
import com.bunny.integration.service.IMergeProfilesService;

import lombok.extern.slf4j.Slf4j;

@Component(value = "profile")
@ELBeanName(value = "profile")
@Join(path = "/profile", to = "/profile-page.jsf")
@Slf4j
public class ProfileController {

	private ProfileData objProfile = new ProfileData();
	
	private ResultDto response = new ResultDto();

	@Autowired
	private IMergeProfilesService mergeProfile;

	@Autowired
	private IAuthLinkedIn authLinkedIn;

	public void merge() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String code = paramMap.get("code");
		String accessToken = authLinkedIn.accessToken(code);
		response = mergeProfile.mergeProfiles(accessToken, objProfile.getPersonId());
		log.info(response.toString());
	}

	public ProfileData getProfileData() {
		return objProfile;
	}
	
	public ResultDto getResponse() {
		return response;
	}

}
