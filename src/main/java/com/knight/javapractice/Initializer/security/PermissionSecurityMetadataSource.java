package com.knight.javaPractice.initializer.security;

import cn.hutool.core.util.URLUtil;
import com.knight.javaPractice.entity.Permission;
import com.knight.javaPractice.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PermissionSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        List<ConfigAttribute> configAttributes = new ArrayList<>();

        // 获取当前访问的路径
        String url = ((FilterInvocation) o).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();

        // 获取所有权限
        List<Permission> permissions = permissionMapper.selectList();

        // 转换成 Spring Security 权限
        Map<String, ConfigAttribute> configAttributeMap = new ConcurrentHashMap<>();
        for (Permission permission : permissions) {
            configAttributeMap.put(permission.getUrl(), new org.springframework.security.access.SecurityConfig(permission.getName()));
        }

        // 获取访问该路径所需资源
        for (String pattern : configAttributeMap.keySet()) {
            if (pathMatcher.match(pattern, path)) {
                configAttributes.add(configAttributeMap.get(pattern));
            }
        }

        configAttributes.add(new org.springframework.security.access.SecurityConfig("Fake Permission"));

        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}