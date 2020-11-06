package com.example.group_7_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import java.util.HashMap;

public class JobTextSearchResultActivity extends AppCompatActivity {
    DatabaseReference reff;
    long maxpost = 0;
    String searchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobsearchresults);
        Intent intent = getIntent();
        searchText = intent.getStringExtra("Search Text");

        // insert code here
        reff = FirebaseDatabase.getInstance().getReference().child("jobPostTypeTest");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {
                maxpost = (snapshot.getChildrenCount());
                ArrayList<Long> postposition= new ArrayList<>();
                final HashMap<Long,JobPost> viewPost= new HashMap<>();
                for(long i = maxpost; i >= 1; i--) {
                    String jobTitle = snapshot.child("JOBPOST-"+i).child("jobTitle").getValue().toString();
                    String employerName = snapshot.child("JOBPOST-"+i).child("employerName").getValue().toString();
                    String jobDetails = snapshot.child("JOBPOST-"+i).child("jobDetails").getValue().toString();
                    String salary = snapshot.child("JOBPOST-"+i).child("salary").getValue().toString();
                    String jobType = snapshot.child("JOBPOST-"+i).child("jobType").getValue().toString();
                    JobPost job = new JobPost(employerName,jobTitle,jobType,salary,jobDetails);
                    boolean check = job.getJobDetails().contains(searchText)||job.getJobTitle().equals(searchText)
                            ||job.getSalary().equals(searchText)||job.getEmployerName().equals(searchText)
                            ||job.getJobType().equals(searchText);
                    if(check)
                    {
                        viewPost.put(i, job);
                        postposition.add(i);
                    }
                }
                final LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutDisplaySearchInner);

                if(viewPost.size()>=1) {
                    for (int i=0; i < viewPost.size(); i++) {
                            final TextView jobpostnum = new TextView(getApplicationContext());
                            final TextView jobTitleTextview = new TextView(getApplicationContext());
                            final TextView employerNameTextview = new TextView(getApplicationContext());
                            final TextView jobTypeTextview = new TextView(getApplicationContext());
                            final TextView jobDetailsTextview = new TextView(getApplicationContext());
                            final TextView salaryTextview = new TextView(getApplicationContext());

                            jobTitleTextview.setText(viewPost.get(postposition.get(i)).getJobTitle());
                            jobTitleTextview.setTextSize(30);
                            jobpostnum.setText("Job ID: " + postposition.get(i));
                            employerNameTextview.setText("Employer Name: "
                                    + viewPost.get(postposition.get(i)).getEmployerName());
                            jobTypeTextview.setText("Job Type: " + viewPost.get(postposition.get(i)).getJobType());
                            jobDetailsTextview.setText("Details: " + viewPost.get(postposition.get(i)).getJobDetails());
                            salaryTextview.setText("Salary: " + viewPost.get(postposition.get(i)).getSalary() + "\n");

                            myLayout.addView(jobTitleTextview);
                            myLayout.addView(jobpostnum);
                            myLayout.addView(jobTypeTextview);
                            myLayout.addView(employerNameTextview);
                            myLayout.addView(jobDetailsTextview);
                            myLayout.addView(salaryTextview);
                    }
                }
                else
                {
                    final TextView noresultshowing = new TextView(getApplicationContext());
                    noresultshowing.setText("No result here. Try type something else");
                    myLayout.addView(noresultshowing);
                    Toast.makeText(JobTextSearchResultActivity.this, "Sorry We found nothing",Toast.LENGTH_LONG).show();
                }


                Button backtomainbtn = findViewById(R.id.clearResults);
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