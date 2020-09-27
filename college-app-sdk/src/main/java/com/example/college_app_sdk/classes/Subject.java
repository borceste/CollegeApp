package com.example.college_app_sdk.classes;

public class Subject {
    private String name;
    private String description;
    private float rating;

    public Subject() {
    }

    public Subject(String description, String name){
        this.name = name;
        this.description = description;
        rating = 0.0f;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }
}
