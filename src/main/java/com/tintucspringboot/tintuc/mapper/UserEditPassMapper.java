package com.tintucspringboot.tintuc.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tintucspringboot.tintuc.dto.UserEditPassDto;
import com.tintucspringboot.tintuc.model.User;

@Mapper(componentModel = "spring")
public abstract class UserEditPassMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public abstract void patch(UserEditPassDto src, @MappingTarget User target);

    @AfterMapping
    protected void convert(UserEditPassDto src, @MappingTarget User target){
        if (passwordEncoder.matches(src.password(), target.getPassword())) {
            target.setPassword(passwordEncoder.encode(src.newpassword()));
        }else{
            throw new RuntimeException();
        }
    }
}
