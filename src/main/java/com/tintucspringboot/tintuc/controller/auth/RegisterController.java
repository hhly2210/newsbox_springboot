package com.tintucspringboot.tintuc.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tintucspringboot.tintuc.dto.UserDto;
import com.tintucspringboot.tintuc.dto.UserRegisterDto;
import com.tintucspringboot.tintuc.mapper.UserRegisterMapper;
import com.tintucspringboot.tintuc.model.Role;
import com.tintucspringboot.tintuc.model.User;
import com.tintucspringboot.tintuc.respository.RoleRepository;
import com.tintucspringboot.tintuc.respository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserRegisterMapper mapper;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String page(Model model) {
        return "auth/register";
    }

    @PostMapping
    public String createUser(@ModelAttribute UserRegisterDto dto) {
        User newUser = mapper.convert(dto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Role defaultRole = roleRepository.findByName("ROLE_USER");
        newUser.setRole(defaultRole);
        repository.save(newUser);
        return "redirect:/login";
    }
}
