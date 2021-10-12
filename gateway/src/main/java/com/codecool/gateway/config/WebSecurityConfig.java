package com.codecool.gateway.config;

import com.codecool.gateway.service.CustomLoginSuccessHandler;
import com.codecool.gateway.service.CustomLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomLoginSuccessHandler customLoginSuccessHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and()
                .authorizeRequests()
                .anyRequest().permitAll()
            .and()
                .formLogin()
                    .loginProcessingUrl("/login")
                    .successHandler(customLoginSuccessHandler)
                    .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                    .permitAll()
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .permitAll()
            .and()
                .csrf().disable();
    }
}
