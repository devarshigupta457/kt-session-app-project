package com.project.ktsession.KTSession.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "KTS_COURSES")
public class Course {

    @Id
    @Column(name = "C_ID")
    private String id;

    @Column(name = "C_TITLE")
    private String title;

    @Column(name = "C_DESC")
    private String description;

    @Column(name = "C_PRICE")
    private String price;

    @Column(name = "C_LINK")
    private String link;

    @Column(name = "C_TRENDING")
    private String trending;

    public Course() {
    }

    public Course(String id,
                  String title,
                  String description,
                  String price,
                  String link,
                  String trending) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.link = link;
        this.trending = trending;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTrending() {
        return trending;
    }

    public void setTrending(String trending) {
        this.trending = trending;
    }
}