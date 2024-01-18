package com.tintucspringboot.tintuc.controller.user;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.tintucspringboot.tintuc.controller.user.HomeController.NewDTO;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.respository.CategoryRepository;
import com.tintucspringboot.tintuc.respository.PostRepository;
import com.tintucspringboot.tintuc.service.PostService;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/the-loai")
public class CatagoryController {
    private final CategoryRepository categoryRepository;

    private final PostRepository postRepository;

    private final PostService postService;

    @GetMapping("/{slug}")
    public String page(Model model, @PathVariable String slug,
            @RequestParam Optional<Integer> page) {
        List<Post> latestPosts = postService.getLatestPosts();
        model.addAttribute("latestPosts", latestPosts);
        var data = categoryRepository.findTop1BySlug(slug).orElseThrow();
        model.addAttribute("catagory", data);
        var pageable = PageRequest.of(page.orElse(0), 8, Sort.by(Direction.DESC, "pulishDate"));
        var posts = postRepository.findAllByCategory_Slug(slug, pageable);
        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", posts.getNumber());
        model.addAttribute("pageNumber", page);
        model.addAttribute("totalPages", posts.getTotalPages());
        model.addAttribute("contentFragment", "user/catagory.html");

        var getLatesPostByCategory = postService.getLatesPostByCategory(slug).orElse(null);
        model.addAttribute("getLatesPostByCategory", getLatesPostByCategory);

        List<Post> highViewPosts = postService.get5PostsHighView();
        model.addAttribute("highViewPosts", highViewPosts);

        var getRandom6Posts = postService.getRandom6Posts();
        model.addAttribute("getRandom6Posts", getRandom6Posts);

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

}
