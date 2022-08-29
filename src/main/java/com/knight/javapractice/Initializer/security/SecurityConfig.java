package com.knight.javaPractice.initializer.security;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootConfiguration
public class SecurityConfig {

    private final IgnoreUrlConfig ignoreUrlConfig;

    private final RequestAccessDeniedHandler requestAccessDeniedHandler;

    private final RequestUnauthorized requestUnauthorized;

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    private final PermissionSecurityFilter permissionSecurityFilter;

    public SecurityConfig(IgnoreUrlConfig ignoreUrlConfig,
                          RequestAccessDeniedHandler requestAccessDeniedHandler,
                          RequestUnauthorized requestUnauthorized,
                          JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter,
                          PermissionSecurityFilter permissionSecurityFilter) {
        this.ignoreUrlConfig = ignoreUrlConfig;
        this.requestAccessDeniedHandler = requestAccessDeniedHandler;
        this.requestUnauthorized = requestUnauthorized;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.permissionSecurityFilter = permissionSecurityFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();

        // 不需要保护的资源路径允许访问
        for (String url : ignoreUrlConfig.getUrls()) {
            registry.antMatchers(url).permitAll();
        }

        // 允许跨域请求的OPTIONS请求
        registry.antMatchers(HttpMethod.OPTIONS).permitAll();

        // 由于使用的是JWT，我们这里不需要csrf
        // 基于token，所以不需要session
        // 除上面外的所有请求全部需要鉴权认证
        registry.and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(requestAccessDeniedHandler)
                .authenticationEntryPoint(requestUnauthorized)
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(permissionSecurityFilter, FilterSecurityInterceptor.class);

        return httpSecurity.build();
    }

}