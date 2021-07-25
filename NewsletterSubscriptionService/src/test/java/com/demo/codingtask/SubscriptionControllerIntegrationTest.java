package com.demo.codingtask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.port;
import org.apache.http.HttpStatus;

import com.demo.codingtask.dto.SubscriptionDto;
import com.jayway.restassured.RestAssured;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SubscriptionControllerIntegrationTest {
	@LocalServerPort
	int localServerPort;

	public SubscriptionControllerIntegrationTest() {
		super();
	}

	@BeforeEach
	public void setUp() {
		RestAssured.port = localServerPort;
	}
	@Test
	@DisplayName("test to access unauthorized request")
	public void test_unauthorized_request() {
		SubscriptionDto subscriptionDto = new SubscriptionDto(3L, 1);

		given().port(port).body(subscriptionDto).contentType("application/json")
				.post("/demo-subscriptions/api/v1/subscriptions").then().statusCode(HttpStatus.SC_UNAUTHORIZED);
	}
	
	@Test
	@DisplayName("test to update the newsletter subscription status for a user")
	public void test_update_subscription_status() {
		SubscriptionDto subscriptionDto = new SubscriptionDto(1L, 1);

		given().auth().basic("demo", "demo").port(port).body(subscriptionDto).contentType("application/json")
				.post("/demo-subscriptions/api/v1/subscriptions").then().statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	@DisplayName("test to get the newsletter subscription status for a user")
	public void test_get_subscription_status() {
		SubscriptionDto subscriptionDto = new SubscriptionDto(2L, 0);

		given().auth().basic("demo", "demo").port(port).body(subscriptionDto).contentType("application/json")
				.post("/demo-subscriptions/api/v1/subscriptions").then().statusCode(HttpStatus.SC_OK);
		
		given().auth().basic("demo", "demo").port(port).
		when().get("/demo-subscriptions/api/v1/subscriptions/"+2).
				then().statusCode(HttpStatus.SC_OK);
	}

}
