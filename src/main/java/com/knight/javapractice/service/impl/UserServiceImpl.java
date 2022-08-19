package com.knight.javaPractice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cn.hutool.core.util.StrUtil;
import com.knight.javaPractice.entity.Role;
import com.knight.javaPractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.knight.javaPractice.entity.User;
import com.knight.javaPractice.mapper.UserMapper;
import com.knight.javaPractice.service.UserService;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

@Service("userService")
public class UserServiceImpl implements UserService {

    private  final UserMapper userMapper;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

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

    @Override
    public List<User> jpaFindAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> jpaFindById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> jpaFindByUserName(String userName){
        return userRepository.findByUsername(userName);
    }

    public List<User> jpaFindByUserNameLike(String userName){
        return userRepository.findByUsernameLike(userName);
    }

    // Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
    // Pageable pageable = PageRequest.of(1, 10, sort);
    // userRepository.findAll(pageable);
    // UserService.findByCondition(1, 10, "createdAt", "Hello", "111", "123131");
    @Override
    public Page<User> jpaFindByCondition(Integer page, Integer size, String sort_name, String username, String phone, String email) {
        Sort sort = Sort.by(Sort.Direction.DESC, sort_name);
        Pageable pageable = PageRequest.of(1, 10, sort);

        return userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StrUtil.hasBlank(username)) {
                predicates.add(criteriaBuilder.equal(root.get("username"), username));
            }

            if (!StrUtil.hasBlank(phone)) {
                predicates.add(criteriaBuilder.like(root.get("phone"), phone));
            }

            if (!StrUtil.hasBlank(email)) {
                predicates.add(criteriaBuilder.equal(root.get("email"), email));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[0])).getRestriction();
        }, pageable);
    }

    @Override
    public Page<User> jpaFindByJoin(Integer page, Integer size, String sort_name, String username, String roleName) {
        Sort sort = Sort.by(Sort.Direction.DESC, sort_name);
        Pageable pageable = PageRequest.of(0, 10, sort);

        return userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<User, Role> roleJoin = root.join("role", JoinType.LEFT);


            if (!StrUtil.hasBlank(username)) {
                predicates.add(criteriaBuilder.equal(root.get("username"), username));
            }

            if (!StrUtil.hasBlank(roleName)) {
                predicates.add(criteriaBuilder.equal(roleJoin.get("name"), roleName));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[0])).getRestriction();
        }, pageable);
    }
}
