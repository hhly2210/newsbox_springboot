package com.tintucspringboot.tintuc.controller.user;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.tintucspringboot.tintuc.controller.user.HomeController.NewDTO;
import com.tintucspringboot.tintuc.dto.CommentDto;
import com.tintucspringboot.tintuc.mapper.CommentMapper;
import com.tintucspringboot.tintuc.model.Comment;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.respository.PostRepository;
import com.tintucspringboot.tintuc.service.CommentService;
import com.tintucspringboot.tintuc.service.PostService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/bai-viet")
public class SingleBlogController {
    @Autowired
    private PostRepository postRepository;

    private final PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/{id}")
    public String page(Model model, @PathVariable int id) {
        var post = postRepository.findByIdAndActive(id, true).orElseThrow();
        postService.incrementViewCount(id);
        model.addAttribute("post", post);
        model.addAttribute("contentFragment", "user/single-blog.html");

        List<Post> highViewPosts = postService.get5PostsHighView();
        model.addAttribute("highViewPosts", highViewPosts);

        var getRandom6Posts = postService.getRandom6Posts();
        model.addAttribute("getRandom6Posts", getRandom6Posts);

        // Lấy danh sách comment
        List<Comment> comments = commentService.getComments(id);
        model.addAttribute("comments", comments);

        // Rss
        try {
            URL feedUrl = new URL("https://vnexpress.net/rss/tin-xem-nhieu.rss");
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

    @PostMapping("/{id}/binh-luan")
    public String postComment(@PathVariable int id, @ModelAttribute CommentDto commentDTO) {
        Comment comment = commentMapper.convert(commentDTO);
        // Lấy bài viết theo id và thiết lập cho bình luận
        Post post = postRepository.findById(id).orElse(null);
        comment.setPost(post);
        commentService.saveComment(comment);

        return "redirect:/bai-viet/" + id;
    }

}
