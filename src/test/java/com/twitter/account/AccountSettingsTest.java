package com.twitter.account;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.twitterapis.RestUtilities;
import com.twitter.constants.EndPoints;
import com.twitter.constants.Path;
import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AccountSettingsTest 
{
	public RequestSpecification reqSpec;
	public ResponseSpecification resSpec;
	@BeforeClass
	public void setup()
	{
		reqSpec=RestUtilities.getRequestSpecification();
		reqSpec.basePath(Path.BASE_PATH_ACCOUNT);
		
		resSpec=RestUtilities.getResponseSpecification();	
	}
	
	@Test
	public void getSettings()
	{
		Response response=given()
		.spec(reqSpec)
		.when()
		.get(EndPoints.ACCOUNT_GET_SETTINGS)
		.then()
		.spec(resSpec)
		.extract()
		.response();
		
		
		
		//https://jsonpath.curiousconcept.com/     http://www.jsoneditoronline.org/
		String responseAsString=response.asString();
		System.out.println(responseAsString);
		JsonPath jsopPath=RestUtilities.JsonPath(response);
		
				
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.name), "Pacific Time (US & Canada)");
		Assert.assertEquals(jsopPath.getInt(AccountSettingsJsonPath.utc_offset), -28800);
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.tzinfo_name), "America/Los_Angeles");

		Assert.assertFalse(jsopPath.getBoolean(AccountSettingsJsonPath.Protected));
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.screen_name), "SingkMukesh");
		Assert.assertTrue(jsopPath.getBoolean(AccountSettingsJsonPath.always_use_https));
		Assert.assertFalse(jsopPath.getBoolean(AccountSettingsJsonPath.use_cookie_personalization));

		Assert.assertFalse(jsopPath.getBoolean(AccountSettingsJsonPath.enabled));
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.end_time), null);
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.start_time), null);

		Assert.assertFalse(jsopPath.getBoolean(AccountSettingsJsonPath.geo_enabled));
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.language), "en");
		Assert.assertTrue(jsopPath.getBoolean(AccountSettingsJsonPath.discoverable_by_email));
		Assert.assertFalse(jsopPath.getBoolean(AccountSettingsJsonPath.discoverable_by_mobile_phone));
		Assert.assertFalse(jsopPath.getBoolean(AccountSettingsJsonPath.display_sensitive_media));
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.allow_contributor_request), "all");
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.allow_dms_from), "following");
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.allow_dm_groups_from), "following");
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.translator_type), "none");

		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.location_name), "Bangalore");
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.countryCode), "IN");
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.url), "http://where.yahooapis.com/v1/place/2295420");
		Assert.assertEquals(jsopPath.getInt(AccountSettingsJsonPath.woeid), 2295420);

		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.placeType_name), "Town");
		Assert.assertEquals(jsopPath.getInt(AccountSettingsJsonPath.code), 7);
		Assert.assertEquals(jsopPath.getInt(AccountSettingsJsonPath.parentid), 23424848);
		Assert.assertEquals(jsopPath.get(AccountSettingsJsonPath.country), "India");
	
	}   
	
	
	@Test
	public void verifyCredentials()
	{
		Response response=given()
				.spec(RestUtilities.createQueryParam(reqSpec, "name","MUKESH SINGH"))
				.when()
				.get(EndPoints.ACCOUNT_VERIFY_CREDENTIALS)
				.then()
				.spec(resSpec)
				.extract()
				.response();
		
		String res=response.asString();
		System.out.println(res);
		
		JsonPath jsonPath=new JsonPath(res);
		//Verify credentials
		Assert.assertEquals(jsonPath.getInt(VerifyCredentialsJsonPath.id), 1219286498);
		Assert.assertEquals(jsonPath.get(VerifyCredentialsJsonPath.name), "MUKESH SINGH");
		Assert.assertEquals(jsonPath.get(VerifyCredentialsJsonPath.screen_name), "SingkMukesh");
		Assert.assertEquals(jsonPath.get(VerifyCredentialsJsonPath.location), "Bengaluru, Karnataka");
		Assert.assertEquals(jsonPath.get(VerifyCredentialsJsonPath.description), "Software Engineer, Reader, Listener");
		Assert.assertEquals(jsonPath.get(VerifyCredentialsJsonPath.created_at), "Mon Feb 25 16:40:58 +0000 2013");
		Assert.assertEquals(jsonPath.get(VerifyCredentialsJsonPath.time_zone), "Pacific Time (US & Canada)");
		Assert.assertFalse(jsonPath.getBoolean(VerifyCredentialsJsonPath.verified));

		Assert.assertEquals(jsonPath.get(VerifyCredentialsJsonPath.profile_background_image_url), "http://abs.twimg.com/images/themes/theme1/bg.png");
		Assert.assertEquals(jsonPath.get(VerifyCredentialsJsonPath.profile_background_image_url_https), "https://abs.twimg.com/images/themes/theme1/bg.png");
		Assert.assertEquals(jsonPath.get(VerifyCredentialsJsonPath.profile_image_url_https), "https://pbs.twimg.com/profile_images/788263119021760512/inn_Sh9k_normal.jpg");
		Assert.assertEquals(jsonPath.get(VerifyCredentialsJsonPath.profile_banner_url), "https://pbs.twimg.com/profile_banners/1219286498/1476771758");
		Assert.assertEquals(jsonPath.getInt(VerifyCredentialsJsonPath.followers_count), 42);
		Assert.assertEquals(jsonPath.getInt(VerifyCredentialsJsonPath.friends_count), 169);
		Assert.assertEquals(jsonPath.getInt(VerifyCredentialsJsonPath.statuses_count), 227);

	}
	
	
	
	
	
}
