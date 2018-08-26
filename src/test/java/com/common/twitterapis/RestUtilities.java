package com.common.twitterapis;

import com.twitter.constants.Auth;
import com.twitter.constants.Path;
import com.twitter.constants.ProxyInfo;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers.*;
public class RestUtilities
{
    public static String END_POINT;
    public static RequestSpecBuilder REQUEST_BUILDER;
    public static ResponseSpecBuilder RESPONSE_BUILDER;
    public static RequestSpecification REQUEST_SPECIFICATION;
    public static ResponseSpecification RESPONSE_SPECIFICATION;
    
    public static void setEndPoint(String endPoint)
    {
    	END_POINT=endPoint;
    }
    public static RequestSpecification getRequestSpecification()
    {
    	//ProxySpecification proxySpecification=new ProxySpecification(ProxyInfo.HOST, ProxyInfo.PORT, ProxyInfo.PROTOCOL);
    	AuthenticationScheme authScheme=RestAssured.oauth(Auth.CONSUMER_KEY, Auth.CONSUMER_SECRET, Auth.ACCESS_TOKEN, Auth.ACCESS_SECRET);
    	REQUEST_BUILDER=new RequestSpecBuilder();
    //	REQUEST_BUILDER.setProxy(proxySpecification);
    	REQUEST_BUILDER.setBaseUri(Path.BASE_URI);
    	REQUEST_BUILDER.setAuth(authScheme);
    	REQUEST_SPECIFICATION=REQUEST_BUILDER.build();
    	return REQUEST_SPECIFICATION;   	
    }
    public static ResponseSpecification getResponseSpecification()
    {
    	RESPONSE_BUILDER=new ResponseSpecBuilder();
    	RESPONSE_BUILDER.expectStatusCode(200);
    	RESPONSE_BUILDER.expectResponseTime(lessThan(6L),TimeUnit.SECONDS);
    	RESPONSE_SPECIFICATION=RESPONSE_BUILDER.build();
    	return RESPONSE_SPECIFICATION;
    	
    }
    public static RequestSpecification createQueryParam(RequestSpecification requesSpec, String param, String value)
    {
    	return requesSpec.queryParam(param, value);    	
    }
    public static RequestSpecification createQueryParam(RequestSpecification requesSpec, Map<String, String>queryParam)
    {
    	return requesSpec.queryParams(queryParam);    	
    }
    
    public static RequestSpecification createPathParam(RequestSpecification requesSpec, String path, String value)
    {
    	return requesSpec.pathParam(path, value);    	
    }
    
    public static Response getResponse()
    {
    	return given().get(END_POINT);
    	
    }
    
    public static Response getResponse(RequestSpecification request,RequestType type)
    {
    	REQUEST_SPECIFICATION.spec(request);
    	Response response=null;
    	switch (type)
    	{
		case GET:
			response=given().spec(REQUEST_SPECIFICATION).get(END_POINT);
			break;
			
        case POST:
        	response=given().spec(REQUEST_SPECIFICATION).post(END_POINT);
			break;	
			
        case DELETE:
        	response=given().spec(REQUEST_SPECIFICATION).delete(END_POINT);
        	break;
        case PUT:
        	response=given().spec(REQUEST_SPECIFICATION).put(END_POINT);
        	break;
        	
		default:
			throw new ActionNotSupportedException("Not valid Action");
			
		}
    //	response.then().log().all();
    	response.then().spec(RESPONSE_SPECIFICATION);  	
    	return response;
    }
    
    public static JsonPath JsonPath(Response response)
    {
    	String path=response.asString();
    	return new JsonPath(path);
    }
    public static XmlPath XmlPath(Response response)
    {
    	String path=response.asString();
    	return new XmlPath(path);
    }
    public static void resetBatchPath()
    {
    	RestAssured.baseURI=null;
    }
    public static void setContentType(ContentType type)
    {
    	given().contentType(type);
    }
    
    
    
    
}
