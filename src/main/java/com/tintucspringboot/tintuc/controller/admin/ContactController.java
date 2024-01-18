package com.tintucspringboot.tintuc.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tintucspringboot.tintuc.respository.ContactRepository;

@Controller
@RequestMapping("/admin/contacts")
public class ContactController {
    @Autowired
    private ContactRepository repository;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("contentFragment", "admin/contacts/index.html");
        var contacts = repository.findAll().toArray();
        model.addAttribute("contacts", contacts);
        model.addAttribute("modalDelete", "admin/contacts/delete.html");
        return "admin/layouts/app";
    }

    @PostMapping("/delete")
    public String deleteContact(@RequestParam int id) {
        repository.deleteById(id);
        return "redirect:/admin/contacts";
    }
}
