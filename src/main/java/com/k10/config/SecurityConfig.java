package com.k10.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // in memory authentication.
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication().withUser(users.username("user1").password("test").roles("ADMIN"))
                .withUser(users.username("user2").password("test").roles("READ"));

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/get/**")
                .hasRole("MANAGER")
                .antMatchers("/edit/**")
                .hasRole("ADMIN")
                .antMatchers("/delete/**")
                .hasRole("ADMIN")
                .anyRequest().authenticated()//adding this at the beginning wont work.
                .and()
                .httpBasic();

    }

}
