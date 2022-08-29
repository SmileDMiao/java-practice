package com.knight.javaPractice.service;

import java.util.List;
import java.util.Optional;

import com.knight.javaPractice.controller.payload.user.LoginRequest;
import com.knight.javaPractice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    List<User> findAll();

    User selectById(Long id);

    void saveUser(User user);

    int deleteById(Long id);

    String login(LoginRequest loginRequest);

    UserDetails loadUserByUsername(String username);

    List<User> jpaFindAll();

    Optional<User> jpaFindById(Long id);

    List<User> jpaFindByUserName(String userName);

    List<User> jpaFindByUserNameLike(String userName);

    Page<User> jpaFindByCondition(Integer page, Integer size, String sort, String username, String phone, String email);

    Page<User> jpaFindByJoin(Integer page, Integer size, String sort, String username, String roleName);

}
