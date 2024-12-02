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
        return blogRepository.findById(id).map(detail -> {
            detail.setMainTitle(updatedBlog.getMainTitle());
            detail.setTitle1(updatedBlog.getTitle1());
            detail.setDescription1(updatedBlog.getDescription1());
            detail.setTitle2(updatedBlog.getTitle2());
            detail.setDescription2(updatedBlog.getDescription2());
            detail.setTitle3(updatedBlog.getTitle3());
            detail.setDescription3(updatedBlog.getDescription3());
            detail.setCoverImage(updatedBlog.getCoverImage());
            detail.setImage1(updatedBlog.getImage1());
            detail.setImage2(updatedBlog.getImage2());
            detail.setImage3(updatedBlog.getImage3());
            return blogRepository.save(detail);
        }).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    // Delete Blog by ID
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
