package com.twitter.get.statuses;

import org.testng.annotations.Test;

import com.twitter.constants.Auth;
import com.twitter.constants.EndPoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class TweetToTwitter 
{

	@Test
	public void tweet()
	{
		given()
		.auth().oauth("xYOdApZmORBKTtJe9PrmLlCd2", "dNJnwBkZ6q4XaeBuCWMQrHIeyHO5Bf3u4R5940xxyw7CY7uOyc", "1219286498-y8oagkrCuCU44bdFulqNUlNkiTOFWolIJM2aLZp", "BxnSQtKyV6yHYWpOWRxM43rJCYwrDYdNNsDx7ximd86IF")
		.queryParam("status", "Atal ji on life support")
		.when()
		.post("https://api.twitter.com/1.1/statuses/update.json")
		.then()
		.statusCode(200)
		.contentType("application/json");		
	}
	
	@Test
	public void retweet()
	{
		 Response response=given()
		.auth().oauth("xYOdApZmORBKTtJe9PrmLlCd2", "dNJnwBkZ6q4XaeBuCWMQrHIeyHO5Bf3u4R5940xxyw7CY7uOyc", "1219286498-y8oagkrCuCU44bdFulqNUlNkiTOFWolIJM2aLZp", "BxnSQtKyV6yHYWpOWRxM43rJCYwrDYdNNsDx7ximd86IF")
		.queryParam("status", "Atal ji in very critical condition")
		.when()
		.post("https://api.twitter.com/1.1/statuses/retweet/1029800231200731136.json")
		.then()
		.statusCode(200)
		.contentType("application/json")
		.body("id", equalTo("1029800231200731136"))
		.log()
		.all()
		.extract()
		.response();
	}
}
