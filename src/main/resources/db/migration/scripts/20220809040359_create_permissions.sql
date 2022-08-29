-- // create permissions
-- Migration SQL that makes the change goes here.
CREATE TABLE permissions
(
    id         bigint(64)  NOT NULL COMMENT '主键',
    name       varchar(50) NOT NULL COMMENT '权限名',
    url        varchar(1000) DEFAULT NULL COMMENT '接口地址',
    sort       int(11)     NOT NULL COMMENT '排序',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='权限表';

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE permissions;
