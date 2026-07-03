package com.project.ktsession.KTSession.dto.response;

public class CourseResponse {

    private String id;
    private String title;
    private String description;
    private String price;
    private String link;
    private String trending;

    public CourseResponse() {
    }

    public CourseResponse(String id,
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