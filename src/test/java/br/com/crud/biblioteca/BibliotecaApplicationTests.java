package br.com.crud.biblioteca;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BibliotecaApplicationTests {

	@Test
	void contextLoads() {
		String test = "Iniciando Teste...";
		assertEquals("Iniciando Teste...", test);
	}

}
