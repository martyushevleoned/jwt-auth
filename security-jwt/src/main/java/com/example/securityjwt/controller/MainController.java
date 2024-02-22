package com.example.securityjwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
public class MainController {

    @CrossOrigin
    @PostMapping("/unsecured")
    public String unsecuredData(){
        return "Unsecured Data";
    }

    @CrossOrigin
    @PostMapping("/secured")
    public String securedData(){
        return "Secured Data";
    }

    @CrossOrigin
    @PostMapping("/admin")
    public String adminData(){
        return "Admin Data";
    }

    @CrossOrigin
    @PostMapping("/info")
    public String userData(Principal principal){
        return principal.getName();
    }
}