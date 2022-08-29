package com.knight.javaPractice.service.impl;

import com.knight.javaPractice.entity.Permission;
import com.knight.javaPractice.entity.User;
import com.knight.javaPractice.mapper.PermissionMapper;
import com.knight.javaPractice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissions() {

        return permissionMapper.selectList();
    }
}
