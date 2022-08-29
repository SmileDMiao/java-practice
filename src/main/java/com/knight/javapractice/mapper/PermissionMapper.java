package com.knight.javaPractice.mapper;

import com.knight.javaPractice.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PermissionMapper {

    List<Permission> selectListByRoleId(@Param("roleId") Long roleId);

    List<Permission> selectList();

}
