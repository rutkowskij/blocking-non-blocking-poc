package io.thingcare;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@RestController
public class BlockingServiceController {

    @RequestMapping(value = "/{resource}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getData(@PathVariable String resource, @RequestParam int sleep) throws InterruptedException {
        UUID uuid= UUID.randomUUID();
//        log.info("blocking service called " + uuid);
        Thread.sleep(sleep);
        String result = format("%s result: %s", resource, uuid);
//        log.info("result :" + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
