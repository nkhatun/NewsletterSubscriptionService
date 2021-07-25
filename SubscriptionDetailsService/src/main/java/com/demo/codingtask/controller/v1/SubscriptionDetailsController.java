package com.demo.codingtask.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.codingtask.dto.ResponseDto;
import com.demo.codingtask.service.SubscriptionDetailsService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/details")
@Slf4j
public class SubscriptionDetailsController {
	@Autowired
	private SubscriptionDetailsService detailsService;
	
	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch the newsletter subscription details of a user", response = ResponseDto.class)
	public ResponseDto getSubscriptionDetails(@PathVariable("id") Long userId) {
		log.info("API call to check if a requested user should receive the newsletter, or not");
		return detailsService.getSubscriptionDetails(userId);
	}
}
