package com.tintucspringboot.tintuc.dto;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public record PostPostDTO(String details, String title, MultipartFile image, Optional<Integer> category,String description) {
}
