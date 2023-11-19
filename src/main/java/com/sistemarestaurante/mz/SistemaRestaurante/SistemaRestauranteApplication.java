package com.sistemarestaurante.mz.SistemaRestaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("com.sistemarestaurante")
@ComponentScan(basePackages = "com.sistemarestaurante")
@SpringBootApplication

public class SistemaRestauranteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaRestauranteApplication.class, args);
	}

}
