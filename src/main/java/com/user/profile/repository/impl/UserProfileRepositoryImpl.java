package com.user.profile.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import com.user.profile.model.UserCriteriaModel;
import com.user.profile.model.UserProfileModel;
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
			System.out.println("Error Message: " + e.getMessage());
			System.out.println("StackTrace: " + e.getStackTrace());
			throw e;
		}
		// TODO Auto-generated method stub
		return list ;
	}

	private Query getWhereClause(UserCriteriaModel criteria) {
		Query whereClause = new Query();
		
		if(!StringUtils.isEmpty(criteria.getUserStatus())) {
			whereClause.addCriteria(Criteria.where("userStatus").is(criteria.getUserStatus()));
		}
		if(!StringUtils.isEmpty(criteria.getUserPassword())) {
			whereClause.addCriteria(Criteria.where("userPassword").is(criteria.getUserPassword()));
		}
		if(!StringUtils.isEmpty(criteria.getUserEmailId())) {
			whereClause.addCriteria(Criteria.where("userEmailId").is(criteria.getUserEmailId()));
		}
		if(!StringUtils.isEmpty(criteria.getUserRole())) {
			whereClause.addCriteria(Criteria.where("userRole").is(criteria.getUserRole()));
		}
		if(!StringUtils.isEmpty(criteria.getUserName())) {
			whereClause.addCriteria(Criteria.where("userName").is(criteria.getUserName()));
		}
		if(!StringUtils.isEmpty(criteria.getUserReview())) {
			whereClause.addCriteria(Criteria.where("userReview").is(criteria.getUserReview()));
		}
		if(!StringUtils.isEmpty(criteria.getUserCreatedDate())) {
			whereClause.addCriteria(Criteria.where("userCreatedDate").is(criteria.getUserCreatedDate()));
		}
		if(!StringUtils.isEmpty(criteria.getAverageRating())) {
			whereClause.addCriteria(Criteria.where("averageRating").is(criteria.getAverageRating()));
		}
		if(!StringUtils.isEmpty(criteria.getWashCount())) {
			whereClause.addCriteria(Criteria.where("washCount").is(criteria.getWashCount()));
		}
		if(!StringUtils.isEmpty(criteria.getUserId())) {
			whereClause.addCriteria(Criteria.where("userId").is(criteria.getUserId()));
		}
		return whereClause ;
	}

}
