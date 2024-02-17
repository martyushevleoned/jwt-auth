package com.example.securityjwt;

import com.example.securityjwt.config.SecurityConfig;
import com.example.securityjwt.model.entity.Role;
import com.example.securityjwt.model.entity.User;
import com.example.securityjwt.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class SecurityJwtApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtApplication.class, args);
    }


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public void run(String... args) throws Exception {

        User admin = new User(
                "adminEmail",
                "admin",
                securityConfig.passwordEncoder().encode("password")
        );
        admin.setRoles(Set.of(Role.ADMIN));

        userRepository.save(admin);
    }
}
