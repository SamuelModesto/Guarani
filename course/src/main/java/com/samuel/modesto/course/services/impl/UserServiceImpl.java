package com.samuel.modesto.course.services.impl;

import com.samuel.modesto.course.repositories.UserRepository;
import com.samuel.modesto.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
}
