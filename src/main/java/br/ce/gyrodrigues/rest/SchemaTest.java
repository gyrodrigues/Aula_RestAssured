package br.ce.gyrodrigues.rest;

import static io.restassured.RestAssured.given;

import org.junit.Test;


import io.restassured.matcher.RestAssuredMatchers;
import org.xml.sax.SAXParseException;


public class SchemaTest {

	@Test
	
	public void deveValidarSchemaXml() {
		given()
			.log().all()
		.when()
			.get("https://restapi.wcaquino.me/usersXML")
		.then()
			.log().all()	
			.statusCode(200)
			.body(RestAssuredMatchers.matchesXsdInClasspath("users.xsd"))
			;
	}
	
	@Test(expected = SAXParseException.class)
	
	public void napdeveValidarSchemaXmlInvalido() {
		given()
			.log().all()
		.when()
			.get("https://restapi.wcaquino.me/invalidUsersXML")
		.then()
			.log().all()	
			.statusCode(200)
			.body(RestAssuredMatchers.matchesXsdInClasspath("users.xsd"))
			;
	}
}
