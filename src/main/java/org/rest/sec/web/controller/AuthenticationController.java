package org.rest.sec.web.controller;

import org.rest.sec.dto.User;
import org.rest.sec.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.Sets;

/**
 * - note: this controller will start working with the User model and, if necessary, will move to a Authentication resource (which is the way it should work)
 */
@Controller
public class AuthenticationController{
	
	public AuthenticationController(){
		super();
	}
	
	// API
	
	@RequestMapping( method = RequestMethod.POST,value = "/authentication" )
	@ResponseStatus( value = HttpStatus.CREATED )
	@ResponseBody
	public User createAuthentication(){
		final Authentication authenticationInSpring = SecurityContextHolder.getContext().getAuthentication();
		
		// - note: no need to publish the roles at this point
		
		final User authenticationResource = new User( authenticationInSpring.getName(), (String) authenticationInSpring.getCredentials(), Sets.<Role> newHashSet() );
		return authenticationResource;
	}

}
