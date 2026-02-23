package com.sysinteg.jotify.controller;

import com.sysinteg.jotify.model.User;
import com.sysinteg.jotify.service.JwtService;
import com.sysinteg.jotify.service.UserService;
import com.sysinteg.jotify.service.AuthService;
import com.sysinteg.jotify.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    //private final AuthService authService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request){
        User user = userService.register(request);
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        User user = userService.login(request.getEmail(), request.getPassword());
        String token = jwtService.generateToken(user.getEmail());
        return ResponseEntity.ok(Map.of("token",token));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request){
//        String token = authService.login(request);
//        return ResponseEntity.ok(token);
//    }
}
