package io.thingcare;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class BlockingServiceClientController {
    private final RestTemplate restTemplate;

    @Autowired
    public BlockingServiceClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/client")
    public ResponseEntity<String> getData() throws InterruptedException {
//        final String result = restTemplate.getForObject("https://mighty-river-19883.herokuapp.com/routing?sleep=200", String.class);
        final String result = restTemplate.getForObject("http://localhost:8000/routing?sleep=200", String.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
