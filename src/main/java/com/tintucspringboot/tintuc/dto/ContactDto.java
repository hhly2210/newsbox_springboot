package com.tintucspringboot.tintuc.dto;
import com.tintucspringboot.tintuc.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;



@Data // get set
@Builder
public class ContactDto {
    private String fullName;
    private String email;
    private String message;
}
