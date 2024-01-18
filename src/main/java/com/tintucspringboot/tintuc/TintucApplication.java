package com.tintucspringboot.tintuc;

import com.tintucspringboot.tintuc.config.CustomUserDetails;
import com.tintucspringboot.tintuc.service.AdminMenuService;
import com.tintucspringboot.tintuc.service.MyUserDetailsService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaAuditing
public class TintucApplication {
    @Bean(name = "menuService")
    public AdminMenuService adminMenuService(AdminMenuService service) {

        return service;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MyUserDetailsService userDetailsService)
            throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService);
        var authenticationManager = authenticationManagerBuilder.build();

        http
                .anonymous(cfg -> cfg.disable())
                .authorizeHttpRequests(cfg -> {
                    cfg.requestMatchers("/admin/**").hasAnyRole("ADMIN", "EDITOR");
                    cfg.anyRequest().permitAll();
                }).csrf(cfg -> {
                    cfg.disable();
                }).authenticationManager(authenticationManager);
        http.formLogin(cfg -> {
            cfg.loginPage("/login").usernameParameter("username").passwordParameter("password");
            cfg.successHandler((request, response, authentication) -> {

                var user = (CustomUserDetails) authentication.getPrincipal();
                request.getSession().setAttribute("userName", user.getName());
                request.getSession().setAttribute("password", user.user.getPassword());
                request.getSession().setAttribute("roleName", user.user.getRole().getName());
                request.getSession().setAttribute("email", user.user.getEmail());

                Map<String, String> map = new HashMap<>();
                map.put("ROLE_ADMIN", "/admin");
                map.put("ROLE_EDITOR", "/admin");
                map.put("ROLE_USER", "/");
                var claims = authentication.getAuthorities();
                response.sendRedirect(map.get(claims.stream().findFirst().orElseThrow().getAuthority()));
            });
            cfg.permitAll();
        });
        return http.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(TintucApplication.class, args);
    }

}
