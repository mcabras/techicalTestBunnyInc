package com.bunny.integration.flow;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.handler.GenericHandler;

import com.bunny.integration.constants.Constants;
import com.bunny.integration.dto.ResultDto;

public class FinalHandler implements GenericHandler<List<ResultDto>> {

	@Override
	public ResultDto handle(List<ResultDto> payload, Map<String, Object> headers) {

		ResultDto objMerged = new ResultDto();

		ResultDto objResultBio = payload.stream()
				.filter(pay -> pay != null && StringUtils.equals(pay.getType(), Constants.BIO)).findFirst()
				.orElse(null);
		
		if (objResultBio != null) {
			objMerged.setCategory(objResultBio.getCategory());
			objMerged.setContributions(objResultBio.getContributions());
			objMerged.setFromMonth(objResultBio.getFromMonth());
			objMerged.setFromYear(objResultBio.getFromYear());
			objMerged.setLocation(objResultBio.getLocation());
			objMerged.setName(objResultBio.getName());
			objMerged.setToMonth(objResultBio.getToMonth());
			objMerged.setToYear(objResultBio.getToYear());
			objMerged.setRole(objResultBio.getRole());
		} else {
			objMerged.setErrorMessage("Error in Bio API");
		}

		ResultDto objResultLink = payload.stream()
				.filter(pay -> pay != null && StringUtils.equals(pay.getType(), Constants.LINKEDIN)).findFirst()
				.orElse(null);
		
		if (objResultLink != null) {
			objMerged.setFirstName(objResultLink.getFirstName());
			objMerged.setHeadline(objResultLink.getHeadline());
			objMerged.setId(objResultLink.getId());
			objMerged.setLastName(objResultLink.getLastName());
		} else {
			objMerged.setErrorMessage("Error in LinkedIn API");
		}

		return objMerged;
	}

}
