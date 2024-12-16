package com.internode_studios.spring_boot_application.blog.service;

import com.internode_studios.spring_boot_application.blog.model.Blog;
import com.internode_studios.spring_boot_application.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    // Create a new Blog
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    // Get all Blog
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    // Get Blog by ID
    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    // Update Blog by ID
    public Blog updateBlog(Long id, Blog updatedBlog) {
        return blogRepository.findById(id).map(existingBlog -> {
            // Update only non-null fields
            if (updatedBlog.getMainTitle() != null) {
                existingBlog.setMainTitle(updatedBlog.getMainTitle());
            }
            if (updatedBlog.getTitle1() != null) {
                existingBlog.setTitle1(updatedBlog.getTitle1());
            }
            if (updatedBlog.getDescription1() != null) {
                existingBlog.setDescription1(updatedBlog.getDescription1());
            }
            if (updatedBlog.getTitle2() != null) {
                existingBlog.setTitle2(updatedBlog.getTitle2());
            }
            if (updatedBlog.getDescription2() != null) {
                existingBlog.setDescription2(updatedBlog.getDescription2());
            }
            if (updatedBlog.getTitle3() != null) {
                existingBlog.setTitle3(updatedBlog.getTitle3());
            }
            if (updatedBlog.getDescription3() != null) {
                existingBlog.setDescription3(updatedBlog.getDescription3());
            }
            if (updatedBlog.getCoverImage() != null) {
                existingBlog.setCoverImage(updatedBlog.getCoverImage());
            }
            if (updatedBlog.getImage1() != null) {
                existingBlog.setImage1(updatedBlog.getImage1());
            }
            if (updatedBlog.getImage2() != null) {
                existingBlog.setImage2(updatedBlog.getImage2());
            }
            if (updatedBlog.getImage3() != null) {
                existingBlog.setImage3(updatedBlog.getImage3());
            }
            return blogRepository.save(existingBlog);
        }).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    // Delete Blog by ID
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
