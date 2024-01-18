package com.tintucspringboot.tintuc.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.tintucspringboot.tintuc.dto.UserEditDto;
import com.tintucspringboot.tintuc.model.Role;
import com.tintucspringboot.tintuc.model.User;

@Mapper(componentModel = "spring")
public abstract class UserEditMapper {
    public abstract void patch(UserEditDto src, @MappingTarget User target);
    Role convert(Optional<Integer> src){
        if (src.isEmpty()) {
            return null;
        }
        return Role.builder().id(src.get()).build();
    }
}
