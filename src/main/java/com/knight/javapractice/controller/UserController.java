package com.knight.javaPractice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knight.javaPractice.entity.User;
import com.knight.javaPractice.controller.payload.user.LoginRequest;
import com.knight.javaPractice.controller.concern.ResultData;
import com.knight.javaPractice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResultData<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);

        return ResultData.success(tokenMap);
    }

    @PostMapping("/logout")
    public ResultData<String> logout() {
        return ResultData.success("OK");
    }

    @GetMapping()
    public ResultData<List<User>> findAll() {
        List<User> users;
        users = userService.findAll();

        return ResultData.success(users);
    }

    @PostMapping()
    public ResultData<String> create(@Valid @RequestBody User user) {
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
