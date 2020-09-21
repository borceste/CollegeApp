package com.example.college_app_sdk.classes;

/**
 * This is model class for the user.
 */
public class User {
        private String name;
        private String surname;
        private String username;
        private String password;
        private String email;
        private String index;
        private String faculty;
        private int yearsOfService;
        private int score;

        public User(String name, String surname, String username, String email, String index, String faculty){
                this.name = name;
                this.surname = surname;
                this.username = username;
                this.email = email;
                this.index = index;
                this.faculty = faculty;
                yearsOfService = 0;
                score = 0;
        }
}
