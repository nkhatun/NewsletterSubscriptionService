package com.demo.codingtask.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.codingtask.dto.ResponseDto;
import com.demo.codingtask.dto.SubscriptionDto;
import com.demo.codingtask.service.SubscriptionService;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api/v1/subscriptions")
@Slf4j
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update the newsletter subscription status for a user", response = ResponseDto.class)
	public ResponseDto updateSubsriptionStatus(@RequestBody SubscriptionDto subscriptionDto) {
		log.info("API call to update the newsletter subscription status");
		return subscriptionService.updateSubscriptionStatus(subscriptionDto);
	}
	
	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch the newsletter subscription status for a user", response = ResponseDto.class)
	public ResponseDto getSubscriptionPermission(@PathVariable("id") Long userId) {
		log.info("API call to check if a requested user should receive the newsletter, or not");
		return subscriptionService.getSubscriptionPermission(userId);
	}

}
