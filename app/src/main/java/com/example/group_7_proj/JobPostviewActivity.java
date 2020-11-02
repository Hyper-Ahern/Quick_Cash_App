package com.example.group_7_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class JobPostviewActivity extends AppCompatActivity {
    Button backToMainBtn;
    DatabaseReference reff;
    long maxPost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobpostview);

        // insert code here
        reff = FirebaseDatabase.getInstance().getReference().child("jobPostTypeTest");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maxPost = (snapshot.getChildrenCount());
                HashMap<String,JobPost> viewPost= new HashMap<>();
                for(long i = maxPost; i >= 1; i--) {
                    String employerName = snapshot.child("JOBPOST-"+i).child("employerName").getValue().toString();
                    String jobDetails = snapshot.child("JOBPOST-"+i).child("jobDetails").getValue().toString();
                    String jobTitle = snapshot.child("JOBPOST-"+i).child("jobTitle").getValue().toString();
                    //add job type here
                    String jobTypes = snapshot.child("JOBPOST-"+i).child("jobType").getValue().toString();
                    String salary = snapshot.child("JOBPOST-"+i).child("salary").getValue().toString();
                    JobPost job = new JobPost(employerName,jobTitle,jobTypes,salary,jobDetails);
                    viewPost.put(("JOBPOST-"+i),job);
                }
                LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutdisplay);

                for (int i = 0; i < viewPost.size(); i++) {
                    final TextView jobPostNum = new TextView(getApplicationContext());

                    int hashMapPosition = i+1;
                    String salary = "Salary: ";
                    jobPostNum.setText("Jobpost"+hashMapPosition);
                    jobPostNum.setTextSize(30);
                    jobPostNum.setId(i+1);
                    myLayout.addView(jobPostNum);

                    final TextView employerNameTextview = new TextView(getApplicationContext());
                    final TextView jobDetailsTextview = new TextView(getApplicationContext());
                    final TextView jobTypesTextview = new TextView(getApplicationContext());
                    final TextView jobTitleTextview = new TextView(getApplicationContext());
                    final TextView salaryTextview = new TextView(getApplicationContext());

                    employerNameTextview.setText("Employer Name: "+viewPost.get("JOBPOST-"+hashMapPosition).getEmployerName());
                    jobDetailsTextview.setText("Job Details: "+viewPost.get("JOBPOST-"+hashMapPosition).getJobDetails());
                    jobTypesTextview.setText("Job Types: "+viewPost.get("JOBPOST-"+hashMapPosition).getJobType());
                    jobTitleTextview.setText("Job Title: "+viewPost.get("JOBPOST-"+hashMapPosition).getJobTitle());
                    salaryTextview.setText("Salary: "+ viewPost.get("JOBPOST-"+hashMapPosition).getSalary() + "\n");

                    myLayout.addView(employerNameTextview);
                    myLayout.addView(jobDetailsTextview);
                    myLayout.addView(jobTypesTextview);
                    myLayout.addView(jobTitleTextview);
                    myLayout.addView(salaryTextview);

                }
                backToMainBtn = findViewById(R.id.backdashbtn);
                backToMainBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        }
    }
