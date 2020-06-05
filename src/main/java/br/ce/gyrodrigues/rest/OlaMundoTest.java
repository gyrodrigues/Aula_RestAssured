package br.ce.gyrodrigues.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;




import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	
	@Test
	public void testOlaMundo() {
		Response response = request(Method.GET,"http://restapi.wcaquino.me/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode()== 200);
		Assert.assertTrue("o status code deveria ser 201", response.statusCode()== 200);
		Assert.assertEquals(200, response.statusCode());
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}
	
	@Test
	public void devoConhecerOutrasFormasDeRestAssured() {
		Response response = request(Method.GET,"http://restapi.wcaquino.me/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		
		get("http://restapi.wcaquino.me/ola").then().statusCode(200);
		
		given() 	//pré condições.
		
		.when()    //Ação 
			.get("http://restapi.wcaquino.me/ola")
		.then()  //Assertiva
			.assertThat()
			.statusCode(200);
	}
	
	
	
	@Test
	public void devoConhecerOsMatchersComHamcrest() {
		Assert.assertThat("Maria", Matchers.is("Maria"));
		Assert.assertThat(128, Matchers.is(128));
		Assert.assertThat(128, Matchers.isA(Integer.class));
		Assert.assertThat(128d, Matchers.isA(Double.class));
		Assert.assertThat(128, Matchers.greaterThan(120));
		Assert.assertThat(128, Matchers.lessThan(130));
		
		
		List<Integer> impares = Arrays.asList(1,3,5,7,9);
		assertThat(impares, hasSize(5));
		assertThat(impares,contains(1,3,5,7,9));
		assertThat(impares,containsInAnyOrder(1,3,5,7,9));
		assertThat(impares,hasItem(1));
		assertThat(impares,hasItems(1,5));
		
		assertThat("maria", is(not("joão")));
		assertThat("maria", not("joão"));
		assertThat("maria", anyOf(is("maria"), is("joana")));
		assertThat("joaquina", allOf(startsWith("joa"), endsWith("ina"), containsString("qui")));
	}
	
	
	@Test
	public void devoValidarOBody(){
		
		given() 	
		
		.when()    
			.get("http://restapi.wcaquino.me/ola")
		.then() 
			.statusCode(200)
			.body(is("Ola Mundo!"))
			.body(containsString("Mundo"))
			.body(is(not(nullValue())));
	}
}
