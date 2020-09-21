package com.example.college_app_sdk.classes;

public class Reply {
    private int id;
    private String content;
    private User publisher;
    private int numberOfLikes;
    private int numberOfDislikes;
    private int idReview;

    public Reply(int id, String content, User publisher, int idReview) {
        this.id = id;
        this.content = content;
        this.publisher = publisher;
        this.idReview = idReview;
        this.numberOfDislikes = 0;
        this.numberOfLikes = 0;
    }
}
