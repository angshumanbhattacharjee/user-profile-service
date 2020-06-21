package com.user.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.profile.model.UserCriteriaModel;
import com.user.profile.model.UserProfileModel;
import com.user.profile.service.UserProfileService;

@RestController
public class UserProfileController {
	
	@Autowired
	private UserProfileService service;
	
	
	/** 
	 * API endpoint used to receive the user model object and call the service method to save the model object in 
	 * database
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value = "/user-profile-service/registerUser" , produces = "application/json")
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
	
	/**
	 * API endpoint used for all required GET calls and uses a filtering logic
	 *  */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value = "/user-profile-service/criteria" , produces = "application/json")
	public ResponseEntity getUsersByCriteria(@RequestBody(required = true) UserCriteriaModel criteria) throws Exception{
		ResponseEntity response = null;
		try {
			response = new ResponseEntity(service.getUsersByCriteria(criteria) , HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
			throw e;
		}
		return response;
	}
	
	/**
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value = "/user-profile-service/updateUserWashCount", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity updateUserWashCount(@RequestBody(required = true) UserProfileModel model) throws Exception {
		ResponseEntity response = null;
		try {
			response = new ResponseEntity(service.updateUserWashCount(model), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
			throw e;
			// TODO: handle exception
		}
		return response ;
		
	}
	**/
}
