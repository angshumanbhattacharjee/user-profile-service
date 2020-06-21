/**
 * 
 */
package com.user.profile.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.profile.constants.IConstants;
import com.user.profile.dto.UserWashCountUpdateDTO;
import com.user.profile.model.UserCriteriaModel;
import com.user.profile.model.UserProfileModel;
import com.user.profile.repository.UserProfileRepository;
import com.user.profile.service.UserProfileService;
import com.user.profile.utility.CommonUtility;

import lombok.extern.log4j.Log4j2;

/**
 * @author ANGSHUMAN
 *
 */
@Service
@Log4j2
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	private UserProfileRepository repository;
	
	@Autowired
	private ObjectMapper mapper;
	
	
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
	
	@Override
	public String updateUserWashCount(String userWashCountMessage) throws Exception {
		String message = null;
		Optional<UserProfileModel> model1 = null;
		try {
			UserWashCountUpdateDTO washCountDTO = mapper.readValue(userWashCountMessage, UserWashCountUpdateDTO.class);
			model1 = repository.findById(washCountDTO.getUserIdCustomer());
			if(model1.isPresent()) {
				updateWashCount(model1.get());
			}
			else {
				message = IConstants.UPDATE_WASH_COUNT_FAILURE_CUSTOMER;
			}
			model1 = repository.findById(washCountDTO.getUserIdWasher());
			if(model1.isPresent()) {
				updateWashCount(model1.get());
			}
			else {
				message = IConstants.UPDATE_WASH_COUNT_FAILURE_WASHER;
			}
		} catch (Exception e) {
			throw e;
		}
		return message ;
	}

	
	
	private void updateWashCount(UserProfileModel userProfileModel) {
		try {
			userProfileModel.setWashCount(userProfileModel.getWashCount() + 1);
			userProfileModel.setUserUpdatedDate(CommonUtility.getCurrentDateInString());
			repository.save(userProfileModel);
			log.info(IConstants.UPDATE_WASH_COUNT_SUCCESS + userProfileModel.getUserName());
		} catch (Exception e) {
			throw e;
		}
	}


	/**
	 	Method used to prepare the incoming user model based on register or update
	 	
	 	userStatus of value 2 means user is active
	 	userStatus of value 1 means user is inactive
	 	userStatus of value 0 means user is deleted
	*/
	private UserProfileModel prepareObject(UserProfileModel model) {
		/**
		 * update profile
		 */
		if(!StringUtils.isEmpty(model.getUserId())) {
			Optional<UserProfileModel> model1 = repository.findById(model.getUserId());
			model.setUserUpdatedDate(CommonUtility.getCurrentDateInString());
			model.setAverageRating(model1.get().getAverageRating());
			model.setUserCreatedDate(model1.get().getUserCreatedDate());
			model.setUserRole(model1.get().getUserRole());
			model.setUserStatus(model1.get().getUserStatus());
			model.setWashCount(model1.get().getWashCount());
			return model;
		}
		/**
		 * create new user profile
		 */
		else {
			ArrayList<String> list = new ArrayList<>();
			model.setUserCreatedDate(CommonUtility.getCurrentDateInString());
			model.setWashCount(0);
			model.setUserStatus(2);
			model.setAverageRating(0.0);
			model.setUserReview(list);
			return model;
		}
	}

}
