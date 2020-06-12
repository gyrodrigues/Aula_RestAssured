package br.ce.gyrodrigues.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.junit.Test;


import io.restassured.http.ContentType;

public class EnvioDadostest {
	
	@Test
	public void deveEnviarValorviaQuero() {
		given()
			.log().all()
		.when()
			.get("https://restapi.wcaquino.me/v2/users?format=json")
		.then()
			.log().all()
			.statusCode(200)
			.contentType(ContentType.JSON)
		;
	}
	
	@Test
	public void deveEnviarValorviaQueryViaParametro() {
		given()
			.log().all()
			.queryParam("format", "xml")
			.queryParam("Outra", "coisa")
		.when()
			.get("https://restapi.wcaquino.me/v2/users")
		.then()
			.log().all()
			.statusCode(200)
			.contentType(ContentType.XML)
			.contentType(containsString("utf-8"))
		;
	}

	@Test
	public void deveEnviarValorviaHeader() {
		given()
			.log().all()
			.accept(ContentType.XML)
		.when()
			.get("https://restapi.wcaquino.me/v2/users")
		.then()
			.log().all()
			.statusCode(200)
			.contentType(ContentType.XML)
		;
	}

}
