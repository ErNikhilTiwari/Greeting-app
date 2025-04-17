package com.example.GreetingApp.Controllers;


import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.repository.GreetingRepository;
import com.example.GreetingApp.services.GreetingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private GreetingRepository Service;

    @Autowired
    private GreetingServiceImpl greetingService;
    @Autowired
    private GreetingServiceImpl greetingServiceImpl;

    @PostMapping("/save")
    public Greeting saveGreeting(@RequestParam String message) {
        return greetingServiceImpl.saveGreeting(message);
    }

    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingServiceImpl.getAllGreetings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGreeting(@PathVariable Long id) {
        if (greetingService.getGreetingById(id).isPresent()) {
            greetingService.deleteGreetingById(id);
            return ResponseEntity.ok("Greeting with ID " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Greeting with ID " + id + " not found.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Greeting> updateGreeting(@PathVariable Long id, @RequestParam String message) {
        return greetingService.updateGreeting(id, message)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
