/**
 * 
 */
package com.user.profile.service;

import com.user.profile.model.UserProfileModel;

/**
 * @author ANGSHUMAN
 *
 */
@FunctionalInterface
public interface UserProfileService {

	public UserProfileModel registerUser(UserProfileModel model) throws Exception;

}
