package com.demo.codingtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDetailsVO{
	private Long userId;
	private int subscriptionStatus;
	private String userName;
	private String userEmail;
}
