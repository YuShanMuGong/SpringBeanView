package com.mu.sview.service.impl;

import com.mu.sview.service.IServiceB;
import com.mu.sview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private String name = "this is test";

    @Autowired
    private IServiceB serviceB;

    @Override
    public String getName() {
        return name;
    }
}
