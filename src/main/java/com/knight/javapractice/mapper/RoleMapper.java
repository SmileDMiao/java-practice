package com.knight.javaPractice.mapper;

import com.knight.javaPractice.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoleMapper {

    List<Role> selectById(@Param("id") Long id);

}