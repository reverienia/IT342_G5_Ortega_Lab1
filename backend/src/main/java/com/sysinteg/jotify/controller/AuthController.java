package com.sysinteg.jotify.controller;

import com.sysinteg.jotify.model.User;
import com.sysinteg.jotify.service.UserService;
import com.sysinteg.jotify.dto.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request){
        User user = userService.register(request);
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request){
        User user = userService.login(request.getEmail(), request.getPassword());
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
