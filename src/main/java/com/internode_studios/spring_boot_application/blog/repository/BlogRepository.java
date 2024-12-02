package com.internode_studios.spring_boot_application.blog.repository;

import com.internode_studios.spring_boot_application.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
