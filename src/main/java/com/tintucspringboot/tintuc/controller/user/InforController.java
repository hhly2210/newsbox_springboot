package com.tintucspringboot.tintuc.controller.user;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.tintucspringboot.tintuc.controller.user.HomeController.NewDTO;
import com.tintucspringboot.tintuc.dto.UserEditInforDto;
import com.tintucspringboot.tintuc.dto.UserEditPassDto;
import com.tintucspringboot.tintuc.mapper.UserEditInforMapper;
import com.tintucspringboot.tintuc.mapper.UserEditPassMapper;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.model.User;
import com.tintucspringboot.tintuc.respository.UserRepository;
import com.tintucspringboot.tintuc.service.PostService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/thong-tin-tai-khoan")
public class InforController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserEditInforMapper editInforMapper;

    @Autowired
    private UserEditPassMapper editPassMapper;

    @GetMapping
    @Transactional(readOnly = true)
    public String page(Model model) {

        List<Post> latestPosts = postService.getLatestPosts();
        model.addAttribute("latestPosts", latestPosts);

        List<Post> news3Posts = postService.get3Posts();
        model.addAttribute("news3Posts", news3Posts);

        List<Post> highViewPosts = postService.get5PostsHighView();
        model.addAttribute("highViewPosts", highViewPosts);

        var getRandom6Posts = postService.getRandom6Posts();
        model.addAttribute("getRandom6Posts", getRandom6Posts);

        model.addAttribute("contentFragment", "user/infor.html");
        model.addAttribute("type", "infor");
        // var contacts = repository.findAll().toArray();
        // model.addAttribute("contacts", contacts);

        // Rss
        try {
            URL feedUrl = new URL("https://vnexpress.net/rss/tin-moi-nhat.rss");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            List<SyndEntry> rss = feed.getEntries();

            int limit = 6;
            var limitedRss = rss.stream().limit(limit).map(NewDTO::create).collect(Collectors.toList());
            model.addAttribute("rss", limitedRss);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Không đọc được Rss");
        }

        return "user/layouts/app";
    }

    @PostMapping("inforedit")
    public String editInfor(@ModelAttribute UserEditInforDto dto, HttpServletRequest request) {
        User user = repository.getReferenceById(dto.id());
        editInforMapper.patch(dto, user);
        repository.save(user);
        request.getSession().setAttribute("userName", user.getName());
        request.getSession().setAttribute("email", user.getEmail());
        return "redirect:/thong-tin-tai-khoan";
    }

    @PostMapping("inforpassword")
    public String editPass(@ModelAttribute UserEditPassDto dto, HttpServletRequest request, Model model) {
        try {
            User user = repository.getReferenceById(dto.id());
            editPassMapper.patch(dto, user);
            repository.save(user);
            return "redirect:/thong-tin-tai-khoan";
        } catch (Exception e) {
            model.addAttribute("contentFragment", "user/infor.html");
            model.addAttribute("errorMessger", "Mật khẩu sai");
            model.addAttribute("type", "password");
            return "user/layouts/app";
        }
    }
}
