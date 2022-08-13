-- // create permissions
-- Migration SQL that makes the change goes here.
CREATE TABLE permissions
(
    id         bigint(64)  NOT NULL COMMENT '主键',
    name       varchar(50) NOT NULL COMMENT '权限名',
    url        varchar(1000) DEFAULT NULL COMMENT '类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址',
    type       int(2)      NOT NULL COMMENT '权限类型，页面-1，按钮-2',
    permission varchar(50)   DEFAULT NULL COMMENT '权限表达式',
    method     varchar(50)   DEFAULT NULL COMMENT '后端接口访问方式',
    sort       int(11)     NOT NULL COMMENT '排序',
    parent_id  bigint(64)  NOT NULL COMMENT '父级id',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='权限表';

-- //@UNDO
-- SQL to undo the change goes here.

DROP TABLE permissions;
