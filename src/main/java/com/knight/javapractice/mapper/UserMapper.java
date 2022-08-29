package com.knight.javaPractice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.knight.javaPractice.entity.User;

@Mapper
@Component
public interface UserMapper {

    List<User> findAll();

    User selectById(@Param("id") Long id);

    User selectByUsername(@Param("username") String username);

    int saveUser(@Param("user") User user);

    int deleteById(@Param("id") Long id);
}
