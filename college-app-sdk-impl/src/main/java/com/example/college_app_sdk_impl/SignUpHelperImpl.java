package com.example.college_app_sdk_impl;

import com.example.college_app_sdk.interfaces.SignUpHelper;
import com.example.college_app_sdk.classes.User;

public class SignUpHelperImpl implements SignUpHelper {

    @Override
    public User registerNewUser(String username, String email, int yearsOfService, int score) {
        return new User(username, email, yearsOfService, score);
    }

}
