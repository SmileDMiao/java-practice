-- // create roles
-- Migration SQL that makes the change goes here.

CREATE TABLE roles
(
    id          bigint(64)  NOT NULL COMMENT '主键',
    name        varchar(50) NOT NULL COMMENT '角色名',
    description varchar(100) DEFAULT NULL COMMENT '描述',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY name (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色表';


-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE roles;
