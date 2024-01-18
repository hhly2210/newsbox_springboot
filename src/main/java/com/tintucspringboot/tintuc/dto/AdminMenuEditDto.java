package com.tintucspringboot.tintuc.dto;

import java.util.Optional;

public record AdminMenuEditDto(int id, String name, Optional<Integer> parentId,
        Long menuOrder, String link, String icon,
        String menuTarget, String idName, String className,
        Optional<Boolean> active) {
    public static AdminMenuEditDto create(int id, String name, Optional<Integer> parentId,
            Long menuOrder, String link, String icon,
            String menuTarget, String idName, String className,
            Boolean active) {
        return new AdminMenuEditDto(
                id, name, parentId, menuOrder, link, icon,
                menuTarget, idName, className,
                Optional.of(active != null && active ? Boolean.TRUE : Boolean.FALSE));
    }
}
