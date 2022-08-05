package com.knight.javapractice.service;

import java.util.List;

import com.knight.javapractice.entity.User;

public interface UserService {
  List<User> findAll();

  User selectById(Long id);

  int saveUser(User user);

  int deleteById(Long id);
}
