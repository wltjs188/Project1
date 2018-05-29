package com.example.ds.project;

public class Review {
    String username;
    String reviewContent;
    int menuid;

    public Review(){}

    public Review(String username, String reviewContent, int menuid) {
        this.username = username;
        this.reviewContent = reviewContent;
        this.menuid = menuid;
    }

}
