package es.carlosnh.springboot_carlosnieto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class SpringBootCarlosNietoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCarlosNietoApplication.class, args);
	}

}
