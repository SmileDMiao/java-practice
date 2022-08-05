package com.knight.javapractice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knight.javapractice.entity.User;
import com.knight.javapractice.mapper.UserMapper;
import com.knight.javapractice.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
  @Autowired
  private UserMapper userMapper;

  @Override
  public List<User> findAll() {
    return userMapper.findAll();
  }

  @Override
  public User selectById(Long id) {
    return userMapper.selectById(id);
  }

  @Override
  public int saveUser(User user) {
    return userMapper.saveUser(user);
  }

  @Override
  public int deleteById(Long id) {
    return userMapper.deleteById(id);
  }
}
