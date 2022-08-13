package com.knight.javaPractice.service;

import java.util.List;

import com.knight.javaPractice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    List<User> findAll();

    User selectById(Long id);

    int saveUser(User user);

    int deleteById(Long id);

    Page<User> findByCondition(Integer page, Integer size, String sort, String username, String phone, String email);

}
