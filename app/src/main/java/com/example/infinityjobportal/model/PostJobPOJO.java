package com.example.infinityjobportal.model;

import android.location.Address;

import java.util.List;

public class PostJobPOJO {
    private String companyName;
    private String jobCategory;
    private String jobTitle;
    private String streetAddress;
    private String cityAddress;
    private String provinceAddress;
    private String language;
    private Double minSalary;
    private Double maxSalary;
    private String availability;
    private String joiningDate;
    private String applicationDeadline;
    private String jobDescription;
    private String skillsRequired;
    private String qualificationRequired;
    private double latitude;
    private double longitude;

    public PostJobPOJO() {
    }

    public PostJobPOJO(String companyName, String jobCategory, String jobTitle, String streetAddress, String cityAddress, String provinceAddress, String language, Double minSalary, Double maxSalary, String availability, String joiningDate, String applicationDeadline, String jobDescription, String skillsRequired, String qualificationRequired,double latitude,double longitude) {
        this.companyName = companyName;
        this.jobCategory = jobCategory;
        this.jobTitle = jobTitle;
        this.streetAddress = streetAddress;
        this.cityAddress = cityAddress;
        this.provinceAddress = provinceAddress;
        this.language = language;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.availability = availability;
        this.joiningDate = joiningDate;
        this.applicationDeadline = applicationDeadline;
        this.jobDescription = jobDescription;
        this.skillsRequired = skillsRequired;
        this.qualificationRequired = qualificationRequired;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public void setProvinceAddress(String provinceAddress) {
        this.provinceAddress = provinceAddress;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void setApplicationDeadline(String applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setSkillsRequired(String skillsRequired) {
        this.skillsRequired = skillsRequired;
    }

    public void setQualificationRequired(String qualificationRequired) {
        this.qualificationRequired = qualificationRequired;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public String getProvinceAddress() {
        return provinceAddress;
    }

    public String getLanguage() {
        return language;
    }

    public Double getMinSalary() {
        return minSalary;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public String getAvailability() {
        return availability;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public String getApplicationDeadline() {
        return applicationDeadline;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getSkillsRequired() {
        return skillsRequired;
    }

    public String getQualificationRequired() {
        return qualificationRequired;
    }

    public String getId(String id) {
        return id;
    }
}