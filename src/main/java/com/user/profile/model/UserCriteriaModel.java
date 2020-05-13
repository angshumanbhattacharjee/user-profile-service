/**
 * 
 */
package com.user.profile.model;

import java.util.Date;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ANGSHUMAN
 *
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCriteriaModel {
	
	@Id
	@Getter
	String userId;
	
	@Getter
	@Setter
	String userName;
	
	@Getter
	@Setter
	Integer userStatus;
	
	@Getter
	@Setter
	Date userCreatedDate;
	
	@Getter
	@Setter
	String userRole;
	
	@Getter
	@Setter
	HashMap<String, String> userReview;
	
	@Getter
	@Setter
	Double averageRating;
	
	@Getter
	@Setter
	Integer washCount;

}
