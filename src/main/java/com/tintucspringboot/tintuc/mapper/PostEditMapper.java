package com.tintucspringboot.tintuc.mapper;

import java.io.IOException;
import java.util.Optional;

import org.aspectj.lang.annotation.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.tintucspringboot.tintuc.dto.PostEditDTO;
import com.tintucspringboot.tintuc.model.Category;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.utils.FileManager;

import lombok.SneakyThrows;

@Mapper(componentModel = "spring")
public abstract class PostEditMapper {
    @Autowired
    FileManager fileManager;
    public abstract Post _convert(PostEditDTO src);

    @SneakyThrows
    public Post convert(PostEditDTO src) {
        var data = _convert(src);
        mapPost(src, data);
        return data;
    }

    Category convert(Optional<Integer> src){
        if (src.isEmpty()) {
            return null;
        }
        return Category.builder().id(src.get()).build();
    }
    // String convert(MultipartFile src) throws IOException{
    //     return fileManager.saveFile(src);
    // }
    public void mapPost(PostEditDTO dto, @MappingTarget final Post post) throws IOException{
        if (!dto.imageFile().isEmpty()) {
            post.setImage(fileManager.saveFile(dto.imageFile()));
        } 
    }
}
