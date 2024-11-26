package api.endponits;

//This is created for perform CRUD Operation on the user API.

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoints {
	
	public static Response createUser(User Payload)
	{
		Response response =given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
		
		.when()
			.post(Routes.post_url);
		return response;
	}
	
	public static Response readUser(String userName) 
	{
		Response response = given()
			.pathParam("username", userName)
		
		.when()
			.get(Routes.get_url);
		
		return response;
	}


	public static Response UpdateUser(String userName,User Payload)
	{
		Response response =given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(Payload)
		
		.when()
			.put(Routes.update_url);
		return response;
	}
	

	public static Response deleteUser(String userName) 
	{
		Response response = given()
			.pathParam("username", userName)
		
		.when()
			.delete(Routes.delete_url);
		
		return response;
	}


	
}
