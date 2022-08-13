-- // create role_permissions
-- Migration SQL that makes the change goes here.

CREATE TABLE role_permissions
(
    id bigint UNSIGNED AUTO_INCREMENT,
    role_id       bigint(64) NOT NULL COMMENT '角色主键',
    permission_id bigint(64) NOT NULL COMMENT '权限主键',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色权限关系表';

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE roles;
