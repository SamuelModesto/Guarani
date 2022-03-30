package com.samuel.authuser.services.impl;

import com.samuel.authuser.repositories.UserRepository;
import com.samuel.authuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

}
