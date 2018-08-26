package com.twitter.application;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.twitterapis.RestUtilities;
import com.twitter.account.AccountSettingsJsonPath;
import com.twitter.constants.EndPoints;
import com.twitter.constants.Path;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

import java.util.List;
public class RateLimitStatus 
{
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	@BeforeClass
	public void setup()
	{
		reqSpec=RestUtilities.getRequestSpecification();
		reqSpec.basePath(Path.BASE_PATH_APPLICATION);
		
		resSpec=RestUtilities.getResponseSpecification();		
	}
	
	@Test
	public void rateLimitStatus()
	{
		Response response=given()
				.spec(RestUtilities.createQueryParam(reqSpec, "resources", "help,users,search,statuses"))
				.when()
				.get(EndPoints.APPLICATION_RATE_LIMIT_STATUS)
				.then()
				.extract()
				.response();
		
		System.out.println(response.asString());
		JsonPath jsonPath=new JsonPath(response.asString());
		//http://goessner.net/articles/JsonPath/    https://github.com/json-path/JsonPath
		Assert.assertEquals(jsonPath.get(RateLimitStatusJsonPath.access_token), "1219286498-y8oagkrCuCU44bdFulqNUlNkiTOFWolIJM2aLZp");
		Assert.assertEquals(jsonPath.getInt(RateLimitStatusJsonPath.users_report_spam_limit), 180);         		
		Assert.assertEquals(jsonPath.getInt(RateLimitStatusJsonPath.users_report_spam_remaining), 180);
		//Assert.assertEquals(jsonPath.getInt(RateLimitStatusJsonPath.users_report_spam_reset), 1512301820);
        		
		Assert.assertEquals(jsonPath.getInt(RateLimitStatusJsonPath.users_show_id_limit), 900);         		
		Assert.assertEquals(jsonPath.getInt(RateLimitStatusJsonPath.users_show_id_remaining), 900);
		//Assert.assertEquals(jsonPath.getInt(RateLimitStatusJsonPath.users_show_id_reset), 1512301820);		
	}
}
