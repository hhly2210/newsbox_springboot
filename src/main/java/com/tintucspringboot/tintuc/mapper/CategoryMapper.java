package com.tintucspringboot.tintuc.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.tintucspringboot.tintuc.dto.CategoryDto;
import com.tintucspringboot.tintuc.model.Category;
import com.tintucspringboot.tintuc.respository.CategoryRepository;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    @Autowired
    private CategoryRepository repository;
    @Mapping(target = "parent" , source = "parentId", qualifiedByName = "mapParent")
    public abstract Category convert(CategoryDto src);

    @Named("mapParent")
    public Category mapParent(Optional<Integer> id){
        if (id.isEmpty()) {
            return null;
        }
        return repository.getReferenceById(id.get());
    }
}
