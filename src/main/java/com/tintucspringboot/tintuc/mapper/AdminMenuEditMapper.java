package com.tintucspringboot.tintuc.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.tintucspringboot.tintuc.dto.AdminMenuDto;
import com.tintucspringboot.tintuc.dto.AdminMenuEditDto;
import com.tintucspringboot.tintuc.model.AdminMenu;
import com.tintucspringboot.tintuc.respository.MenuAdminAsideRepository;

@Mapper(componentModel = "spring")
public abstract class AdminMenuEditMapper {
    @Autowired
    MenuAdminAsideRepository repository;

    @Mapping(target = "parent", source = "parentId", qualifiedByName = "convertParent")
    public abstract void patch(AdminMenuEditDto src, @MappingTarget AdminMenu target);

    @Named("convertParent")
    public AdminMenu convertParent(Optional<Integer> parent) {
        if (parent.isEmpty()) {
            return null;
        }
        return repository.findById(parent.get()).orElse(null);
    }

    public boolean convertActive(Optional<Boolean> active){
        return active.orElse(false);
    }
}
