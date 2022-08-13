package com.knight.javaPractice.repository;

import com.knight.javaPractice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    public List<User> findByUsername(String name);

    public List<User> findByUsernameLike(String name);

}
