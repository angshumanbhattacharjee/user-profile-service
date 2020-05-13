package com.user.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.profile.model.UserProfileModel;
import com.user.profile.service.UserProfileService;
import com.user.profile.service.impl.UserProfileServiceImpl;

@RestController
public class UserProfileController {
	
	@Autowired
	private UserProfileService service;
	
	
	@PostMapping(value = "/registerUser" , produces = "application/json")
	public ResponseEntity registerUser(@RequestBody(required = true) UserProfileModel model) throws Exception{
		ResponseEntity response = null;
		try {
			response = new ResponseEntity(service.registerUser(model), HttpStatus.OK);
		}
		catch (Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
			throw e;
		}
		return response;
	}

}
