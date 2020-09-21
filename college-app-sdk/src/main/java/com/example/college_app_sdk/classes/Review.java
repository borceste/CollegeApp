package com.example.college_app_sdk.classes;

import java.util.ArrayList;
import java.util.List;

public class Review {
    private int id;
    private String content;
    private int numberOfLikes;
    private int numberOfDislikes;
    private User publisher;
    private List<Reply> replies;

    public Review(int id, String content, User publisher) {
        this.id = id;
        this.content = content;
        this.publisher = publisher;
        this.replies = new ArrayList<>();
        numberOfDislikes = 0;
        numberOfLikes = 0;
    }
}
