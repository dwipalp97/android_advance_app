package com.dwipal.practice.androidadvancepracticeapp.model;

public class Friend {
    String name, email;

    public Friend(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Friend() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}