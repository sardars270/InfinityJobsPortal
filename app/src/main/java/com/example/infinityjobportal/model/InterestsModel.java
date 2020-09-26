package com.example.infinityjobportal.model;

public class InterestsModel {
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getFaltu() {
        return faltu;
    }

    public void setFaltu(String faltu) {
        this.faltu = faltu;
    }

    String interests;
    String faltu;


    public InterestsModel() {
        this.id = id;
        this.interests = interests;
        this.faltu = faltu;
    }
}
