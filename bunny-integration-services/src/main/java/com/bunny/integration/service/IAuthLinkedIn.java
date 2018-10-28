package com.bunny.integration.service;

public interface IAuthLinkedIn {

	String authenticationLinkedUrl();

	String accessToken(String code);

}
