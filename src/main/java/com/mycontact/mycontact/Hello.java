package com.mycontact.mycontact;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Hello {

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }
}
