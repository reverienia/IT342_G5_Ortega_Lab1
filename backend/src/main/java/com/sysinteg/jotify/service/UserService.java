package com.sysinteg.jotify.service;

import com.sysinteg.jotify.dto.RegisterRequest;
import com.sysinteg.jotify.model.User;

public class UserService {
    User register(RegisterRequest request);
    User login(String email, String password);
}
