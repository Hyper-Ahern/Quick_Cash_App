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
import java.util.Objects;

public class JobSearchResultActivity extends AppCompatActivity {
    DatabaseReference reff;
    long maxpost = 0;
    String jobType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobsearchresults);
        Intent intent = getIntent();
        jobType = intent.getStringExtra("Job Type");

        // insert code here
        reff = FirebaseDatabase.getInstance().getReference().child("jobPostTypeTest");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {
                maxpost = (snapshot.getChildrenCount());
                final HashMap<String,JobPostV2> viewPost= new HashMap<>();
                for(long i = maxpost; i >= 1; i--) {
                    String jobTitle = snapshot.child("JOBPOST-"+i).child("jobTitle").getValue().toString();
                    String employerName = snapshot.child("JOBPOST-"+i).child("employerName").getValue().toString();
                    String jobDetails = snapshot.child("JOBPOST-"+i).child("jobDetails").getValue().toString();
                    String salary = snapshot.child("JOBPOST-"+i).child("salary").getValue().toString();
                    String jobType = snapshot.child("JOBPOST-"+i).child("jobType").getValue().toString();
                    JobPostV2 job = new JobPostV2(employerName,jobTitle,salary,jobDetails,jobType);
                    viewPost.put(("JOBPOST-"+i),job);
                    //System.out.println("hello");
                }
                final LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutDisplaySearchInner);

                for (int hashMapPosition = 1; hashMapPosition < viewPost.size()+1; hashMapPosition++) {
                    if(viewPost.get("JOBPOST-"+hashMapPosition).getJobType().equals(jobType)) {
                        final TextView jobpostnum = new TextView(getApplicationContext());
                        final TextView jobTitleTextview = new TextView(getApplicationContext());
                        final TextView employerNameTextview = new TextView(getApplicationContext());
                        final TextView jobTypeTextview = new TextView(getApplicationContext());
                        final TextView jobDetailsTextview = new TextView(getApplicationContext());
                        final TextView salaryTextview = new TextView(getApplicationContext());

                        jobTitleTextview.setText(viewPost.get("JOBPOST-" + hashMapPosition).getJobTitle());
                        jobTitleTextview.setTextSize(30);
                        jobpostnum.setText("Job ID: " + hashMapPosition);
                        employerNameTextview.setText("Employer Name: "
                                + viewPost.get("JOBPOST-" + hashMapPosition).getEmployerName());
                        jobTypeTextview.setText("Job Type: " + viewPost.get("JOBPOST-" + hashMapPosition).getJobType());
                        jobDetailsTextview.setText("Details: " + viewPost.get("JOBPOST-" + hashMapPosition).getJobDetails());
                        salaryTextview.setText("Salary: " + viewPost.get("JOBPOST-" + hashMapPosition).getSalary() + "\n");

                        myLayout.addView(jobTitleTextview);
                        myLayout.addView(jobpostnum);
                        myLayout.addView(jobTypeTextview);
                        myLayout.addView(employerNameTextview);
                        myLayout.addView(jobDetailsTextview);
                        myLayout.addView(salaryTextview);
                    }

                }

                Button backtomainbtn = findViewById(R.id.backdashbtn);
                backtomainbtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), JobPostViewActivity.class);
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