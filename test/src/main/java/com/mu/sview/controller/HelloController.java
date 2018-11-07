package com.mu.sview.controller;

import com.mu.sview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/show")
    public String show() {
        return userService.getName();
    }

}
