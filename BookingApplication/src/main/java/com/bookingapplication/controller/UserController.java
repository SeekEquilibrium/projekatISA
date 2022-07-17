package com.bookingapplication.controller;

import com.bookingapplication.dto.MyInfoDTO;
import com.bookingapplication.model.UserApp;
import com.bookingapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/whoami")
    public  MyInfoDTO getMyInfo(Principal user) {
        UserApp userApp = userService.FindUserByUsername(user.getName());
        return new MyInfoDTO(userApp);
    }
}
