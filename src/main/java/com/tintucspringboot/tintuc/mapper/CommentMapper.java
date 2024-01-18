package com.tintucspringboot.tintuc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import com.tintucspringboot.tintuc.dto.CommentDto;
import com.tintucspringboot.tintuc.model.Comment;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.respository.PostRepository;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {
    @Autowired
    private PostRepository postRepository;

    @Mapping(target = "post", source = "post", qualifiedByName = "mapPost")
    public abstract Comment convert(CommentDto src);

    @Named("mapPost")
    public Post mapPost(Optional<Integer> post) {

        if (post.isEmpty()) {
            return null;
        }
        return postRepository.getReferenceById(post.get());
    }
}
