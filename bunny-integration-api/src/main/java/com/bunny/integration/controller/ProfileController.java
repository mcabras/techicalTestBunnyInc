package com.bunny.integration.controller;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bunny.integration.dto.ProfileData;
import com.bunny.integration.dto.ResultDto;
import com.bunny.integration.service.IMergeProfilesService;

import lombok.extern.slf4j.Slf4j;

@Component(value = "profile")
@ELBeanName(value = "profile")
@Join(path = "/profile", to = "/profile.jsf")
@Slf4j
public class ProfileController {

	private ProfileData objProfile = new ProfileData();
	
	@Autowired
	private IMergeProfilesService mergeProfile; 
	
	public void merge() {
		FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String accessToken = paramMap.get("code");
		
        ResultDto objResult = mergeProfile.mergeProfiles(accessToken, objProfile.getPersonId());
        log.info(objResult.toString());
	}

	public ProfileData getProfileData() {
		return objProfile;
	}
	
}
