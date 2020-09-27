package com.example.college_app_sdk.classes;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String name;
    private String description;
    private String city;
    //private List<Course> courses;

    public Faculty(){
        this.name = "";
        this.description = "";
        this.city = "";
    }


    public Faculty(String city, String description, String name) {
        this.name = name;
        this.description = description;
        this.city = city;
        //this.courses = new ArrayList<>();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /*public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }*/
}
