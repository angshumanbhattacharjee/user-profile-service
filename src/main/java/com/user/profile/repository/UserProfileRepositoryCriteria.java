/**
 * 
 */
package com.user.profile.repository;

import java.util.List;

import com.user.profile.model.UserCriteriaModel;
import com.user.profile.model.UserProfileModel;

/**
 * @author ANGSHUMAN
 *
 */
@FunctionalInterface
public interface UserProfileRepositoryCriteria {
	
	List<UserProfileModel> findByCriteria(UserCriteriaModel criteria) throws Exception;

}
