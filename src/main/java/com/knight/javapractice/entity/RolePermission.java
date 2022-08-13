package com.knight.javaPractice.entity;

import javax.persistence.*;

import com.knight.javaPractice.entity.base.BaseModel;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role_permissions")
public class RolePermission extends BaseModel {

    @Column(name = "role_id", insertable = false, updatable = false)
    private Long roleId;

    @Column(name = "permission_id", insertable = false, updatable = false)
    private Long permissionId;

    @OneToOne(targetEntity = Role.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToOne(targetEntity = Permission.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private Permission permission;
}
