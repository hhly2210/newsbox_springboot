package com.tintucspringboot.tintuc.dto;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public record PostApproveDto(int id, String details, String title, String image,
        MultipartFile imageFile, Optional<Integer> category, String description,
        Optional<Boolean> active) {
    public static PostApproveDto create(int id, String details, String title, String image,
            MultipartFile imageFile, Optional<Integer> category, String description,
            Boolean active) {
        return new PostApproveDto(id, details, title, image, imageFile, category, description,
                Optional.of(active != null && active ? Boolean.TRUE : Boolean.FALSE));
    }
}
