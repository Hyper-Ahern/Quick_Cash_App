package com.example.group_7_proj.CustomDataTypes;
import java.util.ArrayList;

public class User {
    private Name name;
    private Email email;
    private Password password;
    private GeoLocation geoLoc;
    private ArrayList<JobPost> myAppliedJobs;
    private ArrayList<JobPost> myJobPosts;

    public User() {
    }

    public User(Name name, Email email, Password password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.geoLoc = null;
        this.myAppliedJobs = new ArrayList<>();
        this.myJobPosts= new ArrayList<>();
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

    public GeoLocation getLocation(){
        return geoLoc;
    }
    public void setLocation(GeoLocation locat){
        geoLoc = locat;
    }

}
