package com.bunny.integration.flow;

import java.util.List;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.bunny.integration.constants.Constants;
import com.bunny.integration.dto.RequestDto;
import com.bunny.integration.dto.ResultDto;

@MessagingGateway(name = "mergeGateway")
public interface MergeGateway {

    @Gateway(requestChannel = Constants.INTEGRATION_MERGE_CHANNEL)
    ResultDto mergeMessageGateway(List<RequestDto> request);
}
