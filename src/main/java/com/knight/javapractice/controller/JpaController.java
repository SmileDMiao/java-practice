package com.knight.javaPractice.controller;

import com.knight.javaPractice.entity.User;
import com.knight.javaPractice.helper.ResultData;
import com.knight.javaPractice.repository.UserRepository;
import com.knight.javaPractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jpa")

public class JpaController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResultData<String> findAll() {

//        userRepository.findAll();

//        userRepository.findById(1L);

//        userRepository.findByUsername("Hello");

//        userRepository.findByUsernameLike("Hello");
//
//        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
//        Pageable pageable = PageRequest.of(1, 10, sort);
//
//
//        userRepository.findAll(pageable);

        userService.findByCondition(1, 10, "createdAt", "malzahar", "111", "123131");


        return ResultData.success("OK");
    }
}
