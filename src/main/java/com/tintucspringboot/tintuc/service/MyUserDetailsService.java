package com.tintucspringboot.tintuc.service;

import com.tintucspringboot.tintuc.config.CustomUserDetails;
import com.tintucspringboot.tintuc.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("User not found");
        return new CustomUserDetails(user.get());
    }
}
