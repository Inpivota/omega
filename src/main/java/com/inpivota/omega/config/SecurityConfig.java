package com.inpivota.omega.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final JwtTokenProvider tokenProvider;
//    private DefaultUserDetailsService userDetailsService;
//    private JwtAuthenticationEntryPoint unauthorizedHandler;
//
//    @Autowired
//    public SecurityConfig(
//            final DefaultUserDetailsService userDetailsService,
//            final JwtTokenProvider tokenProvider,
//            final JwtAuthenticationEntryPoint unauthorizedHandler) {
//
//        this.userDetailsService = userDetailsService;
//        this.tokenProvider = tokenProvider;
//        this.unauthorizedHandler = unauthorizedHandler;
//    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll();
    }
}
