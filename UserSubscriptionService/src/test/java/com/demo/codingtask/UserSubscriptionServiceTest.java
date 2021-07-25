package com.demo.codingtask;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.codingtask.dto.ResponseDto;
import com.demo.codingtask.dto.SubscriptionDto;
import com.demo.codingtask.dto.UserDto;
import com.demo.codingtask.service.UserService;
import com.demo.codingtask.utility.Constants;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserSubscriptionServiceTest {
	@Autowired
	UserService userService;

	@Test
	@DisplayName("to test user service for creating new user")
	void testCreateUser() {
		UserDto userDto = new UserDto("Naj50", "naj50@gmail.com");
		ResponseDto responseDto = userService.saveUserDetails(userDto);
		assertEquals(responseDto.getStatus(), Constants.REST_STATUS_SUCCESS);
		assertTrue(responseDto.getData().getDataItems().contains("id"));
	}

	@Test
	@DisplayName("to test user service for updating unsubscribed status")
	void testUpdateUnSubscriptionStatus() {
		SubscriptionDto subscriptionDto = new SubscriptionDto(3L, 0);
		ResponseDto responseDto = userService.updateSubscriptionStatus(subscriptionDto);
		assertEquals(responseDto.getStatus(), Constants.REST_STATUS_SUCCESS);
		assertTrue(responseDto.getMessage().contains(Constants.UN_SUBSCRIBE));
	}

	@Test
	@DisplayName("to test user service for updating subscribed status")
	void testUpdateSubscriptionStatus() {
		SubscriptionDto subscriptionDto = new SubscriptionDto(3L, 1);
		ResponseDto responseDto = userService.updateSubscriptionStatus(subscriptionDto);
		assertEquals(responseDto.getStatus(), Constants.REST_STATUS_SUCCESS);
		assertTrue(responseDto.getMessage().contains(Constants.SUBSCRIBE));
	}
}
