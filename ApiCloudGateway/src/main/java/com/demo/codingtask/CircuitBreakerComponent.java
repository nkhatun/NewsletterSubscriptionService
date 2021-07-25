package com.demo.codingtask;

import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class CircuitBreakerComponent {
	  @CircuitBreaker(name = "CircuitBreaker", fallbackMethod = "subscriptionServiceFallback")
	    public String subscriptionServiceFallback(){
	       return "here in fallback: subscriptionServiceFallback ";
	    }
	  @CircuitBreaker(name = "CircuitBreaker", fallbackMethod = "userServiceFallback")
	    public String userServiceFallback(){
	       return "here in fallback: userServiceFallback ";
	    }
	  @CircuitBreaker(name = "CircuitBreaker", fallbackMethod = "detailsServiceFallback")
	    public String detailsServiceFallback(){
	       return "here in fallback: detailsServiceFallback ";
	    }
}
