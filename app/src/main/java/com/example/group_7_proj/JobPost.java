package com.example.group_7_proj;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobPost{

    private String EmployerName;
    private String JobTitle;
    private String Salary;
    private String JobDetails;

    public JobPost() {}
    public JobPost(String EN,String JT,String salary,String JobDetails)
    {
        this.EmployerName = EN;
        this.JobTitle=JT;
        this.Salary=salary;
        this.JobDetails=JobDetails;
    }

    public String getEmployerName() {
        return EmployerName;
    }

    public String getJobDetails() {
        return JobDetails;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public String getSalary() {
        return Salary;
    }

    public void setEmployerName(String employerName) {
        EmployerName = employerName;
    }

    public void setJobDetails(String jobDetails) {
        JobDetails = jobDetails;
    }

    public void setJobTitle(String JT) {
        this.JobTitle = JT;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public boolean InvalidSalary()
    {
        if (Salary == null) {
            return false;
        }
        try {
            double dbl = Double.parseDouble(Salary);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public boolean InvalidEmployerName() {
        if (EmployerName == null||EmployerName.equals("")) {
            return false;
        }
        return true;
    }

    public boolean InvalidJobTitle() {
        String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        for(int i=0;i<29;i++) {
            if (JobTitle.contains(Character.toString(specialCharactersString.charAt(i)))) {
                return false;
            }
        }
                return true;

    }

    public boolean InvalidJobDetails() {
        if (JobDetails == null || JobDetails.equals("")) {
            return false;
        }
        return true;
    }
}
