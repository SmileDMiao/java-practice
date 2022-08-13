package com.knight.javaPractice.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.StrUtil;
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

import javax.persistence.criteria.Predicate;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

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
    public Page<User> findByCondition(Integer page, Integer size, String sort_name, String username, String phone, String email) {
        Sort sort = Sort.by(Sort.Direction.DESC, sort_name);
        Pageable pageable = PageRequest.of(1, 10, sort);

        return userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (!StrUtil.hasBlank(username)) {
                predicates.add(criteriaBuilder.equal(root.get("username"), username));
            }

            if (!StrUtil.hasBlank(phone)) {
                predicates.add(criteriaBuilder.like(root.get("phone"), phone));
            }

            if (!StrUtil.hasBlank(email)) {
                predicates.add(criteriaBuilder.equal(root.get("email"), email));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }, pageable);
    }
}
