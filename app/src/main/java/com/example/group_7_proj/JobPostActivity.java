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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobPostActivity extends AppCompatActivity {
    EditText EmployerName, jobTitle, salaryInput, jobDetails;
    String jobType;
    Button submitJobPost, backDashBtn;
    TextView validTextView;
    DatabaseReference rootRef;

    long maxId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String firebaseFirstLevel = "jobPostTypeTest";

        rootRef = FirebaseDatabase.getInstance().getReference().child(firebaseFirstLevel);

        setContentView(R.layout.jobpost);

        Spinner jobTypeList = (Spinner) findViewById(R.id.jobType);
        this.addJobTypeList(jobTypeList);

        EmployerName = findViewById(R.id.employerNameText);
        jobTitle = findViewById(R.id.jobTitleText);
        salaryInput = findViewById(R.id.salaryInputText);
        jobDetails = findViewById(R.id.jobDetailsText);
        submitJobPost = findViewById(R.id.submitBtnJobPost);
        validTextView =findViewById(R.id.inputStatusTextview);
        validTextView.setVisibility(View.GONE);
        backDashBtn = findViewById(R.id.BackdashBtn);

        backDashBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
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

        submitJobPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Spinner jobTypeList = (Spinner) findViewById(R.id.jobType);
                jobType = jobTypeList.getSelectedItem().toString();

                String Emname = EmployerName.getText().toString();
                String jobtitle1 = jobTitle.getText().toString();
                String salary = salaryInput.getText().toString();
                String detail = jobDetails.getText().toString();

                final JobPost j1 = new JobPost(Emname,jobtitle1,jobType,salary,detail);
                if(!j1.InvalidEmployerName())
                {
                    validTextView.setText("Invalid Employer info");
                    validTextView.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobDetails())
                {
                    validTextView.setText("Invalid Detail info");
                    validTextView.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobTitle())
                {
                    validTextView.setText("Invalid Job Title info");
                    validTextView.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidSalary())
                {
                    validTextView.setText("Invalid Salary info");
                    validTextView.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobTypes()){
                    validTextView.setText("Invalid Job Type info");
                    validTextView.setVisibility(View.VISIBLE);
                }

                else
                {
                    System.out.println(maxId);
                    rootRef.child("JOBPOST-"+String.valueOf(maxId + 1)).setValue(j1);
                    validTextView.setText("Job Posted Successfully");
                    Toast.makeText(JobPostActivity.this, "Job Posted Successfully",Toast.LENGTH_LONG).show();
                    validTextView.setVisibility(View.GONE);
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

