package com.demo.codingtask.service;

import java.util.Collections;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.codingtask.dto.DataDto;
import com.demo.codingtask.dto.ResponseDto;
import com.demo.codingtask.dto.SubscriptionDetailsVO;
import com.demo.codingtask.dto.SubscriptionDto;
import com.demo.codingtask.dto.UserDto;
import com.demo.codingtask.entity.Subscriptions;
import com.demo.codingtask.repository.SubscriptionRepository;
import com.demo.codingtask.utility.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	@Autowired
	private SubscriptionRepository subscriptionRepo;
	@Autowired
	private RestTemplate restTemplate;

	@Transactional
	public ResponseDto updateSubscriptionStatus(SubscriptionDto subscriptionDto) {
		Subscriptions subscription = null;
		Optional<Subscriptions> subscriptionOp = subscriptionRepo.findByUserId(subscriptionDto.getUserId());
		if (subscriptionOp.isPresent()) {
			// Update subscription status for the newsletter
			subscription = subscriptionOp.get();
			subscription.setStatus(subscriptionDto.getSubscriptionStatus());
			subscriptionRepo.save(subscription);
			return new ResponseDto(Constants.REST_STATUS_SUCCESS, null, "The newsletter Is "
					+ (subscriptionDto.getSubscriptionStatus() == 0 ? Constants.UN_SUBSCRIBE : Constants.SUBSCRIBE)
					+ " successfully");
		} else {
			// create new subscriptions entry with the status
			Subscriptions subscriptions = new Subscriptions();
			subscriptions.setStatus(subscriptionDto.getSubscriptionStatus());
			subscriptions.setUserId(subscriptionDto.getUserId());
			subscriptionRepo.save(subscriptions);
			return new ResponseDto(Constants.REST_STATUS_SUCCESS, null, "The newsletter Is "
					+ (subscriptionDto.getSubscriptionStatus() == 0 ? Constants.UN_SUBSCRIBE : Constants.SUBSCRIBE)
					+ " successfully");
		}
	}

	@Override
	public ResponseDto getSubscriptionPermission(Long userId) {
		// find user details
		ResponseDto userDetailsResponse = restTemplate
				.getForObject("http://USER-SERVICE/demo-user/api/v1/users/" + userId, ResponseDto.class);
		if (userDetailsResponse.getStatus().equals(Constants.REST_STATUS_SUCCESS)) {
			Optional<Subscriptions> subscriptionOp = subscriptionRepo.findByUserId(userId);
			if (subscriptionOp.isPresent()) {
				// prepare subscription details for the user
				Subscriptions subscriptions = subscriptionOp.get();
				ObjectMapper mapper = new ObjectMapper();
				UserDto userDto = mapper.convertValue(userDetailsResponse.getData().getDataItems().get(0),
						UserDto.class);
				SubscriptionDetailsVO subscriptionDetailsVO = new SubscriptionDetailsVO();
				subscriptionDetailsVO.setSubscriptionStatus(subscriptions.getStatus());
				subscriptionDetailsVO.setUserId(userId);
				subscriptionDetailsVO.setUserName(userDto.getUserName());
				subscriptionDetailsVO.setUserEmail(userDto.getUserEmail());

				String status = subscriptions.getStatus() == 1 ? Constants.SUBSCRIBE : Constants.UN_SUBSCRIBE;
				return new ResponseDto(Constants.REST_STATUS_SUCCESS,
						new DataDto<>(Collections.singletonList(subscriptionDetailsVO)),
						"User has " + status + " from receiving the newsletter");
			} else {
				return new ResponseDto(Constants.REST_STATUS_FAIL, null, Constants.NO_HISTORY_PRESENT);
			}
		} else {
			// user does not exist in the system
			return new ResponseDto(Constants.REST_STATUS_FAIL, null, Constants.NO_USER_PRESENT);
		}
	}
}
