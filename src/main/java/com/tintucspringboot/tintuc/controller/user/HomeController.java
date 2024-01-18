package com.tintucspringboot.tintuc.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.service.PostService;

import lombok.Builder;
// import lombok.var;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PostService postService;

    /**
     * NewDTO
     */
    @Builder
    public record NewDTO(String title, String link, String image, String date) {
        static NewDTO create(SyndEntry entry) {
            var t = Pattern.compile("img\\ssrc=\"([\\d\\w\\.\\=\\&\\?\\-\\/\\:]+)\"\\s")
                    .matcher(entry.getDescription().getValue());
            if (t.find())
                return NewDTO.builder().title(entry.getTitle()).link(entry.getLink())
                        .image(t.group(1))
                        .date(entry.getPublishedDate().toString()).build();
            else
                return NewDTO.builder().title(entry.getTitle()).link(entry.getLink())
                        .date(entry.getPublishedDate().toString()).build();
        }
    }

    @GetMapping
    @Transactional(readOnly = true)
    public String page(Model model) {

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

        model.addAttribute("contentFragment", "user/index.html");
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

}
