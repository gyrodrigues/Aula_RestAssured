package br.ce.gyrodrigues.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;

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
	
	
	@Test
	public void naoDeveSalvarUsuarioSemNome () {
		given()
			.log().all()
			.contentType("application/json")
			.body("{\n" + 
				"	\"age\": 50\n" + 
				"}")
		.when()
			.post("https://restapi.wcaquino.me/users")
			.then()
			.log().all()
			.statusCode(400)
			.body("id", is(nullValue()))
			.body("error", is("Name é um atributo obrigatório"))
		
	
		;
	}
	
	
	@Test
	public void deveSalvarUsuarioViaXML() {
		given()
			.log().all()
			.contentType(ContentType.XML)
			.body("<user> \n" + 
					"	<name>Jose</name>\n" + 
					"	<age>50</age>\n" + 
					"	</user>")
		.when()
			.post("https://restapi.wcaquino.me/usersXML")
		.then()
			.log().all()
			.statusCode(201)
			.body("user.@id", is(notNullValue()))
			.body("user.name", is("Jose"))
			.body("user.age", is("50"))
		;
	}
	
	@Test
	public void deveAlterarUsuario () {
		given()
			.log().all()
			.contentType("application/json")
			.body("{\n" + 
					"	\"name\": \"Usuario Alterado\",\n" + 
					"	\"age\": 80\n" + 
					"}")
		.when()
			.put("https://restapi.wcaquino.me/users/1")
		.then()
			.log().all()		
			.statusCode(200)
			.body("id", is(1))
			.body("name", is("Usuario Alterado"))
			.body("age", is(80))
			.body("salary", is(1234.5678f))
		;
	}
	
	@Test
	public void devoCustomizarURL () {
		given()
			.log().all()
			.contentType("application/json")
			.body("{\n" + 
					"	\"name\": \"Usuario Alterado\",\n" + 
					"	\"age\": 80\n" + 
					"}")
		.when()
			.put("https://restapi.wcaquino.me/{entidade}/{userId}","users", "1")
		.then()
			.log().all()		
			.statusCode(200)
			.body("id", is(1))
			.body("name", is("Usuario Alterado"))
			.body("age", is(80))
			.body("salary", is(1234.5678f))
		;
	}
	
	
	@Test
	public void customizarURLParte2 () {
		given()
			.log().all()
			.contentType("application/json")
			.body("{\n" + 
					"	\"name\": \"Usuario Alterado\",\n" + 
					"	\"age\": 80\n" + 
					"}")
			
			.pathParam("entidade", "users")
			.pathParam("userId", 1)
		.when()
			.put("https://restapi.wcaquino.me/{entidade}/{userId}","users", "1")
		.then()
			.log().all()		
			.statusCode(200)
			.body("id", is(1))
			.body("name", is("Usuario Alterado"))
			.body("age", is(80))
			.body("salary", is(1234.5678f))
		;
	}
	
	
	@Test
	public void deveRemoverumUsuario (){
		given()
			.log().all()
		.when()
			.delete("https://restapi.wcaquino.me/users/100")
		.then()
		.log().all()
		.statusCode(204)
		
		;
	}
	

	@Test
	public void naoDeveRemoverumUsuarioInexistente (){
		given()
			.log().all()
		.when()
			.delete("https://restapi.wcaquino.me/users/100")
		.then()
		.log().all()
		.statusCode(400)
		.body("error", is("Registro inexistente"))
		
		;
	}
	
	@Test
	public void deveSalvarUsuarioUsandoMap () {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "Usuario via map");
		params.put("age", 25);
		
		given()
			.log().all()
			.contentType("application/json")
			.body(params)
		.when()
			.post("https://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Usuario via map"))
			.body("age", is(25))
		;
	}
	
	@Test
	public void deveSalvarUsuarioUsandoObjeto () {
		
		User user = new User("Usuario via Objeto", 35);
		
		
		given()
			.log().all()
			.contentType("application/json")
			.body(user)
		.when()
			.post("https://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Usuario via Objeto"))
			.body("age", is(35))
		;
	}
	
	@Test
	public void deveDesearilizarObjetoaoSalvarUsuario () {
		
		User user = new User("Usuario deserealizado", 35);
		
		
		User usuarioInserido = given()
			.log().all()
			.contentType("application/json")
			.body(user)
		.when()
			.post("https://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.extract().body().as(User.class)
		;
		
		System.out.println(usuarioInserido);
		Assert.assertThat(usuarioInserido.getId(), notNullValue());
		Assert.assertEquals("Usuario deserealizado", usuarioInserido.getName());
		Assert.assertThat(usuarioInserido.getAge(), is(35));
		
	}
	
	
	@Test
	public void deveSalvarUsuarioViaXMLUsandoObjeto() {
		User user = new User("Usuario xml", 40);
		
		given()
			.log().all()
			.contentType(ContentType.XML)
			.body(user)
		.when()
			.post("https://restapi.wcaquino.me/usersXML")
		.then()
			.log().all()
			.statusCode(201)
			.body("user.@id", is(notNullValue()))
			.body("user.name", is("Usuario xml"))
			.body("user.age", is("40"))
		;
	}
	
	@Test
	public void deveDeserealizarXMLAoSalvaUsuario() {
		User user = new User("Usuario xml", 40);
		
		User usuarioInserido = given()
			.log().all()
			.contentType(ContentType.XML)
			.body(user)
		.when()
			.post("https://restapi.wcaquino.me/usersXML")
		.then()
			.log().all()
			.statusCode(201)
			.extract().body().as(User.class)
			;
			Assert.assertThat(usuarioInserido.getName(), is("Usuario xml"));
			Assert.assertThat(usuarioInserido.getAge(), is (40));
	}

}
