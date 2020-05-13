/**
 * 
 */
package com.user.profile.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.user.profile.model.UserProfileModel;
import com.user.profile.repository.UserProfileRepository;
import com.user.profile.service.UserProfileService;
import com.user.profile.utility.CommonUtility;

/**
 * @author ANGSHUMAN
 *
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	private UserProfileRepository repository;
	
	@Override
	public UserProfileModel registerUser(UserProfileModel model) {
		UserProfileModel model1 = null;
		try {
			model1 = repository.save(prepareObject(model));
		}
		catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
			System.out.println("StackTrace: " + e.getStackTrace());
			throw e;
		}
		return model1;
	}

	
	
	
	private UserProfileModel prepareObject(UserProfileModel model) {
		if(!StringUtils.isEmpty(model.getUserId())) {
			model.setUserUpdatedDate(CommonUtility.getCurrentDateInString());
		}
		else {
			model.setUserCreatedDate(CommonUtility.getCurrentDateInString());
			model.setUserUpdatedDate(CommonUtility.getCurrentDateInString());
			model.setUserStatus(1);
			model.setWashCount(0);
			model.setUserReview(new HashMap<String, String>());
		}
		return model;
	}

}
