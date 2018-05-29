package com.example.ds.project;

public class ReviewItem {
    String username;
    String reviewContent;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public ReviewItem(String username, String reviewContent) {

        this.username = username;
        this.reviewContent = reviewContent;
    }
}

