package com.bunny.integration.controller;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bunny.integration.service.IAuthLinkedIn;

@Component(value = "initial")
@ELBeanName(value = "intial")
@Join(path = "/", to = "/initial-action.jsf")
public class InitialController {

	@Autowired
	private IAuthLinkedIn authLinkedIn;

	public void enter() throws IOException {
		String urlAtuhLinked = authLinkedIn.authenticationLinkedUrl();
		FacesContext.getCurrentInstance().getExternalContext().redirect(urlAtuhLinked);
	}

}
