package io.thingcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.http.client.HttpClient;
import ratpack.http.client.ReceivedResponse;
import ratpack.spring.config.EnableRatpack;

import java.net.URI;

interface Service {
    String message();
}

@SpringBootApplication
@EnableRatpack
public class RatpackNonBlockingClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatpackNonBlockingClientApplication.class, args);
    }

    @Bean
    public Action<Chain> home() {
        return chain -> chain
                .get(ctx -> ctx
                        .render("Hello " + service().message())
                );
    }

    @Bean
    public Action<Chain> client() {
        return chain -> chain
                .get("client", ctx -> {

                        HttpClient client = ctx.get(HttpClient.class);
//                        client.get(new URI("https://mighty-river-19883.herokuapp.com/routing?sleep=100"))
                        client.get(new URI("http://localhost:9000/routing?sleep=200"))
                                .map(ReceivedResponse::getBody)
                                .map(body -> "Received from routing: " + body.getText())
                                .then(ctx::render);
                    });
    }

    @Bean
    public Service service() {
        return () -> "Ratpack!";
    }


}