package br.ce.gyrodrigues.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Test;


public class UserJsonTest {
	
	@Test
	public void deveVerificarPrimeiroNivel() {
		given()
		
		.when()
			.get("http://restapi.wcaquino.me/users/1")
		.then()
			.statusCode(200)
			.body("id", is(1))
			.body("name", containsString("Silva"))
			.body("age", greaterThan(18))
			;
	}

	
	@Test
}
