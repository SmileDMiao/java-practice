package com.knight.javaPractice.controller;

import com.knight.javaPractice.helper.ResultData;
import com.knight.javaPractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")

public class JpaController {

    private final UserService userService;

    @Autowired
    public JpaController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public ResultData<String> findAll() {
        userService.jpaFindAll();

        userService.jpaFindById(1L);

        userService.jpaFindByUserName("Hello");

        userService.jpaFindByUserNameLike("Hello");

        return ResultData.success("ok");
    }
}
