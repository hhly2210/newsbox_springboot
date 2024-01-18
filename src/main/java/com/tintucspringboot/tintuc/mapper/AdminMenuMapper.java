package com.tintucspringboot.tintuc.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;

import com.tintucspringboot.tintuc.dto.AdminMenuDto;
import com.tintucspringboot.tintuc.model.AdminMenu;
import com.tintucspringboot.tintuc.model.Category;
import com.tintucspringboot.tintuc.model.Role;
import com.tintucspringboot.tintuc.respository.MenuAdminAsideRepository;
import com.tintucspringboot.tintuc.respository.RoleRepository;

@Mapper(componentModel = "spring")
public abstract class AdminMenuMapper {
    @Autowired
    private MenuAdminAsideRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Mapping(target = "parent", source = "parentId", qualifiedByName = "mapParent")
    public abstract AdminMenu convert(AdminMenuDto src);

    @Named("mapParent")
    public AdminMenu mapParent(Optional<Integer> id) {
        if (id.isEmpty()) {
            return null;
        }
        return repository.getReferenceById(id.get());
    }

    Set<Role> convert(List<Integer> ids){
        if(ids.isEmpty()){
            return null;
        }
        Set<Role> roles = new HashSet<>();
        for (Integer id : ids) {
            Role role = roleRepository.getReferenceById(id);
            roles.add(role);
        }
        return roles;
    }
}
