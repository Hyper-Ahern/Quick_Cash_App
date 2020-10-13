package com.example.group_7_proj;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JobPostActivity extends AppCompatActivity {
    EditText Employername,jobtitle,salaryinput,jobdetails;
    Button submitjobpost;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobpost);
        Employername = findViewById(R.id.employerNameText);
        jobtitle = findViewById(R.id.jobTitleText);
        salaryinput = findViewById(R.id.salaryInputText);
        jobdetails = findViewById(R.id.jobDetailsText);
        submitjobpost= findViewById(R.id.submitBtnJobPost);
    }
}
