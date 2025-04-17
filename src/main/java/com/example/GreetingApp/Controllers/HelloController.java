package com.example.GreetingApp.Controllers;


import com.example.GreetingApp.model.Greeting;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Hello")
public class HelloController {

    // this is get map - Read request
    @RequestMapping(value ={"/query"}, method = RequestMethod.GET)
    public String SayHello(@RequestParam(defaultValue = "User") String name) {
        return "Hello " + name + "!";
    }







}
