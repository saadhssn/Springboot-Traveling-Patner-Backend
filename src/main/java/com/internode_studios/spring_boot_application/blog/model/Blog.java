package com.internode_studios.spring_boot_application.blog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mainTitle", nullable = false)
    private String mainTitle;

    @Column(name = "title1", nullable = false)
    private String title1;

    @Column(name = "description1", nullable = false)
    private String description1;

    @Column(name = "title2", nullable = false)
    private String title2;

    @Column(name = "description2", nullable = false)
    private String description2;

    @Column(name = "title3", nullable = false)
    private String title3;

    @Column(name = "description3", nullable = false)
    private String description3;

    @Column(name = "cover_image", nullable = false)
    private String coverImage;

    @Column(name = "image1", nullable = false)
    private String image1;

    @Column(name = "image2", nullable = false)
    private String image2;

    @Column(name = "image3", nullable = false)
    private String image3;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle1() { return title1; }
    public void setTitle1(String title1) { this.title1 = title1; }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getDescription1() { return description1; }
    public void setDescription1(String description1) { this.description1 = description1; }

    public String getTitle2() { return title2; }
    public void setTitle2(String title2) { this.title2 = title2; }

    public String getDescription2() { return description2; }
    public void setDescription2(String description2) { this.description2 = description2; }

    public String getTitle3() { return title3; }
    public void setTitle3(String title3) { this.title3 = title3; }

    public String getDescription3() { return description3; }
    public void setDescription3(String description3) { this.description3 = description3; }

    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public String getImage1() { return image1; }
    public void setImage1(String image1) { this.image1 = image1; }

    public String getImage2() { return image2; }
    public void setImage2(String image2) { this.image2 = image2; }

    public String getImage3() { return image3; }
    public void setImage3(String image3) { this.image3 = image3; }
}
