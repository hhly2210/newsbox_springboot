package com.tintucspringboot.tintuc.respository;

import com.tintucspringboot.tintuc.model.Category;
import com.tintucspringboot.tintuc.model.Post;
import com.tintucspringboot.tintuc.model.User;

import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.stream.Stream;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT e FROM Post e WHERE e.active IS NULL OR e.active = false")
    List<Post> findByActiveIsNullOrZero();

    List<Post> findByAuthor(User author);

    long countByPulishDateBetween(Date start, Date end);

    List<Post> findTop5ByActiveOrderByPulishDateDesc(boolean active);

    List<Post> findTop3ByActiveOrderByPulishDateDesc(boolean active);

    Page<Post> findAllByCategory_Slug(String slug, Pageable pageable);

    List<Post> findTop6ByActiveOrderByViewDesc(boolean active);

    Optional<Post> findByIdAndActive(int id, boolean active);

    Optional<Post> findTopByCategory_SlugAndActiveOrderByPulishDateDesc(String slug, boolean active);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.view = p.view + 1 WHERE p.id = :postId")
    void incrementViewCount(@Param("postId") int id);

    // Lấy 4 thể loại có nhiều bài viết có trạng thái là true nhất
    @Query("SELECT c FROM (SELECT p.category as c, COUNT(p.category) AS postCount FROM Post p WHERE p.active = true GROUP BY p.category ORDER BY postCount DESC limit 4)")
    Stream<Category> findTop4CategoriesByPostCount();

    // Lấy 6 bài viết của mỗi thể loại có nhiều bài viết có trạng thái là true nhất
    @Query("SELECT p FROM Post p WHERE p.category = :category AND p.active = true ORDER BY p.category, p.view DESC limit 6")
    Stream<Post> findTop6PostsForEachCategory(@Param("category") Category category);

    // Lấy 6 bài viết ngẫu nhiên
    @Query(value = "SELECT * FROM post WHERE active = true ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Post> findRandom6Posts();

    // Tìm kiếm bài viết
    List<Post> findByTitleContainingIgnoreCase(String keyword);
}
