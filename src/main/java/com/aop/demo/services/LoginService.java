package com.aop.demo.services;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public String login(String username, String password) {
        // on successful login return a valid accessKey for the user
        // this is DIY Functionality
        return "SUCCESS";
    }
}
