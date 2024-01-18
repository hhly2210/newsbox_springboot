package com.tintucspringboot.tintuc.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.tintucspringboot.tintuc.dto.UserRegisterDto;
import com.tintucspringboot.tintuc.model.User;
import com.tintucspringboot.tintuc.respository.UserRepository;

@Mapper(componentModel = "spring")
public abstract class UserRegisterMapper {
    @Autowired
    UserRepository repository;

    @Autowired
    public abstract User convert(UserRegisterDto src);
}
