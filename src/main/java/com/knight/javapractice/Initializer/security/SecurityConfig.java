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

    private final RequestUnauthorizedHandler requestUnauthorizedHandler;

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    private final PermissionSecurityFilter permissionSecurityFilter;

    public SecurityConfig(IgnoreUrlConfig ignoreUrlConfig,
                          RequestAccessDeniedHandler requestAccessDeniedHandler,
                          RequestUnauthorizedHandler requestUnauthorizedHandler,
                          JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter,
                          PermissionSecurityFilter permissionSecurityFilter) {
        this.ignoreUrlConfig = ignoreUrlConfig;
        this.requestAccessDeniedHandler = requestAccessDeniedHandler;
        this.requestUnauthorizedHandler = requestUnauthorizedHandler;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.permissionSecurityFilter = permissionSecurityFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();

        // ??????????????????????????????????????????
        for (String url : ignoreUrlConfig.getUrls()) {
            registry.antMatchers(url).permitAll();
        }

        // ?????????????????????OPTIONS??????
        registry.antMatchers(HttpMethod.OPTIONS).permitAll();

        // ??????????????????JWT????????????????????????csrf
        // ??????token??????????????????session
        // ???????????????????????????????????????????????????
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
                .authenticationEntryPoint(requestUnauthorizedHandler)
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(permissionSecurityFilter, FilterSecurityInterceptor.class);

        return httpSecurity.build();
    }

}