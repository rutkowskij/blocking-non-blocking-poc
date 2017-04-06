package io.thingcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class NonBlockingClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NonBlockingClientApplication.class, args);
    }
}
