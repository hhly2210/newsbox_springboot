package com.tintucspringboot.tintuc.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.service.DashBoardService;

@Controller
@RequestMapping("/admin")
public class DashBoardController {
    @Autowired
    private DashBoardService dashBoardService;

    @GetMapping
    public String page(Model model) {

        Date today = new Date();
        long postCountInDay = dashBoardService.getPostCountInDay(today);
        long postCountInMonth = dashBoardService.getPostCountInMonth(today);
        long postCountInYear = dashBoardService.getPostCountInYear(today);

        model.addAttribute("postCountInDay", postCountInDay);
        model.addAttribute("postCountInMonth", postCountInMonth);
        model.addAttribute("postCountInYear", postCountInYear);

        model.addAttribute("contentFragment", "admin/index.html");
        // var contacts = repository.findAll().toArray();
        // model.addAttribute("contacts", contacts);
        return "admin/layouts/app";
    }
}
