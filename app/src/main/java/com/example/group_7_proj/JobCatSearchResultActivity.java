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
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobCatSearchResultActivity extends AppCompatActivity {
    DatabaseReference reff;
    long maxPost = 0;
    String jobTypePassed = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobsearchresults);
        Intent intent = getIntent();
        jobTypePassed = intent.getStringExtra("Job Type");

        // insert code here
        reff = FirebaseDatabase.getInstance().getReference().child("JOBPOST");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {
                maxPost = (snapshot.getChildrenCount());
                final LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutDisplaySearchInner);
                int resultCount = 0;

                for(long jobID = 1; jobID < maxPost +1; jobID++) {
                    String jobTitle = snapshot.child("JOBPOST-"+jobID).child("jobTitle").getValue().toString();
                    String employerName = snapshot.child("JOBPOST-"+jobID).child("employerName").getValue().toString();
                    String jobDetails = snapshot.child("JOBPOST-"+jobID).child("jobDetails").getValue().toString();
                    String salary = snapshot.child("JOBPOST-"+jobID).child("salary").getValue().toString();
                    String jobType = snapshot.child("JOBPOST-"+jobID).child("jobType").getValue().toString();

                    if(jobType.equals(jobTypePassed)){
                        resultCount += 1;
                        final TextView jobIDTextview = new TextView(getApplicationContext());
                        final TextView jobTitleTextview = new TextView(getApplicationContext());
                        final TextView employerNameTextview = new TextView(getApplicationContext());
                        final TextView jobTypeTextview = new TextView(getApplicationContext());
                        final TextView jobDetailsTextview = new TextView(getApplicationContext());
                        final TextView salaryTextview = new TextView(getApplicationContext());

                        jobTitleTextview.setText(jobTitle);
                        jobTitleTextview.setTextSize(30);
                        jobTitleTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        jobIDTextview.setText("Job ID: " + jobID);
                        jobIDTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        employerNameTextview.setText("Employer Name: " + employerName);
                        employerNameTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        jobTypeTextview.setText("Job Type: " + jobType);
                        jobTypeTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        jobDetailsTextview.setText("Details: " + jobDetails);
                        jobDetailsTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        salaryTextview.setText("Salary: " + salary + "\n");
                        salaryTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));

                        myLayout.addView(jobTitleTextview);
                        myLayout.addView(jobIDTextview);
                        myLayout.addView(jobTypeTextview);
                        myLayout.addView(employerNameTextview);
                        myLayout.addView(jobDetailsTextview);
                        myLayout.addView(salaryTextview);
                    }
                }

                if(resultCount == 0){
                    final TextView noResultShowing = new TextView(getApplicationContext());
                    noResultShowing.setText("No result here. Try type something else");
                    myLayout.addView(noResultShowing);
                    Toast.makeText(JobCatSearchResultActivity.this, "Sorry We found nothing",Toast.LENGTH_LONG).show();
                }

                Button clearResultsBtn = findViewById(R.id.editPostSubmitBtn);
                clearResultsBtn.setOnClickListener(new View.OnClickListener(){
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