package com.example.college_app_sdk.classes;

import java.util.ArrayList;
import java.util.List;

public class Review {
    private String content;
    private int numberOfLikes;
    private int numberOfDislikes;
    private String publisher;
    private String title;
    private String subjectId;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Review(){
        this.content = "";
        this.numberOfDislikes = 0;
        this.numberOfLikes = 0;
        this.publisher = "";
        this.title = "";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getNumberOfDislikes() {
        return numberOfDislikes;
    }

    public void setNumberOfDislikes(int numberOfDislikes) {
        this.numberOfDislikes = numberOfDislikes;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Review(String content, String publisher, int numberOfDislikes, int numberOfLikes, String subjectId) {
        this.subjectId = subjectId;
        this.content = content;
        this.publisher = publisher;
        this.numberOfDislikes = numberOfDislikes ;
        this.numberOfLikes = numberOfLikes;

    }
}
