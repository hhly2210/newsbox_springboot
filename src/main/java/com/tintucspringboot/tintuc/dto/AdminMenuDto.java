package com.tintucspringboot.tintuc.dto;

import java.util.List;
import java.util.Optional;

public record AdminMenuDto(String name, Optional<Integer> parentId,
        Long menuOrder, String link, String icon,
        String menuTarget, String idName, String className,
        boolean active, List<Integer> roles) {

}
