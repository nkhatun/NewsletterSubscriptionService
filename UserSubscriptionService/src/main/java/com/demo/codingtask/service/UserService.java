package com.demo.codingtask.service;

import com.demo.codingtask.dto.ResponseDto;
import com.demo.codingtask.dto.SubscriptionDto;
import com.demo.codingtask.dto.UserDto;

public interface UserService {

	ResponseDto saveUserDetails(UserDto userDto);

	ResponseDto updateSubscriptionStatus(SubscriptionDto subscriptionDto);

	ResponseDto getSubscriptionDetails(Long userId);

}
