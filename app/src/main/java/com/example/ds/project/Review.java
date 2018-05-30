package com.example.ds.project;

public class Review {
    String username;
    String reviewContent;
    int menuId;

    public Review(){}

    public Review(String username, String reviewContent ,int menuId) {
        this.username = username;
        this.reviewContent = reviewContent;
        this.menuId = menuId;
    }

}
