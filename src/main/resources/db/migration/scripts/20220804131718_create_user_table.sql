-- // create user table
-- Migration SQL that makes the change goes here.
-- Table Definition
CREATE TABLE IF NOT EXISTS users (
    id bigint UNSIGNED AUTO_INCREMENT,
    username text COMMENT '用户登录名' ,
    password text COMMENT '用户登录密码',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE users;
