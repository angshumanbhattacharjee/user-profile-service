/**
 * 
 */
package com.user.profile.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include; 

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
@Document(collection = "user")
@Component
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(value = Include.NON_NULL)
public class UserProfileModel implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	String userId;
	
	@Getter
	@Setter
	String userName;
	
	@Getter
	@Setter
	String userPassword;
	
	@Getter
	@Setter
	String userEmailId;
	
	@Getter
	@Setter
	String userContactNumber;
	
	@Getter
	@Setter
	String userRole;
	
	@Getter
	@Setter
	String userAddress;
	
	@Getter
	@Setter
	Integer userStatus;
	
	@Getter
	@Setter
	String userCreatedDate;
	
	@Getter
	@Setter
	String userUpdatedDate;
	
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
