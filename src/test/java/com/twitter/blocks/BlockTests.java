package com.twitter.blocks;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.twitterapis.RestUtilities;
import com.twitter.constants.EndPoints;
import com.twitter.constants.Path;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
public class BlockTests 
{
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	@BeforeClass
	public void setup()
	{
		reqSpec=RestUtilities.getRequestSpecification();
		reqSpec.basePath(Path.BASE_PATH_BLOCKS);
		
		resSpec=RestUtilities.getResponseSpecification();	
	}
	@Test
	public void verifyBlockId()
	{
		Response res=given()
				.spec(RestUtilities.createQueryParam(reqSpec, "stringify_ids", "true"))
				.spec(RestUtilities.createQueryParam(reqSpec, "cursor", "-1"))
				.when()
				.get(EndPoints.BLOCKS_ID)
				.then()
				.extract()
				.response();
		
		System.out.println(res.asString());
		JsonPath jsonPath=new JsonPath(res.asString());
		Assert.assertEquals(jsonPath.getInt("ids[0]"), 19929890);
		Assert.assertEquals(jsonPath.getInt("ids[1]"), 47685065);
		Assert.assertEquals(jsonPath.getInt("ids[2]"), 1111268858);
		Assert.assertEquals(jsonPath.getInt("ids[3]"), 22763833);
		Assert.assertEquals(jsonPath.getInt("ids[4]"), 6509832);	
	}
	@Test
	public void verifyBlockUserList()
	{
		Response res=given()
				.spec(RestUtilities.createQueryParam(reqSpec, "skip_status", "true"))
				.spec(RestUtilities.createQueryParam(reqSpec, "cursor", "-1"))
				.when()
				.get(EndPoints.BLOCKS_LIST)
				.then()
				.extract()
				.response();
		
		System.out.println(res.asString());
		JsonPath jsonPath=new JsonPath(res.asString());
		Assert.assertEquals(jsonPath.get(BlockUserJsonPath.name), "barkha dutt");
		Assert.assertEquals(jsonPath.get(BlockUserJsonPath.id_str), "19929890");
		Assert.assertEquals(jsonPath.get(BlockUserJsonPath.screen_name), "BDUTT");
		Assert.assertEquals(jsonPath.get(BlockUserJsonPath.location), "India ");
		Assert.assertEquals(jsonPath.get(BlockUserJsonPath.profile_image_url), "http://pbs.twimg.com/profile_images/857238305443684352/DS0dW8JX_normal.jpg");
		
	}
}
