package br.ce.gyrodrigues.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;


public class FileTeste {

	
	@Test
	public void deveObrigarAEnviarArquivo() {
		given()
			.log().all()
		.when()
			.post("http://restapi.wcaquino.me/upload")
		.then()
			.log().all()
			.statusCode(404) //deveria ser 400							
			.body("error", is("Arquivo não enviado"))
		;
		
	}
	
	
	
	@Test
	public void deveFazerUploadDoA() {
		given()
			.log().all()
			.multiPart("arquivo", new File("src/main/resources/Users.pdf"))
		.when()
			.post("http://restapi.wcaquino.me/upload")
		.then()
			.log().all()
			.statusCode(200) //deveria ser 400	
			.body("name", is("Users.pdf"))
			.body("size", is (53080))
		
		;
		
	}
	
	
	@Test
	
	public void deveBaixarArquivo () throws IOException {
		
	byte[] image = given()
		.log().all()
		
	.when()
		.get("http://restapi.wcaquino.me/download")
	.then()
		//.log().all()
		.statusCode(200)
		.extract().asByteArray()
	;
	
	File imagem = new File("src/main/resources/file.jpg");
	OutputStream out =  new FileOutputStream(imagem);
	out.write(image);
	
	out.close();
	
	System.out.println(imagem.length());
	assertThat(imagem.length(), lessThan(100000L));
	
			
	
	}
}
