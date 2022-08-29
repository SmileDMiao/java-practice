package com.knight.javaPractice.initializer.security;


import com.knight.javaPractice.entity.Permission;
import com.knight.javaPractice.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CurrentDetails implements UserDetails {
    private final User user;

    private final List<Permission> permissions;

    // 用户信息 权限信息
    public CurrentDetails(User user, List<Permission> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    // 将用户权限信息转换为 Spring Security中的权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}