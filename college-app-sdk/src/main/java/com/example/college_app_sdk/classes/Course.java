package com.example.college_app_sdk.classes;

public class  Course {
    private int id;
    private String name;
    private String description;
    private float rating;

    public Course(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
        rating = 0.0f;
    }
}
