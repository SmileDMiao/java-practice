package com.knight.javaPractice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cn.hutool.core.util.StrUtil;

import com.knight.javaPractice.controller.payload.user.LoginRequest;
import com.knight.javaPractice.entity.Role;
import com.knight.javaPractice.initializer.security.CurrentDetails;
import com.knight.javaPractice.initializer.security.JwtToken;
import com.knight.javaPractice.mapper.PermissionMapper;
import com.knight.javaPractice.repository.UserRepository;
import com.knight.javaPractice.entity.User;
import com.knight.javaPractice.mapper.UserMapper;
import com.knight.javaPractice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PermissionMapper permissionMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtToken jwtToken;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtToken jwtToken,
                           PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtToken = jwtToken;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userMapper.saveUser(user);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        UserDetails userDetails = loadUserByUsername(loginRequest.getUsername());

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtToken.generateToken(userDetails);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = selectByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return new CurrentDetails(user, permissionMapper.selectListByRoleId(user.getRoleId()));
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
    public List<User> jpaFindByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public List<User> jpaFindByUserNameLike(String userName) {
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
