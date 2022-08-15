package com.knight.javaPractice.service;

import java.util.List;
import java.util.Optional;

import com.knight.javaPractice.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {

    List<User> findAll();

    User selectById(Long id);

    int saveUser(User user);

    int deleteById(Long id);

    List<User> jpaFindAll();

    Optional<User> jpaFindById(Long id);

    List<User> jpaFindByUserName(String userName);

    List<User> jpaFindByUserNameLike(String userName);

    Page<User> findByCondition(Integer page, Integer size, String sort, String username, String phone, String email);

}
