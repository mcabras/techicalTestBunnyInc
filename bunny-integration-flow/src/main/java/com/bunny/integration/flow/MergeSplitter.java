package com.bunny.integration.flow;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.bunny.integration.constants.Constants;
import com.bunny.integration.dto.RequestDto;

public class MergeSplitter extends AbstractMessageSplitter {

	@SuppressWarnings("unchecked")
	@Override
	protected List<Message<RequestDto>> splitMessage(Message<?> payload) {
		List<RequestDto> lstRequest = (List<RequestDto>) payload.getPayload();
		
		List<Message<RequestDto>> lstResponseMessage = new ArrayList<>();
		
		RequestDto objRequestLinked = lstRequest.stream()
				.filter(requ -> StringUtils.equals(requ.getType(), Constants.LINKEDIN))
				.findFirst()
				.orElse(null);
		
		lstResponseMessage.add(createMessageResponse(payload, objRequestLinked));
		
		
		RequestDto objRequestBio = lstRequest.stream()
				.filter(requ -> StringUtils.equals(requ.getType(), Constants.BIO))
				.findFirst()
				.orElse(null);
		
		lstResponseMessage.add(createMessageResponse(payload, objRequestBio));
		
		return lstResponseMessage;
	}
	
	private Message<RequestDto> createMessageResponse(Message<?> message, RequestDto objRequestLinked) {
        return MessageBuilder.withPayload(objRequestLinked)
                .copyHeaders(message.getHeaders())
                .build();
    }

}
