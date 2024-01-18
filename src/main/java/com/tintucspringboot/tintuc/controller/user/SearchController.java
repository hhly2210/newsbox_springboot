package com.tintucspringboot.tintuc.controller.user;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.tintucspringboot.tintuc.controller.user.HomeController.NewDTO;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.respository.PostRepository;
import com.tintucspringboot.tintuc.service.PostService;

@Controller
public class SearchController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @GetMapping("/search")
    @Transactional(readOnly = true)
    public String search(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Post> searchResults = postService.searchPosts(keyword);
        model.addAttribute("results", searchResults);
        model.addAttribute("keyword", keyword);

        //
        List<Post> latestPosts = postService.getLatestPosts();
        model.addAttribute("latestPosts", latestPosts);

        List<Post> news3Posts = postService.get3Posts();
        model.addAttribute("news3Posts", news3Posts);

        List<Post> highViewPosts = postService.get5PostsHighView();
        model.addAttribute("highViewPosts", highViewPosts);

        var getTopPostsByCategories = postService.getTopPostsByCategories();
        model.addAttribute("getTopPostsByCategories", getTopPostsByCategories);

        var getRandom6Posts = postService.getRandom6Posts();
        model.addAttribute("getRandom6Posts", getRandom6Posts);

        model.addAttribute("contentFragment", "user/pages-search.html");

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
