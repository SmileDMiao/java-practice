package com.knight.javaPractice.initializer.security;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootConfiguration
public class CommonSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IgnoreUrlConfig ignoreUrlsConfig() {
        return new IgnoreUrlConfig();
    }

    @Bean
    public JwtToken jwtTokenUtil() {
        return new JwtToken();
    }

    @Bean
    public RequestAccessDeniedHandler requestAccessDeniedHandler() {
        return new RequestAccessDeniedHandler();
    }

    @Bean
    public RequestUnauthorizedHandler requestUnauthorizedHandler() {
        return new RequestUnauthorizedHandler();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public PermissionSecurityFilter permissionSecurityFilter() {
        return new PermissionSecurityFilter();
    }

    @Bean
    public PermissionAccessDecisionManager permissionAccessDecisionManager() {
        return new PermissionAccessDecisionManager();
    }

    @Bean
    public PermissionSecurityMetadataSource permissionSecurityMetadataSource() {
        return new PermissionSecurityMetadataSource();
    }
}
