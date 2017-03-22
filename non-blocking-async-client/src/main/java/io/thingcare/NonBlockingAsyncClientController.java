package io.thingcare;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

@RestController
public class NonBlockingAsyncClientController {

    private AsyncRestTemplate restTemplate = new AsyncRestTemplate();

    @GetMapping("/client")
    public ListenableFuture<ResponseEntity<String>> getData() {
        return restTemplate.getForEntity("http://localhost:9000/routing", String.class);
    }
}
