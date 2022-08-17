package com.knight.javaPractice.Initializer;

import java.util.Date;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import com.knight.javaPractice.entity.base.BaseEntity;

@Component("MybatisAuditInterceptor")
@Intercepts({@Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class})})
public class MybatisAuditInterceptor implements Interceptor {

        @Override
        public Object intercept(Invocation invocation) throws Throwable {

            Object[] args = invocation.getArgs();
            SqlCommandType sqlCommandType = null;

            for (Object object : args) {
                // 从MappedStatement参数中获取到操作类型
                if (object instanceof MappedStatement) {
                    MappedStatement ms = (MappedStatement) object;
                    sqlCommandType = ms.getSqlCommandType();
                    continue;
                }

                // 判断参数是否是BaseEntity类型
                // 一个参数
                if (object instanceof BaseEntity) {
                    if (SqlCommandType.INSERT == sqlCommandType) {
                        Date currentTime = new Date();
                        ((BaseEntity) object).setCreatedAt(currentTime);
                        ((BaseEntity) object).setUpdatedAt(currentTime);
                         continue;
                    }
                    if (SqlCommandType.UPDATE == sqlCommandType) {
                        Date currentTime = new Date();
                        ((BaseEntity) object).setUpdatedAt(currentTime);
                         continue;
                    }
                }

                // 参数使用了注解@Param
                if (object instanceof MapperMethod.ParamMap) {
                    MapperMethod.ParamMap<Object> parasMap = (MapperMethod.ParamMap<Object>) object;

                    for(String key : parasMap.keySet()) {
                        if (parasMap.get(key) instanceof BaseEntity){
                            if (SqlCommandType.INSERT == sqlCommandType) {
                                Date currentTime = new Date();
                                ((BaseEntity) parasMap.get(key)).setCreatedAt(currentTime);
                                ((BaseEntity) parasMap.get(key)).setUpdatedAt(currentTime);
                            }
                            if (SqlCommandType.UPDATE == sqlCommandType) {
                                Date currentTime = new Date();
                                ((BaseEntity) parasMap.get(key)).setUpdatedAt(currentTime);
                            }
                        }
                    }
                }
            }
            return invocation.proceed();
        }

}
