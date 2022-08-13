package com.knight.javaPractice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.knight.javaPractice.entity.base.BaseModel;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permissions")
public class Permission extends BaseModel {

    private String name;

    private String url;

    private Integer type;

    private String permission;

    private String method;

    private Long parentId;

}
