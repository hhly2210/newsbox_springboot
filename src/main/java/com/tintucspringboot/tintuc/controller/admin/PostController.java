package com.tintucspringboot.tintuc.controller.admin;

import com.tintucspringboot.tintuc.config.CustomUserDetails;
import com.tintucspringboot.tintuc.dto.PostApproveDto;
import com.tintucspringboot.tintuc.dto.PostEditDTO;
import com.tintucspringboot.tintuc.dto.PostPostDTO;
import com.tintucspringboot.tintuc.mapper.PostApproveMapper;
import com.tintucspringboot.tintuc.mapper.PostEditMapper;
import com.tintucspringboot.tintuc.mapper.PostMapper;
import com.tintucspringboot.tintuc.model.Category;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.model.User;
import com.tintucspringboot.tintuc.respository.CategoryRepository;
import com.tintucspringboot.tintuc.respository.PostRepository;
import com.tintucspringboot.tintuc.respository.UserRepository;
import com.tintucspringboot.tintuc.utils.FileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/posts")
public class PostController {
    @Autowired
    private PostMapper mapper;

    @Autowired
    private PostEditMapper editMapper;

    @Autowired
    private PostApproveMapper approveMapper;

    @Autowired
    private PostRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("contentFragment", "admin/posts/index.html");
        var t = repository.findAll().toArray();
        model.addAttribute("posts", t);
        model.addAttribute("modalDelete", "admin/posts/delete.html");
        return "admin/layouts/app";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("contentFragment", "admin/posts/create.html");
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/layouts/app";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostPostDTO dto) {
        Post newPost = mapper.convert(dto);
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (CustomUserDetails) authentication.getPrincipal();
        newPost.setAuthor(User.builder().id(user.getId()).build());
        repository.save(newPost);
        return "redirect:/admin/posts";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        Post post = repository.getReferenceById(id);
        model.addAttribute("post", post);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("contentFragment", "admin/posts/edit.html");
        return "admin/layouts/app";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute PostEditDTO dto) {
        Post editPost = editMapper.convert(dto);
        Post post = repository.findById(editPost.getId()).orElseThrow();
        post.setTitle(editPost.getTitle());
        post.setDescription(editPost.getDescription());
        post.setCategory(editPost.getCategory());
        post.setImage(editPost.getImage());
        post.setDetails(editPost.getDetails());
        post.setPulishDate(new Date());
        repository.save(post);
        return "redirect:/admin/posts";
    }

    @GetMapping("/approve")
    public String approve(Model model) {
        model.addAttribute("contentFragment", "admin/posts/approve.html");
        List<Post> posts = repository.findByActiveIsNullOrZero();
        model.addAttribute("posts", posts);
        model.addAttribute("modalDelete", "admin/posts/delete.html");
        return "admin/layouts/app";
    }

    @GetMapping("/approve-check")
    public String approveCheck(@RequestParam int id, Model model) {
        Post post = repository.getReferenceById(id);
        model.addAttribute("post", post);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("contentFragment", "admin/posts/approve-check.html");
        return "admin/layouts/app";
    }

    @PostMapping("/approve-check")
    public String approvePost(@ModelAttribute PostApproveDto dto) {
        Post approvePost = approveMapper.convert(dto);
        Post post = repository.getReferenceById(approvePost.getId());
        post.setTitle(approvePost.getTitle());
        post.setDescription(approvePost.getDescription());
        post.setCategory(approvePost.getCategory());
        post.setImage(approvePost.getImage());
        post.setDetails(approvePost.getDetails());
        post.setPulishDate(new Date());
        post.setActive(approvePost.isActive());
        repository.save(post);
        return "redirect:/admin/posts/approve";
    }

    // @GetMapping("/my-post-list")
    // public String myPost(Model model) {
    //     model.addAttribute("contentFragment", "admin/posts/mylist.html");

    //     // Lấy ra tác giả hiện tại từ thông tin đăng nhập
    //     String username = principal.getName();
    //     User author = userRepository.findByUsername(username);

    //     List<Post> posts = repository.findByAuthor(author);
    //     model.addAttribute("posts", posts);
    //     model.addAttribute("modalDelete", "admin/posts/delete.html");
    //     return "admin/layouts/app";
    // }

    @PostMapping("/delete")
    public String deletePost(@RequestParam int id) {
        repository.deleteById(id);
        return "redirect:/admin/posts";
    }
}
