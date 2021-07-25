package com.demo.codingtask;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.port;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.codingtask.dto.SubscriptionDto;
import com.jayway.restassured.RestAssured;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DetailsControllerIntegrationTest {

	@LocalServerPort
	int localServerPort;

	public DetailsControllerIntegrationTest() {
		super();
	}
	@BeforeEach
	public void setUp() {
		RestAssured.port = localServerPort;
	}
	
	@Test
	@DisplayName("test to get the newsletter subscription details of a user from NewsLetterSubscriptionService")
	public void test_get_subscription_details() {
		given().port(port).
		when().get("/demo-details/api/v1/details/"+2).
				then().statusCode(HttpStatus.SC_OK);
	}
	

}
