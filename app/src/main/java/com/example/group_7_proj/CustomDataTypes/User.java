package com.example.group_7_proj.CustomDataTypes;

import com.example.group_7_proj.JobPost;

import java.util.ArrayList;

public class User {
    private Name name;
    private Email email;
    private Password password;
    private ArrayList<JobPost> appliedJobs;
    private ArrayList<JobPost> createdJobPosts;
    private ArrayList<String> jobCategoryPreferences;

    public User() {
    }

    public User(Name name, Email email, Password password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.appliedJobs = new ArrayList<JobPost>();
        this.createdJobPosts = new ArrayList<JobPost>();
        this.jobCategoryPreferences = new ArrayList<String>();
    }

    public String getName() {
        return name.returnValue();
    }

    public void setName(String name) {
        this.name.setName(name);
    }

    public String getEmail() {
        return email.returnValue();
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public String getPassword() {
        return password.returnValue();
    }

    public void setPassword(String password) {
        this.password.setValue(password);
    }

    public void setAppliedJobs(ArrayList<JobPost> appliedJobs){this.appliedJobs = appliedJobs;}
    public void addAppliedJob(JobPost appliedJob){this.appliedJobs.add(appliedJob);}
    public ArrayList<JobPost> getAppliedJobs(){return this.appliedJobs;};

    public void setCreatedJobPosts(ArrayList<JobPost> createdJobPosts){this.createdJobPosts = createdJobPosts;}
    public void addCreatedJobPosts(JobPost createdJobPost){this.createdJobPosts.add(createdJobPost);}
    public ArrayList<JobPost> getCreatedJobPosts(){return this.createdJobPosts;}
    public void deleteCreatedJobPost(JobPost jobPost){this.createdJobPosts.remove(jobPost);}

    public void setJobCategoryPreferences(ArrayList<String> jobCategoryPreferences){this.jobCategoryPreferences = jobCategoryPreferences;}
    public void addJobCategoryPreference(String jobCategory){this.jobCategoryPreferences.add(jobCategory);}
    public ArrayList<String> getJobCategoryPreferences(){return this.jobCategoryPreferences;}
    public void removeJobCategoryPreference(String jobCategory){this.jobCategoryPreferences.remove(jobCategory);}
}
