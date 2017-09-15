package io.thingcare;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.http.MediaType.TEXT_PLAIN;

@RestController
public class NonBlockingClientController {
    private WebClient client = WebClient.create("http://localhost:9999");

    @GetMapping("/client")
    public Mono<String> getData() {
        return client.get()
                .uri("/routing")
                .accept(TEXT_PLAIN)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
    }
}
