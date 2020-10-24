package com.example.infinityjobportal.model;

import java.util.Date;

public class PostJobPojo {
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
    private String status;

    public PostJobPojo() {
    }

    public PostJobPojo(String companyName, String jobCategory, String jobTitle, String streetAddress, String cityAddress, String provinceAddress, String language, Double minSalary, Double maxSalary, String availability, String joiningDate, String applicationDeadline, String jobDescription, String skillsRequired, String qualificationRequired, String status) {
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
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public String getProvinceAddress() {
        return provinceAddress;
    }

    public void setProvinceAddress(String provinceAddress) {
        this.provinceAddress = provinceAddress;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(String applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(String skillsRequired) {
        this.skillsRequired = skillsRequired;
    }

    public String getQualificationRequired() {
        return qualificationRequired;
    }

    public void setQualificationRequired(String qualificationRequired) {
        this.qualificationRequired = qualificationRequired;
    }
}
