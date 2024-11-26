package api.endponits;

//This is created for perform CRUD Operation on the user API.

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Properties;
import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPointsProperties {
	
	
	//Method Created for getting url's from properties file
	
	public static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // Load the properties file
		
		return routes;
	}
	
	public static Response createUser(User Payload)
	{
		String post_url =getURL().getString("post_url");
		Response response =given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
		
		.when()
			//.post(Routes.post_url);
			.post(post_url);
		return response;
	}
	
	public static Response readUser(String userName) 
	{
		String get_url = getURL().getString("get_url");
		
		Response response = given()
			.pathParam("username", userName)
		
		.when()
			//.get(Routes.get_url);
			.get(get_url);
		
		return response;
	}


	public static Response UpdateUser(String userName,User Payload)
	{
		String update_url = getURL().getString("update_url");
		
		Response response =given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(Payload)
		
		.when()
			//.put(Routes.update_url);
			.put(update_url);
		return response;
	}
	

	public static Response deleteUser(String userName) 
	{
		String delete_url = getURL().getString("delete_url");
		Response response = given()
			.pathParam("username", userName)
		
		.when()
			//.delete(Routes.delete_url);
			.delete("delete_url");
		
		return response;
	}


	
}
