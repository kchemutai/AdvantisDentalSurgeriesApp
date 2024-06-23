package miu.edu.ADS.controller;

import miu.edu.ADS.dto.test.MyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api")
public class TestController {
    @PostMapping
    public ResponseEntity<String> handleDateTime(@RequestBody MyRequest myRequest) {
        LocalDate date = myRequest.getDate();
        LocalTime time = myRequest.getTime();
        System.out.println("date: " + date + " time: " + time);
        // Handle the date and time
        return ResponseEntity.ok("Received date: " + date.toString() + " and time: " + time.toString());
    }
}
