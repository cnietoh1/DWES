package es.calosnh.examendwes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ExamenDwesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamenDwesApplication.class, args);
    }

}
