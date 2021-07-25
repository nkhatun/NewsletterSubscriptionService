package com.demo.codingtask.service;

import java.util.Collections;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.codingtask.dto.DataDto;
import com.demo.codingtask.dto.ResponseDto;
import com.demo.codingtask.dto.SubscriptionDto;
import com.demo.codingtask.dto.UserDto;
import com.demo.codingtask.entity.Users;
import com.demo.codingtask.repository.UserRepository;
import com.demo.codingtask.utility.Constants;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RestTemplate restTemplate;

	@Transactional
	public ResponseDto saveUserDetails(UserDto userDto) {
		Users user = new Users();
		user.setUserEmail(userDto.getUserEmail());
		user.setUserName(userDto.getUserName());
		Users usersSaved = userRepo.save(user);
		return new ResponseDto(Constants.REST_STATUS_SUCCESS,new DataDto<>(Collections.singletonList(usersSaved)),"User Details Saved Successfully");
	}

	@Override
	public ResponseDto updateSubscriptionStatus(SubscriptionDto subscriptionDto) {
		restTemplate.getInterceptors().add(
				  new BasicAuthorizationInterceptor("demo", "demo"));
		return restTemplate.postForObject("http://SUBSCRIPTION-SERVICE/demo-subscriptions/api/v1/subscriptions", subscriptionDto, ResponseDto.class);
	}

	@Override
	public ResponseDto getSubscriptionDetails(Long userId) {
		Optional<Users> usersOp = userRepo.findById(userId);
		if(usersOp.isPresent()) {
			Users userDetails = usersOp.get();
			return new ResponseDto(Constants.REST_STATUS_SUCCESS,new DataDto<>(Collections.singletonList(userDetails)),"User Details Fetched Successfully");
		}
		return new ResponseDto(Constants.REST_STATUS_FAIL,null,Constants.NO_USER_PRESENT);
	}

}
