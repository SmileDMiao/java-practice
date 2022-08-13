package com.knight.javaPractice.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.knight.javaPractice.entity.base.BaseModel;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseModel {

    @NotBlank(message = "用户名不可为空")
    private String username;

    @NotBlank(message = "密码不可为空")
    @JsonIgnore
    private transient String password;

    private String nickname;

    private String phone;

    @Email
    private String email;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
    )
    Set<Permission> permissions;

}
