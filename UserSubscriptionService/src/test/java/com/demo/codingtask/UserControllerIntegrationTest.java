package com.demo.codingtask;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.port;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.codingtask.dto.ResponseDto;
import com.demo.codingtask.dto.SubscriptionDto;
import com.demo.codingtask.dto.UserDto;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ExtractableResponse;
import com.jayway.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {

	@LocalServerPort
	int localServerPort;

	public UserControllerIntegrationTest() {
		super();
	}

	@BeforeEach
	public void setUp() {
		RestAssured.port = localServerPort;
	}

	@Test
	@DisplayName("test to create a new user")
	public void test_create_user() {
		UserDto userDto = new UserDto("Naj100", "naj100@gmail.com");
		given().port(port).body(userDto).contentType("application/json").post("/demo-user/api/v1/users").then()
				.statusCode(HttpStatus.SC_OK);
	}

	@Test
	@DisplayName("test to update suscription status to NewsletterSubscriptionService")
	public void test_update_subscription_status() {
		SubscriptionDto subscriptionDto = new SubscriptionDto(2L, 1);

		ExtractableResponse<Response> updateResponseEx = given().port(port).body(subscriptionDto)
				.contentType("application/json").post("/demo-user/api/v1/users/subscriptionStatus").then().and()
				.extract();
		ResponseDto response = updateResponseEx.as(ResponseDto.class);
		assertEquals(updateResponseEx.statusCode(), HttpStatus.SC_OK);
		assertEquals(response.getStatus(), "success");
	}

}
