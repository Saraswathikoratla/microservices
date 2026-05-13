package com.saraswathi.controller;

import com.saraswathi.dto.UserRequest;
import com.saraswathi.entity.User;
import com.saraswathi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
   private   UserRepository userRepository;
   @PostMapping("/user")
    public String createUser(@RequestBody UserRequest userRequest)
    {
        User user = new User(userRequest.getUsername(),userRequest.getRole(),userRequest.getPassword());
        userRepository.save(user);
        return  "user saved";
    }
}
