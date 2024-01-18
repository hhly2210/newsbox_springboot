package com.tintucspringboot.tintuc.dto;

import java.util.Optional;

public record UserEditDto(int id, String username, String email, Optional<Integer> role) {

}
