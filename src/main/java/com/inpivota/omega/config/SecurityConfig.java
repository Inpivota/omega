package com.inpivota.omega.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("!https")
//@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
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
//        http.csrf().disable().authorizeRequests()
        http.authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/login.html", "/data/**", "/index*", "/static/**", "/*.js", "/*.json", "/*.ico")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/index.html",true);
//                .failureUrl("/index.html?error=true");
//        http
//                .authorizeRequests()
//                .antMatchers("/css/**", "/index").permitAll()
//                .antMatchers("/user/**").hasRole("USER")
//                .and()
//                .formLogin()
//                .loginPage("/login").failureUrl("/login-error");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
