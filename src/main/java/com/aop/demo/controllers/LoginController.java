package com.aop.demo.controllers;

import com.aop.demo.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String userName,
                        @RequestParam String password) {
        return loginService.login(userName, password);
    }
}
