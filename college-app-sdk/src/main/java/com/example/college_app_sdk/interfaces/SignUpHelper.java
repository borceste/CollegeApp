package com.example.college_app_sdk.interfaces;

import com.example.college_app_sdk.classes.User;

public interface SignUpHelper {
    public User registerNewUser(String username, String email, int yearsOfService, int score);

}
