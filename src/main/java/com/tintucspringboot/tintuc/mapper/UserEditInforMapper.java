package com.tintucspringboot.tintuc.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.tintucspringboot.tintuc.dto.UserEditDto;
import com.tintucspringboot.tintuc.dto.UserEditInforDto;
import com.tintucspringboot.tintuc.model.Role;
import com.tintucspringboot.tintuc.model.User;

@Mapper(componentModel = "spring")
public abstract class UserEditInforMapper {
    public abstract void patch(UserEditInforDto src, @MappingTarget User target);
}
