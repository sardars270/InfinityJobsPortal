package com.example.infinityjobportal.model;

import java.util.Date;

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

    public PostJobPOJO() {
    }

    public PostJobPOJO(String companyName, String jobCategory, String jobTitle, String streetAddress, String cityAddress, String provinceAddress, String language, Double minSalary, Double maxSalary, String availability, String joiningDate, String applicationDeadline, String jobDescription, String skillsRequired, String qualificationRequired) {
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
}
