package com.example.group_7_proj;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JobPostUI extends AppCompatActivity {
    EditText Employername,jobtitle,salaryinput,jobdetails;
    Button submitjobpost;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobpost);
        Employername = findViewById(R.id.EmployerName);
        jobtitle = findViewById(R.id.jobTitle);
        salaryinput = findViewById(R.id.salaryInput);
        jobdetails = findViewById(R.id.jobDetails);
        submitjobpost= findViewById(R.id.submitJobpost);
    }
}
