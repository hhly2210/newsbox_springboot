package com.tintucspringboot.tintuc.dto;

import java.util.Optional;

public record CommentDto(String comment, String userName, Optional<Integer> post) {

}
