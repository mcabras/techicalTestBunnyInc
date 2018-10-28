package com.bunny.integration.flow;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;

import com.bunny.integration.constants.Constants;

@Configuration
public class MergeFlowConfiguration {

	@Bean(name = Constants.INTEGRATION_MERGE_CHANNEL)
	public DirectChannel mergeInboundChannel() {
		return MessageChannels.direct().get();
	}

	@Bean(name = "mergekExecutor")
	public Executor mergeExecutor() {
		return Executors.newFixedThreadPool(5);
	}

	@Bean
	public IntegrationFlow mergeFlow() {
		return IntegrationFlows.from(mergeInboundChannel())
				.split(mergeSplitter())
				.channel(c -> c.executor(mergeExecutor()))
				.handle(getInformationHandler())
				.aggregate()
				.handle(finalHandler())
				.get();
	}

	@Bean
	public MergeSplitter mergeSplitter() {
		return new MergeSplitter();
	}

	@Bean("getInformationHandler")
	public GetInformationHandler getInformationHandler() {
		return new GetInformationHandler();
	}
	
	@Bean("finalHandler")
	public FinalHandler finalHandler() {
		return new FinalHandler();
	}
	
}
