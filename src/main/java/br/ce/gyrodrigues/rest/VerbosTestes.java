package br.ce.gyrodrigues.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class VerbosTestes {
	
	@Test
	public void deveSalvarUsuario () {
		given()
			.log().all()
			.contentType("application/json")
			.body("{\n" + 
					"	\"name\": \"Jose\",\n" + 
					"	\"age\": 50\n" + 
					"}")
		.when()
			.post("https://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Jose"))
			.body("age", is(50))
		;
	}
}
