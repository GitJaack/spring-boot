package com.mycontact.mycontact.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public String me(Principal principal) {
        return "Bonjour " + principal.getName();
    }
}
