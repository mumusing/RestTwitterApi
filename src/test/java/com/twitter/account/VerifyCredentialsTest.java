package com.twitter.account;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.twitterapis.RestUtilities;
import com.twitter.constants.EndPoints;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class VerifyCredentialsTest 
{
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	@BeforeClass
	public void setup()
	{
		reqSpec=RestUtilities.getRequestSpecification();
		reqSpec.basePath(EndPoints.ACCOUNT_VERIFY_CREDENTIALS);
		
		resSpec=RestUtilities.getResponseSpecification();		
	}
	@Test
	public void verifyCredentials()
	{
		
		
	}
	
	

}
