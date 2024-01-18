package com.tintucspringboot.tintuc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Entity
@Data // get set
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Identity {
    private String name;
    private String description;
    @OneToMany(mappedBy = "role")
    private Set<User> users;
    @ManyToMany(mappedBy = "roles")
    private Set<AdminMenu> adminMenus;

}
