package com.proyecto.parqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProyectoGradoParqueaderoV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoGradoParqueaderoV1Application.class, args);

	}

}
