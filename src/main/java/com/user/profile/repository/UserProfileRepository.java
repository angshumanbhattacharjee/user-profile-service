package com.user.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.user.profile.model.UserProfileModel;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfileModel,String> {

}
