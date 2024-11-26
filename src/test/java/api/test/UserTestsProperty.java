package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endponits.UserEndPoints;
import api.endponits.UserEndPointsProperties;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestsProperty {
	
	Faker faker;
	User userPayload;
	Logger logger;

	@BeforeClass
	public void setup() 
	{
		faker = new Faker();
		
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser() 
	{
		logger.info("Creating the User");
		Response response =UserEndPointsProperties.createUser(userPayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("User is Created");
	}
	
	@Test(priority=2)
	public void getUserByName() 
	{
		logger.info("Reading User info");
		
		Response response =UserEndPointsProperties.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info(" User info is Displayed");
	}
	
	
	@Test(priority=3)
	public void updateUser() 
	{
		logger.info("Updating the User info");
		//Update data using Payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPointsProperties.UpdateUser(this.userPayload.getUsername(), userPayload);
		
		//response.then().log().body().statusCode(200);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Checking data after update
		Response responseAfterUpdate =UserEndPointsProperties.readUser(this.userPayload.getUsername());
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);	
		logger.info(" User info is Updated");
	}
	
	@Test(priority=4)
	public void deleteUserByName() 
	{
		logger.info("Deleting User info");
		Response response =UserEndPointsProperties.deleteUser(this.userPayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("User info is deleted");
	}
	
	
	
	
	
	
	
	
	
}
