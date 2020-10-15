package com.example.group_7_proj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JobPostActivity extends AppCompatActivity {
    EditText Employername,jobtitle,salaryinput,jobdetails;
    Button submitjobpost;
    TextView validtextview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Post");

        setContentView(R.layout.jobpost);
        Employername = findViewById(R.id.employerNameText);
        jobtitle = findViewById(R.id.jobTitleText);
        salaryinput = findViewById(R.id.salaryInputText);
        jobdetails = findViewById(R.id.jobDetailsText);
        submitjobpost= findViewById(R.id.submitBtnJobPost);
        validtextview =findViewById(R.id.inputStatusTextview);
        validtextview.setVisibility(View.GONE);
        submitjobpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Emname = Employername.getText().toString();
                String jobtitle1 = jobtitle.getText().toString();
                String salary = salaryinput.getText().toString();
                String detail = jobdetails.getText().toString();
                JobPost j1 = new JobPost(Emname,jobtitle1,salary,detail);
                if(!j1.InvalidEmployerName())
                {
                    validtextview.setText("Invalid Employer info");
                    validtextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobDetails())
                {
                    validtextview.setText("Invalid Detail info");
                    validtextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobTitle())
                {
                    validtextview.setText("Invalid Job Title info");
                    validtextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidSalary())
                {
                    validtextview.setText("Invalid Salary info");
                    validtextview.setVisibility(View.VISIBLE);
                }
                else
                {
                    rootRef.push().setValue(j1);
                    Toast.makeText(JobPostActivity.this, "Job Posted successfully",Toast.LENGTH_LONG).show();
                    validtextview.setVisibility(View.GONE);
                }
            }
        });

    }
}
