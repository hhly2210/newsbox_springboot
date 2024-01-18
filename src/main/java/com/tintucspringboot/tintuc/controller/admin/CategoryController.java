package com.tintucspringboot.tintuc.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.tintucspringboot.tintuc.dto.CategoryDto;
import com.tintucspringboot.tintuc.dto.CategoryEditDto;
import com.tintucspringboot.tintuc.mapper.CategoryEditMapper;
import com.tintucspringboot.tintuc.mapper.CategoryMapper;
import com.tintucspringboot.tintuc.model.Category;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.respository.CategoryRepository;
import com.tintucspringboot.tintuc.utils.StringNormalizer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    @Autowired
    private CategoryMapper mapper;

    @Autowired
    private CategoryEditMapper editMapper;

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("contentFragment", "admin/categories/index.html");
        var cat = repository.findAll().toArray();
        model.addAttribute("categories", cat);
        model.addAttribute("modalDelete", "admin/categories/delete.html");
        return "admin/layouts/app";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("contentFragment", "admin/categories/create.html");
        model.addAttribute("categories", repository.findAll());
        return "admin/layouts/app";
    }

    @PostMapping("/create")
    public String creteCategory(@ModelAttribute CategoryDto dto) {
        Category newCategory = mapper.convert(dto); 
        newCategory.setSlug(StringNormalizer.removeAccents(newCategory.getName().replace(" ", "-")));
        repository.save(newCategory);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        Category category = repository.getReferenceById(id);
        model.addAttribute("category", category);
        var cat = repository.findAll();
        cat.removeIf(i -> i.getId() == id);
        model.addAttribute("categories", cat);
        model.addAttribute("contentFragment", "admin/categories/edit.html");
        return "admin/layouts/app";
    }

    // @PostMapping("/edit")
    // public String editCategory(@ModelAttribute CategoryEditDto dto) {
    // Category editCategory = editMapper.convert(dto);
    // Category category = repository.getReferenceById(editCategory.getId());
    // category.setName(editCategory.getName());
    // category.setDescription(editCategory.getDescription());
    // category.setParent(editCategory.getParent());
    // repository.save(category);
    // return "redirect:/admin/categories";
    // }

    @PostMapping("/edit")
    public String editCategory(@ModelAttribute CategoryEditDto dto) {
        Category category = repository.getReferenceById(dto.id());
        editMapper.patch(dto, category);
        category.setSlug(StringNormalizer.removeAccents(category.getName().replace(" ", "-")));
        repository.save(category);
        return "redirect:/admin/categories";
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam int id) {
        repository.deleteById(id);
        return "redirect:/admin/categories";
    }
}
