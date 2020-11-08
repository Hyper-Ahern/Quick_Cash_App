package com.example.group_7_proj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.group_7_proj.CustomDataTypes.JobPost;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobPostActivity extends AppCompatActivity {
    EditText employerNameText, jobTitleText, salaryText, jobDetailsText;
    String jobType;
    Button submitJobPostBtn, backToDashBtn;
    TextView validTextview;
    DatabaseReference rootRef;
    String userNumber = "";

    long maxId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String firebaseFirstLevel = "jobPostTypeTest";
        setContentView(R.layout.jobpost);
        Intent callerIntent = getIntent();
        userNumber = callerIntent.getStringExtra("User");

        rootRef = FirebaseDatabase.getInstance().getReference().child(firebaseFirstLevel);

        Spinner jobTypeList = (Spinner) findViewById(R.id.jobType);
        this.addJobTypeList(jobTypeList);

        employerNameText = findViewById(R.id.employerNameText);
        jobTitleText = findViewById(R.id.jobTitleText);
        salaryText = findViewById(R.id.salaryInputText);
        jobDetailsText = findViewById(R.id.jobDetailsText);
        submitJobPostBtn = findViewById(R.id.submitBtnJobPost);
        validTextview =findViewById(R.id.inputStatusTextview);
        validTextview.setVisibility(View.GONE);
        backToDashBtn = findViewById(R.id.BackdashBtn);

        backToDashBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.putExtra("User", userNumber);
                startActivity(intent);
            }
        });

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxId = (snapshot.getChildrenCount());
                    System.out.println(maxId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        submitJobPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner jobTypeList = (Spinner) findViewById(R.id.jobType);
                jobType = jobTypeList.getSelectedItem().toString();

                String Emname = employerNameText.getText().toString();
                String jobtitle1 = jobTitleText.getText().toString();
                String salary = salaryText.getText().toString();
                String detail = jobDetailsText.getText().toString();

                final JobPost j1 = new JobPost(Emname,jobtitle1,jobType,salary,detail);
                if(!j1.InvalidEmployerName())
                {
                    validTextview.setText("Invalid Employer info");
                    validTextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobDetails())
                {
                    validTextview.setText("Invalid Detail info");
                    validTextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobTitle())
                {
                    validTextview.setText("Invalid Job Title info");
                    validTextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidSalary())
                {
                    validTextview.setText("Invalid Salary info");
                    validTextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobTypes()){
                    validTextview.setText("Invalid Job Type info");
                    validTextview.setVisibility(View.VISIBLE);
                }
                else
                {
                    System.out.println(maxId);
                    rootRef.child("JOBPOST-"+String.valueOf(maxId + 1)).setValue(j1);
                    validTextview.setText("Job Posted Successfully");
                    Toast.makeText(JobPostActivity.this, "Job Posted Successfully",Toast.LENGTH_LONG).show();
                    validTextview.setVisibility(View.GONE);
                }
            }
        });

    }

    public void addJobTypeList(Spinner jobTypeList) {
        List<String> jobTypes = new ArrayList<String>();
        jobTypes.add("--Please select--");
        jobTypes.add("Dog walking");
        jobTypes.add("Babysitting");
        jobTypes.add("Cleaning");
        jobTypes.add("Computer");
        jobTypes.add("Delivery");
        jobTypes.add("Other");

        @SuppressLint("ResourceType") ArrayAdapter<String> jobTypeListAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, jobTypes);
        jobTypeListAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        jobTypeList.setAdapter(jobTypeListAdapter);
    }
}

