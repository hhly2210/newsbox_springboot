package com.tintucspringboot.tintuc.dto;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public record PostEditDTO(int id,String details, String title, String image, MultipartFile imageFile, Optional<Integer> category,String description) {
}
