package com.samuel.modesto.course.services.impl;

import com.samuel.modesto.course.repositories.ModuleRepository;
import com.samuel.modesto.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;
}
