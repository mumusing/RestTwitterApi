package com.twitter.get.statuses;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.twitterapis.RequestType;
import com.common.twitterapis.RestUtilities;
import com.twitter.constants.EndPoints;
import com.twitter.constants.Path;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
public class UserTimelineTest
{

	public RequestSpecification reqSpec;
	public ResponseSpecification resSpec;
	
	@BeforeClass
	public void setup()
	{
		//Get Request Specification
		reqSpec=RestUtilities.getRequestSpecification();
		reqSpec.queryParam("screen_name", "MUKESH SINGH");
		reqSpec.basePath(Path.BASE_PATH_STATUSES);
	    	
		//Get Response Specification
		resSpec=RestUtilities.getResponseSpecification();	     
	}
	
	@Test
	public void readTweet1()
	{
		given()
		.spec(RestUtilities.createQueryParam(reqSpec,"count","1"))
	//	.spec(reqSpec)
		.when()
		.get(EndPoints.STATUSES_USER_TIMELINE)
		.then()
		.log().all()
		.specification(resSpec)
		.body("user.name",hasItem("MUKESH SINGH"));		
	}
	
	@Test
	public void readTweet2()
	{
		RestUtilities.setEndPoint(EndPoints.STATUSES_USER_TIMELINE);
		Response res=RestUtilities.getResponse(RestUtilities.createQueryParam(reqSpec, "count", "1"), RequestType.GET);
		ArrayList<String>screenNameList=res.path("user.screen_name");
		Assert.assertTrue(screenNameList.contains("MUKESH SINGH"));
		
	}
	
	
	
}
