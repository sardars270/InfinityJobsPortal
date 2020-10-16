package com.example.infinityjobportal.model;

public class Jobs {

    String title;
    String at;
    String location;

    public Jobs() {
    }

    public Jobs(String title, String at, String location) {
        this.title = title;
        this.at = at;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
