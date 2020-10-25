package com.example.infinityjobportal.model;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;


public class Query implements Serializable {
    @Exclude
    private String id;
    private String feedbackQuery;
    private String editSubject;
    private String userid;

    public Query(  String editSubject,String feedbackQuery,String userid) {
        this.editSubject=editSubject;
        this.feedbackQuery = feedbackQuery;
        this.userid=userid;
    }


    public Query() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getEditSubject()
    {
        return editSubject;
    }
    public void setEditSubject(String editSubject)
    {
        this.editSubject=editSubject;
    }

    public String getFeedbackQuery() {
        return feedbackQuery;
    }

    public void setFeedbackQuery(String feedbackQuery) {
        this.feedbackQuery = feedbackQuery;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
