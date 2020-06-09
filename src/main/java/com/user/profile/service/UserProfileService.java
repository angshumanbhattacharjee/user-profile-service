/**
 * 
 */
package com.user.profile.service;

import java.util.List;

import com.user.profile.model.UserCriteriaModel;
import com.user.profile.model.UserProfileModel;

/**
 * @author ANGSHUMAN
 *
 */

public interface UserProfileService {

	public UserProfileModel registerUser(UserProfileModel model) throws Exception;

	public List<UserProfileModel> getUsersByCriteria(UserCriteriaModel criteria) throws Exception;

	public String updateUserWashCount(UserProfileModel model) throws Exception;

}
