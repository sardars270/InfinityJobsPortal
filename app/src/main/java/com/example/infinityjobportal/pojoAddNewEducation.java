

package com.example.infinityjobportal;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class PojoAddNewEducation implements Serializable {

    @Exclude
    private String id;

    private String school;
    private String degree;
    private String fieldOfStudy;
    private String startDate;
    private String endDate;
    private String grade;
    private String extraActs;
    private String description;
    private String userid;

    public PojoAddNewEducation(String school, String degree, String fieldOfStudy, String startDate, String endDate, String grade, String extraActs, String description, String userid) {
        this.school = school;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
        this.extraActs = extraActs;
        this.description = description;
        this.userid = userid;
    }


    public PojoAddNewEducation(String school, String degree, String fieldOfStudy, String startDate, String endDate, String grade, String extraActs, String description) {
        this.school = school;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
        this.extraActs = extraActs;
        this.description = description;
    }

    public PojoAddNewEducation() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getExtraActs() {
        return extraActs;
    }

    public void setExtraActs(String extraActs) {
        this.extraActs = extraActs;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
