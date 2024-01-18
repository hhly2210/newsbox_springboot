package com.tintucspringboot.tintuc.dto;

import java.util.Optional;

public record CategoryDto(String name, String description, Optional<Integer> parentId) {

}
