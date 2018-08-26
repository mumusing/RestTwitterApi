package com.twitter.account;

import com.common.twitterapis.RestUtilities;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UpdateProfile 
{
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
    
	public void setup()
	{
		reqSpec=RestUtilities.getRequestSpecification();
		
	}
	
	
}
