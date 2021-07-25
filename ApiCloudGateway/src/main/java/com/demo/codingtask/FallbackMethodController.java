package com.demo.codingtask;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackMethodController {
	
	@GetMapping(value="/userServiceFallback")
	public String userServiceFallbackMethod() {
		return "User service is taking longer than expeced."
				+ " Please try again later";
	}
	
	@GetMapping(value="/subscriptionServiceFallback")
	public String subscriptionServiceFallbackMethod() {
		return "Subscription service is taking longer than expeced."
				+ " Please try again later";
	}
	
	@GetMapping(value="/detailsServiceFallback")
	public String detailsServiceFallbackMethod() {
		return "Subscription Details service is taking longer than expeced."
				+ " Please try again later";
	}
	
}
