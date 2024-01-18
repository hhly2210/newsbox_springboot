package com.tintucspringboot.tintuc.mapper;

import java.util.Optional;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.tintucspringboot.tintuc.dto.CategoryEditDto;
import com.tintucspringboot.tintuc.model.Category;
import com.tintucspringboot.tintuc.respository.CategoryRepository;

@Mapper(componentModel = "spring")
public abstract class CategoryEditMapper {
    @Autowired
    CategoryRepository repository;

    @Mapping(target = "parent", source = "parentId", qualifiedByName = "convertParent")
    public abstract void patch(CategoryEditDto src, @MappingTarget Category target);

    @Named("convertParent")
    public Category convertParent(Optional<Integer> parent) {
        if (parent.isEmpty()) {
            return null;
        }
        return repository.findById(parent.get()).orElse(null);
    }
}
