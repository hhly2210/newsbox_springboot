package com.tintucspringboot.tintuc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.tintucspringboot.tintuc.model.Category;
import com.tintucspringboot.tintuc.respository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatagoryService {
    @Autowired
    private CategoryRepository repository;
    public Page<Category> getCategories(int page) {
        return repository.getPage(Pageable.ofSize(Math.round(repository.count()/4)).withPage(page)); 
    }

    public List<Category> getParent() {
        return repository.findAllByParentIsNull(); 
    }
}
