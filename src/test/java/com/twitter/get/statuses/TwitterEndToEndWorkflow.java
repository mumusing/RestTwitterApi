package com.twitter.get.statuses;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.twitterapis.RequestType;
import com.common.twitterapis.RestUtilities;
import com.twitter.constants.EndPoints;
import com.twitter.constants.Path;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
public class TwitterEndToEndWorkflow 
{
	static ProxySpecification ps;
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	String tweetId;
	@BeforeClass
	public void setup()
	{
		ps=new ProxySpecification("www-proxy-idc.in.oracle.com", 80, "http");
		reqSpec =	RestUtilities.getRequestSpecification();
		reqSpec.basePath(Path.BASE_PATH_STATUSES);		
		resSpec=RestUtilities.getResponseSpecification();	
        RestAssured.proxy(ps);
	}
	
	@Test
	public void postTweet()
	{
		try
		{
		Response response=given()
		.spec(RestUtilities.createQueryParam(reqSpec, "status", "Tweet from test"))
		.when()
		.post(EndPoints.STATUSES_TWEET_POST)
		.then()
		.spec(resSpec)
		.extract()
		.response();
		//System.out.println("hello");
        
		String res=response.asString();
		System.out.println(res);
		JsonPath jsonPath=RestUtilities.JsonPath(response);
		tweetId=jsonPath.get("id_str");
		System.out.println("tweet Id is: "+tweetId);		
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
	} 
/*	@Test/*(dependsOnMethods={"postTweet"})*/
	/*	public void readTweet()
	{
		RestUtilities.setEndPoint(EndPoints.STATUSES_USER_TIMELINE);
		Response response=RestUtilities.getResponse(RestUtilities.createQueryParam(reqSpec, "id", "19929890"), RequestType.GET);
		
		String text=response.path("text");
		System.out.println(text);		
	}	*/
	
/*	@Test(dependsOnMethods={"readTweet"})
	public void deleteTweet()
	{
		given()
		.spec(RestUtilities.createPathParam(reqSpec, "id", tweetId))
		.when()
		.post(EndPoints.STATUSES_TWEET_DESTROY)
		.then()
		.spec(resSpec);		
	}*/
	
	
}
