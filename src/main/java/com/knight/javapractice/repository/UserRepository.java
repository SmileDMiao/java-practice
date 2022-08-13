package com.knight.javaPractice.repository;

import com.knight.javaPractice.entity.Role;
import com.knight.javaPractice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Join;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    public List<User> findByUsername(String name);

    public List<User> findByUsernameLike(String name);

//    Page<User> findAll(Pageable pageable);

}
