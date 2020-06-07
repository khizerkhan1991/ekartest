package ekar.test.controller;

import ekar.test.service.impl.CounterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/count")
public class TestController {

    @Autowired
    private CounterServiceImpl counterService;

    @PutMapping
    public ResponseEntity<?> addThreads(@RequestParam(value = "producer", defaultValue = "0") Integer producer,
                                        @RequestParam(value = "consumer", defaultValue = "0") Integer consumer) {

        counterService.updateCounter(producer, consumer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{count}")
    public ResponseEntity<?> save(@PathVariable Integer count) {

        counterService.setCounter(count);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
