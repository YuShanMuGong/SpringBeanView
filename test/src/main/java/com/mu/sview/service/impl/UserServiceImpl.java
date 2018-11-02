package com.mu.sview.service.impl;

import com.mu.sview.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private String name = "this is test";

    @Override
    public String getName() {
        return name;
    }
}
