package com.knight.javapractice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.knight.javapractice.entity.User;

@Mapper
@Component
public interface UserMapper {

  @Select("SELECT id, username, created_at, updated_at FROM users")
  List<User> findAll();

  @Select("SELECT * FROM users WHERE id = #{id}")
  User selectById(@Param("id") Long id);

  int saveUser(@Param("user") User user);

  int deleteById(@Param("id") Long id);
}
