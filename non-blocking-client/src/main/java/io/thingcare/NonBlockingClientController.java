package io.thingcare;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
public class NonBlockingClientController {
//    https://mighty-river-19883.herokuapp.com/routing?sleep=300

    private WebClient client = WebClient.create("http://localhost:9000");
//    private WebClient client = WebClient.create("https://mighty-river-19883.herokuapp.com");

    @GetMapping("/client")
    public Mono<String> getData() {
        return client.get()
                .uri("/routing?sleep=200")
                .accept(APPLICATION_JSON)
                .exchange().timeout(Duration.ofSeconds(30))
                .then(response -> response.bodyToMono(String.class));
    }
}
