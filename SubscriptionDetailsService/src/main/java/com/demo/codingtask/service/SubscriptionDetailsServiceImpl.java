package com.demo.codingtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.codingtask.dto.ResponseDto;

@Service
public class SubscriptionDetailsServiceImpl implements SubscriptionDetailsService{
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseDto getSubscriptionDetails(Long userId) {
		restTemplate.getInterceptors().add(
				  new BasicAuthorizationInterceptor("demo", "demo"));
		return restTemplate.getForObject("http://SUBSCRIPTION-SERVICE/demo-subscriptions/api/v1/subscriptions/"+userId, ResponseDto.class);
	}

}
