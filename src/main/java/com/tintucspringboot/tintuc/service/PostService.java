package com.tintucspringboot.tintuc.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.tintucspringboot.tintuc.model.Category;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.respository.CategoryRepository;
import com.tintucspringboot.tintuc.respository.PostRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Post> getLatestPosts() {
        return postRepository.findTop5ByActiveOrderByPulishDateDesc(true);
    }

    public List<Post> get3Posts() {
        return postRepository.findTop3ByActiveOrderByPulishDateDesc(true);
    }

    public List<Post> get5PostsHighView() {
        return postRepository.findTop6ByActiveOrderByViewDesc(true);
    }

    public List<Post> getRandom6Posts() {
        return postRepository.findRandom6Posts();
    }

    public List<Post> searchPosts(String keyword){
        return postRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Optional<Post> getLatesPostByCategory(String slug) {
        return postRepository.findTopByCategory_SlugAndActiveOrderByPulishDateDesc(slug, true);
    }

    @Transactional
    public void incrementViewCount(int id) {
        postRepository.incrementViewCount(id);
    }

    public Map<Category,List<Post>> getTopPostsByCategories() {
        Collector<Pair<Category,List<Post>>,?, Map<Category, List<Post>>> map = Collectors.toMap((Pair<Category,List<Post>> p)->p.getFirst(), (Pair<Category,List<Post>> p)->p.getSecond());
        return postRepository.findTop4CategoriesByPostCount().map(category-> Pair.of(category, postRepository.findTop6PostsForEachCategory(category).toList())).collect(map);
    }

}
