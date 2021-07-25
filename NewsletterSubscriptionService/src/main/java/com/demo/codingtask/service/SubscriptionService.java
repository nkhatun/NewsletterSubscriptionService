package com.demo.codingtask.service;

import com.demo.codingtask.dto.ResponseDto;
import com.demo.codingtask.dto.SubscriptionDto;

public interface SubscriptionService {

	ResponseDto updateSubscriptionStatus(SubscriptionDto subscriptionDto);

	ResponseDto getSubscriptionPermission(Long userId);

}
