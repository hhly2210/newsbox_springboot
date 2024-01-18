package com.tintucspringboot.tintuc.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.tintucspringboot.tintuc.dto.UserDto;
import com.tintucspringboot.tintuc.model.Role;
import com.tintucspringboot.tintuc.model.User;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    public abstract User convert(UserDto src);
    Role convert(Optional<Integer> src){
        if (src.isEmpty()) {
            return null;
        }
        return Role.builder().id(src.get()).build();
    }
}
