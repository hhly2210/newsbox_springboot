package com.tintucspringboot.tintuc.dto;

import java.util.Optional;

public record UserDto(String username, String email, String password, Optional<Integer> role) {

}
