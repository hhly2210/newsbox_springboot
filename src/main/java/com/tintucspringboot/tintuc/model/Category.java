package com.tintucspringboot.tintuc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@Data // get set
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Identity {
    private String name;
    private String slug;
    private String description;
    @ManyToOne
    private Category parent;
    @OneToMany(mappedBy = "parent")
    private List<Category> childrens;
    @OneToMany(mappedBy = "category")
    private List<Post> posts;
}
