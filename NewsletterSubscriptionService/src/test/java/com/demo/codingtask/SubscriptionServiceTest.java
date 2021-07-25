package com.demo.codingtask;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.codingtask.dto.ResponseDto;
import com.demo.codingtask.dto.SubscriptionDto;
import com.demo.codingtask.service.SubscriptionService;
import com.demo.codingtask.service.SubscriptionServiceImpl;
import com.demo.codingtask.utility.Constants;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SubscriptionServiceTest {

	@Autowired
	SubscriptionServiceImpl subscriptionService;

	@Test
	@DisplayName("to test user service for updating unsubscribed status")
	void testUpdateUnSubscriptionStatus() {
		SubscriptionDto subscriptionDto = new SubscriptionDto(4L, 0);
		ResponseDto responseDto = subscriptionService.updateSubscriptionStatus(subscriptionDto);
		assertEquals(responseDto.getStatus(), Constants.REST_STATUS_SUCCESS);
		assertTrue(responseDto.getMessage().contains(Constants.UN_SUBSCRIBE));
	}

	@Test
	@DisplayName("to test user service for updating subscribed status")
	void testUpdateSubscriptionStatus() {
		SubscriptionDto subscriptionDto = new SubscriptionDto(4L, 1);
		ResponseDto responseDto = subscriptionService.updateSubscriptionStatus(subscriptionDto);
		assertEquals(responseDto.getStatus(), Constants.REST_STATUS_SUCCESS);
		assertTrue(responseDto.getMessage().contains(Constants.SUBSCRIBE));
	}

	@Test
	@DisplayName("test to get user subscription details")
	void testGetSubscriptionDetails() {
		SubscriptionDto subscriptionDto = new SubscriptionDto(5L, 1);
		ResponseDto updateResponseDto = subscriptionService.updateSubscriptionStatus(subscriptionDto);
		assertEquals(updateResponseDto.getStatus(), Constants.REST_STATUS_SUCCESS);
		ResponseDto responseDto = subscriptionService.getSubscriptionPermission(5L);
		assertEquals(responseDto.getStatus(), Constants.REST_STATUS_SUCCESS);
		assertTrue(responseDto.getData().getDataItems().size() > 0);
	}
}
