package com.mu.sview.service.impl;

import com.mu.sview.service.IServiceB;
import com.mu.sview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceBImpl implements IServiceB {

    @Autowired
    private IUserService userService;

    @Override
    public String show() {
        return null;
    }
}
