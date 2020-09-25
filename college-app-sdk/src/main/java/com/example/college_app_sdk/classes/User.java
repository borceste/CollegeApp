package com.example.college_app_sdk.classes;

import java.io.Serializable;

/**
 * This is model class for the user.
 */
public class User implements Serializable {
    private String username;
    private String email;
    private int yearsOfService;
    private int score;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }

    public void setYearsOfService(int yearsOfService) {
        this.yearsOfService = yearsOfService;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User(String username, String email, int yearsOfService, int score) {
        this.username = username;
        this.email = email;
        this.yearsOfService = yearsOfService;
        this.score = score;
    }

}
