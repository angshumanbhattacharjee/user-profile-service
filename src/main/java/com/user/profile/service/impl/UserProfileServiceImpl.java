/**
 * 
 */
package com.user.profile.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.user.profile.model.UserCriteriaModel;
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
	
	
	/**
	 	Method used to save or update the incoming userModel
	 */
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
	
	
	/**
	 * Method used for filtering logic based on criteria Model
	 *  */
	@Override
	public List<UserProfileModel> getUsersByCriteria(UserCriteriaModel criteria) throws Exception {
		List<UserProfileModel> list = null;
		try {
			list = repository.findByCriteria(criteria);
		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
			System.out.println("StackTrace: " + e.getStackTrace());
			throw e;
		}
		return list;
	}

	
	
	/**
	 	Method used to prepare the incoming user model based on register or update
	 	
	 	userStatus of value 2 means user is active
	 	userStatus of value 1 means user is inactive
	 	userStatus of value 0 means user is deleted
	*/
	private UserProfileModel prepareObject(UserProfileModel model) {
		if(!StringUtils.isEmpty(model.getUserId())) {
			model.setUserUpdatedDate(CommonUtility.getCurrentDateInString());
		}
		else {
			model.setUserCreatedDate(CommonUtility.getCurrentDateInString());
			model.setUserUpdatedDate(CommonUtility.getCurrentDateInString());
			model.setUserStatus(2);
			model.setWashCount(0);
			model.setUserReview(new HashMap<String, String>());
		}
		return model;
	}
}
