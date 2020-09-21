package com.example.college_app_sdk.classes;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private int id;
    private String name;
    private String description;
    private String city;
    private List<Course> courses;


    public Faculty(int id, String name, String description, String city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
        this.courses = new ArrayList<>();
    }
}
