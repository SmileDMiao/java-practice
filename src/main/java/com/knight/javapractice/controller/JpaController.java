package com.knight.javaPractice.controller;

import com.knight.javaPractice.entity.User;
import com.knight.javaPractice.controller.concern.ResultData;
import com.knight.javaPractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jpa")

public class JpaController {

    private final UserService userService;

    @Autowired
    public JpaController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public ResultData<List<User>> findAll() {
        userService.jpaFindAll();

        userService.jpaFindById(1L);

        userService.jpaFindByUserName("Hello");

        userService.jpaFindByUserNameLike("Hello");

        Page<User> users = userService.jpaFindByJoin(1, 10, "createdAt", "malzahar", "aa");

        return ResultData.success(users.getContent());
    }
}
