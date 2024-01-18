package com.tintucspringboot.tintuc.mapper;

import java.io.IOException;
import java.util.Optional;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.tintucspringboot.tintuc.dto.PostApproveDto;
import com.tintucspringboot.tintuc.dto.PostEditDTO;
import com.tintucspringboot.tintuc.model.Category;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.utils.FileManager;

@Mapper(componentModel = "spring")
public abstract class PostApproveMapper {
    @Autowired
    FileManager fileManager;

    public abstract Post convert(PostApproveDto src);

    Category convert(Optional<Integer> src) {
        if (src.isEmpty()) {
            return null;
        }
        return Category.builder().id(src.get()).build();
    }

    // String convert(MultipartFile src) throws IOException{
    // return fileManager.saveFile(src);
    // }
    @AfterMapping
    public void mapPost(PostApproveDto dto, @MappingTarget Post post) throws IOException {
        if (!dto.imageFile().isEmpty()) {
            post.setImage(fileManager.saveFile(dto.imageFile()));
        }
    }

    public boolean convertActive(Optional<Boolean> active) {
        return active.orElse(false);
    }
}
