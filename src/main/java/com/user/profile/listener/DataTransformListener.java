/**
 * 
 */
package com.user.profile.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.profile.dataTransform.DataTransformService;

import lombok.extern.log4j.Log4j2;

/**
 * @author ANGSHUMAN
 *
 */
@Component
@Log4j2
public class DataTransformListener {
	
	@Autowired
	private DataTransformService service;
	
	@RabbitListener(queues = "${data.transform.queue}")
	public void processMessage(String message) {
		log.info("Received the Message from data.transform.queue " + message);
		try {
			service.processTransformation(message);
		} catch (Exception e) {
			log.info("Error occurred while processing!", e.getMessage());
		}
	}

}
