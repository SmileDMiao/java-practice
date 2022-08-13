package com.knight.javaPractice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knight.javaPractice.entity.User;
import com.knight.javaPractice.helper.ResultData;
import com.knight.javaPractice.repository.UserRepository;
import com.knight.javaPractice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResultData<List<User>> findAll() {
        List<User> users;
        users = userService.findAll();

        return ResultData.success(users);
    }

    @PostMapping()
    public ResultData<String> create(@Valid @RequestBody User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userService.saveUser(user);

        return ResultData.success("OK");
    }

    @GetMapping("/{id}")
    public ResultData<User> show(@PathVariable Long id) {
        User user = userService.selectById(id);

        return ResultData.success(user);
    }

    @DeleteMapping("/{id}")
    public ResultData<String> delete(@PathVariable Long id) {
        userService.deleteById(id);

        return ResultData.success("OK");
    }
}
