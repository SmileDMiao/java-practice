package com.knight.javaPractice.entity;

import javax.persistence.*;

import com.knight.javaPractice.entity.base.BaseModel;

import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
@ToString(callSuper = true)
public class Role extends BaseModel {

    private String name;

    private String description;

}
