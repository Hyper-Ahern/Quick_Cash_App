package com.example.group_7_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.group_7_proj.CustomDataTypes.JobPost;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class JobPostviewActivity extends AppCompatActivity {
    Button backtomainbtn;
    DatabaseReference reff;
    long maxpost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobpostview);

        // insert code here
        reff = FirebaseDatabase.getInstance().getReference().child("jobPost");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maxpost = (snapshot.getChildrenCount());
                HashMap<String, JobPost> viewPost= new HashMap<>();
                for(long i = maxpost; i >= 1; i--) {
                    String employerName = snapshot.child("JOBPOST-"+i).child("employerName").getValue().toString();
                    String jobDetails = snapshot.child("JOBPOST-"+i).child("jobDetails").getValue().toString();
                    String jobTitle = snapshot.child("JOBPOST-"+i).child("jobTitle").getValue().toString();
                    String salary = snapshot.child("JOBPOST-"+i).child("salary").getValue().toString();
                    JobPost job = new JobPost(employerName,jobTitle,salary,jobDetails);
                    viewPost.put(("JOBPOST-"+i),job);
                    //System.out.println("hello");
                }
                LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutdisplay);

                for (int i = 0; i < viewPost.size(); i++) {
                    final TextView jobpostnum = new TextView(getApplicationContext());

                    int hashMapPosition = i+1;
                    String salary = "Salary: ";
                    jobpostnum.setText("Jobpost"+hashMapPosition);
                    jobpostnum.setTextSize(30);
                    jobpostnum.setId(i+1);
                    myLayout.addView(jobpostnum);

                    final TextView employerNameTextview = new TextView(getApplicationContext());
                    final TextView jobDetailsTextview = new TextView(getApplicationContext());
                    final TextView jobTitleTextview = new TextView(getApplicationContext());
                    final TextView salaryTextview = new TextView(getApplicationContext());

                    employerNameTextview.setText("Employer Name: "
                            +viewPost.get("JOBPOST-"+hashMapPosition).getEmployerName());
                    jobDetailsTextview.setText("Job Details: "
                            +viewPost.get("JOBPOST-"+hashMapPosition).getJobDetails());
                    jobTitleTextview.setText("Job Title: "
                            +viewPost.get("JOBPOST-"+hashMapPosition).getJobTitle());
                    salaryTextview.setText("Salary: "
                            + viewPost.get("JOBPOST-"+hashMapPosition).getSalary() + "\n");

                    myLayout.addView(employerNameTextview);
                    myLayout.addView(jobDetailsTextview);
                    myLayout.addView(jobTitleTextview);
                    myLayout.addView(salaryTextview);

                }
                backtomainbtn = findViewById(R.id.backdashbtn);
                backtomainbtn.setOnClickListener(new View.OnClickListener(){
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
