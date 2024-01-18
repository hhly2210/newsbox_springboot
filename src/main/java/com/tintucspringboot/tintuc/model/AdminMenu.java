package com.tintucspringboot.tintuc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.List;


@Entity
@Data // get set
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AdminMenu extends Identity {
    private String name;
    @ManyToOne
    private AdminMenu parent;
    @OneToMany(mappedBy = "parent")
    private List<AdminMenu> childrens;
    private Long menuOrder;
    private String menuTarget;
    private String icon;
    private String link;
    private boolean active;
    private String idName;
    private String className;
    @ManyToMany()
    @JoinTable(name = "adminmenu_role",
            joinColumns = @JoinColumn(name = "adminmenu_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
