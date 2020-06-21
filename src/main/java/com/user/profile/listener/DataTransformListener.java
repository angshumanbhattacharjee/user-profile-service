/**
 * 
 */
package com.user.profile.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.profile.dataTransform.DataTransformService;
import com.user.profile.service.impl.UserProfileServiceImpl;

import lombok.extern.log4j.Log4j2;

/**
 * @author ANGSHUMAN
 *
 */
@Component
@Log4j2
public class DataTransformListener {
	
	@Autowired
	private DataTransformService dataTransformationService;
	
	@Autowired
	private UserProfileServiceImpl userProfileService;
	
	@RabbitListener(queues = "${data.transform.queue}")
	public void processMessageForUserReview(String message) {
		log.info("Received the Message from data.transform.queue " + message);
		try {
			dataTransformationService.processTransformation(message);
		} catch (Exception e) {
			log.info("Error occurred while processing!", e.getMessage());
		}
	}
	
	@RabbitListener(queues = "${user.profile.update.queue}")
	public void processMessageForWashCountUpdate(String message) {
		log.info("Received the Message from user.wash.count.update.queue " + message);
		try {
			userProfileService.updateUserWashCount(message);
		} catch (Exception e) {
			log.info("Error occurred while processing!", e.getMessage());
		}
	}

}
