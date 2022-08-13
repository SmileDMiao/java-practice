-- // create user table
-- Migration SQL that makes the change goes here.
-- Table Definition

CREATE TABLE IF NOT EXISTS users (
    id bigint UNSIGNED AUTO_INCREMENT,
    username text COMMENT '用户登录名' ,
    password text COMMENT '用户登录密码',
    nickname varchar(255) COMMENT '昵称',
    phone varchar(11) COMMENT '手机',
    email varchar(50) COMMENT '邮箱',
    role_id bigint COMMENT '角色ID',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户表';

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE users;
