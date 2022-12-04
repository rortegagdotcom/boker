package com.rortegag.boker.main.models.user;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String userName;
    public String email;

    public User() {}

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
