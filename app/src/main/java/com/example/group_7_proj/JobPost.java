package com.example.group_7_proj;

import android.content.ContentValues;

public class JobPost {
    String Employer;
    String JobTitle;
    String Filtertype;
    String Salay;
    String ComInf;
    long Details;
    public JobPost(String Employer, String JobTitle,String Filtertype, String Salay, String ComInf, long Details)
    {
        this.Employer=Employer;
        this.JobTitle=JobTitle;
        this.Filtertype=Filtertype;
        this.Salay=Salay;
        this.ComInf=ComInf;
        this.Details=Details;
    }
    public long addWord(String word, String definition)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_Employer, Employer);
        initialValues.put(COL_JobTitle, JobTitle);
        initialValues.put(COL_Filtertype, Filtertype);
        initialValues.put(COL_Salay, Salay);
        initialValues.put(COL_ComInf,ComInf);
        return CSCI3130FIREBASE.insert(FTS_VIRTUAL_TABLE, null, initialValues);
    }

}
