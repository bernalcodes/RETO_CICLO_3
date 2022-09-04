package com.app.web;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.web.entity.Vehicle;
import com.app.web.repository.VehicleRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private VehicleRepository repository;

	@Override
	public void run(String... args) throws Exception {
		Vehicle vehicle_1 = new Vehicle(
				"ICW749",
				Date.valueOf("2022-09-03"),
				"Carro",
				"Chevrolet",
				"Corsa",
				"Gris niebla",
				"Orlando Bernal");
		// repository.save(vehicle_1);

		Vehicle vehicle_2 = new Vehicle(
				"TST12A",
				Date.valueOf("2022-09-03"),
				"Moto",
				"AlgunaMonto",
				"AlgunModelo",
				"Negra",
				"Algun Dueno");
		// repository.save(vehicle_2);
	}
}
