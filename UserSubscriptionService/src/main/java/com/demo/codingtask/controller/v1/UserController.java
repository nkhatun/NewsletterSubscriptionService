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
import com.demo.codingtask.dto.UserDto;
import com.demo.codingtask.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save details of user", response = ResponseDto.class)
	public ResponseDto saveUserDetails(@RequestBody UserDto userDto) {
		log.info("API call to create new user");
		return userService.saveUserDetails(userDto);
	}
	
	@PostMapping(value="/subscriptionStatus",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update subscription status", response = ResponseDto.class)
	public ResponseDto updateSubscriptionStatus(@RequestBody SubscriptionDto subscriptionDto) {
		log.info("API call to update transaction status for a user");
		return userService.updateSubscriptionStatus(subscriptionDto);
	}
	

	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch subscription details", response = ResponseDto.class)
	public ResponseDto getSubscriptionDetails(@PathVariable("id") Long userId) {
		log.info("API call to get user details by user id");
		return userService.getSubscriptionDetails(userId);
	}
}
