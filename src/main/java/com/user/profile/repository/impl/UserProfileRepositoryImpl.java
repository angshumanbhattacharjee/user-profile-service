package com.user.profile.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import com.user.profile.model.UserCriteriaModel;
import com.user.profile.model.UserProfileModel;
import com.user.profile.repository.UserProfileRepository;
import com.user.profile.repository.UserProfileRepositoryCriteria;

public class UserProfileRepositoryImpl implements UserProfileRepositoryCriteria{

	@Autowired
	private MongoTemplate repository;
	
	@Override
	public List<UserProfileModel> findByCriteria(UserCriteriaModel criteria) throws Exception {
		List<UserProfileModel> list = null;
		try {
			list = repository.find(getWhereClause(criteria), UserProfileModel.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return list ;
	}

	private Query getWhereClause(UserCriteriaModel criteria) {
		Query whereClause = new Query();
		
		//getCustomerByStatus  //getWasherByStatus
		if(!(StringUtils.isEmpty(criteria.getUserStatus())) && !(StringUtils.isEmpty(criteria.getUserRole()))) {
			whereClause.addCriteria(Criteria.where("userStatus").is(criteria.getUserStatus()).and("userRole").is(criteria.getUserRole()));
		}
		//getAllCustomers //getAllWashers
		if(!StringUtils.isEmpty(criteria.getUserRole())) {
			whereClause.addCriteria(Criteria.where("userRole").is(criteria.getUserRole()));
		}
		return whereClause ;
	}

}
