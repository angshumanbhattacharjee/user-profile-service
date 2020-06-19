package com.user.profile.dataTransform;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.profile.dto.UserReviewDTO;
import com.user.profile.model.UserProfileModel;
import com.user.profile.repository.UserProfileRepository;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class DataTransformService {
	
	@Autowired
	private UserProfileRepository repository;
	
	@Autowired
	private ObjectMapper mapper;

	public void processTransformation(String message) throws JsonMappingException, JsonProcessingException {
		try {
			UserReviewDTO userReviewDTO = mapper.readValue(message, UserReviewDTO.class);
			Optional<UserProfileModel> model = null;
			ArrayList<String> list = null;
			//review for washer
			if(userReviewDTO.getUserReviewForCustomer() == null && userReviewDTO.getUserReviewForWasher() != null) {
				model = repository.findById(userReviewDTO.getWasherId());
				if(model.get() != null) {
					list = model.get().getUserReview();
					list.add(userReviewDTO.getCustomerId() + ": " + userReviewDTO.getUserReviewForWasher());
					model.get().setUserReview(list);
					if(userReviewDTO.getAverageRatingForWasher() != null) {
						if(model.get().getAverageRating() == 0.0) {
							model.get().setAverageRating(userReviewDTO.getAverageRatingForWasher());
						}
						else {
							model.get().setAverageRating((model.get().getAverageRating() + userReviewDTO.getAverageRatingForWasher())/2);
						}
					}
				}
				repository.save(model.get());
			}
			//review for customer
			else if(userReviewDTO.getUserReviewForWasher() == null && userReviewDTO.getUserReviewForCustomer() != null) {
				model = repository.findById(userReviewDTO.getCustomerId());
				if(model.get() != null) {
					list = model.get().getUserReview();
					list.add(userReviewDTO.getWasherId() + ": " + userReviewDTO.getUserReviewForCustomer());
					model.get().setUserReview(list);
					if(userReviewDTO.getAverageRatingForCustomer() != null) {
						if(model.get().getAverageRating() == 0.0) {
							model.get().setAverageRating(userReviewDTO.getAverageRatingForCustomer());
						}
						else {
							model.get().setAverageRating((model.get().getAverageRating() + userReviewDTO.getAverageRatingForCustomer())/2);
						}
					}
				}
				repository.save(model.get());
			}
			else {
				log.error("Error in saving user review");
			}
			
		} catch (Exception e) {
			throw e;
		}		
	}

}
