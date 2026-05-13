package com.saraswathi.controller;

import com.saraswathi.dto.UserRequest;
import com.saraswathi.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody UserRequest user) {

        try {
            log.info("username: {}", user.getUsername());
            log.info("password: {}", user.getPassword());

            var authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );

            log.info("Authentication successful");

            String role = authentication.getAuthorities().iterator().next().getAuthority();
            log.info("role: {}", role);

            String token = jwtUtil.generateToken(user.getUsername(), role);
            log.info("Generated token: {}", token);

            return token;

        } catch (Exception e) {
            log.error("Authentication failed", e);
            throw e;
        }

        // 🔥 Extract role from authenticated user



    }
}