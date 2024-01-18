package com.tintucspringboot.tintuc.dto;
import java.util.Optional;
public record CategoryEditDto(int id, String name, String description, Optional<Integer> parentId) {

}
