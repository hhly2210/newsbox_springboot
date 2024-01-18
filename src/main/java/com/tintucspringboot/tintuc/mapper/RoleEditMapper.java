package com.tintucspringboot.tintuc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.tintucspringboot.tintuc.dto.RoleEditDto;
import com.tintucspringboot.tintuc.model.Role;

@Mapper(componentModel = "spring")
public abstract class RoleEditMapper {
    public abstract void patch(RoleEditDto src, @MappingTarget Role target);
}
