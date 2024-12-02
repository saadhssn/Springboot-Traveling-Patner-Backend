package com.internode_studios.spring_boot_application.blog.controller;

import com.internode_studios.spring_boot_application.blog.model.Blog;
import com.internode_studios.spring_boot_application.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // Create Blog
    @PostMapping("/create")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Blog createdDetail = blogService.createBlog(blog);
        return ResponseEntity.ok(createdDetail);
    }

    // Get All Blogs
    @GetMapping("/getAll")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> details = blogService.getAllBlogs();
        return ResponseEntity.ok(details);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        return blog.map(c -> ResponseEntity.ok((Object) c)) // Cast city object to Object
                .orElseGet(() -> ResponseEntity.status(404).body("Blog not found"));
    }

    // Update Blog
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Long id, @RequestBody Blog updatedBlog) {
        try {
            Blog detail = blogService.updateBlog(id, updatedBlog);
            return ResponseEntity.ok(detail);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Delete Blog
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
        Optional<Blog> detail = blogService.getBlogById(id);
        if (detail.isPresent()) {
            blogService.deleteBlog(id);
            return ResponseEntity.ok("Blog deleted successfully");
        }
        return ResponseEntity.status(404).body("Blog not found");
    }
}
