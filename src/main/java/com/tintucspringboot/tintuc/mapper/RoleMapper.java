package com.tintucspringboot.tintuc.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.tintucspringboot.tintuc.dto.RoleDto;
import com.tintucspringboot.tintuc.model.Role;
import com.tintucspringboot.tintuc.respository.RoleRepository;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {
 
    // @Autowired
    // private RoleRepository repository;

    @Autowired
    public abstract Role convert(RoleDto src);

}
