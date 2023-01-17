package com.example.a_mu_geo_na;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AMuGeoNaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AMuGeoNaApplication.class, args);
	}

}
