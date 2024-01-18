package com.tintucspringboot.tintuc.mapper;

import java.io.IOException;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.tintucspringboot.tintuc.dto.PostPostDTO;
import com.tintucspringboot.tintuc.model.Category;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.respository.CategoryRepository;
import com.tintucspringboot.tintuc.utils.FileManager;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FileManager fileManager;

    public abstract Post convert(PostPostDTO src);

    Category convert(Optional<Integer> src){
        if (src.isEmpty()) {
            return null;
        }
        return Category.builder().id(src.get()).build();
        // return categoryRepository.getReferenceById(src.get());
    }

    String convert(MultipartFile src) throws IOException{
        return fileManager.saveFile(src);
    }
}
