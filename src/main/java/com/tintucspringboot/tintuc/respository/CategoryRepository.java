package com.tintucspringboot.tintuc.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tintucspringboot.tintuc.model.Category;
import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findTop1BySlug(String slug);

    List<Category> findAllByParentIsNull();
    @Query("SELECT C FROM Category C")
    Page<Category> getPage(Pageable page);
}
