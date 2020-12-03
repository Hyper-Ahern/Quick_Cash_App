package com.example.group_7_proj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AcceptedJobsActivity extends AppCompatActivity{
    Button acceptedJobsBackToMainBtn;
    DatabaseReference reff2;
    long maxPost = 0;
    String userNumber = "";
    public static long jobID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceptedjobs);

        Intent callerIntent = getIntent();
        userNumber = callerIntent.getStringExtra("User");

        reff2 = FirebaseDatabase.getInstance().getReference().child("JOBPOST");
        reff2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maxPost = (snapshot.getChildrenCount());
                System.out.println("****************************");
                System.out.println(maxPost);
                System.out.println("****************************");
                LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutdisplay);

                // Iterarate through all the job posts and render only those that are user created
                for (jobID = 1; jobID < maxPost + 1; jobID++) {
                    if (snapshot.hasChild("JOBPOST-" + jobID)) {
                        if(!snapshot.child("JOBPOST-" + jobID).hasChild("employeeID")){
                            reff2.child("JOBPOST-" + jobID).child("employeeID").setValue("0");
                            setContentView(R.layout.acceptedjobs);
                        }
                        if (snapshot.child("JOBPOST-" + jobID).hasChild("employeeID")) {
                            String trigger = snapshot.child("JOBPOST-" + jobID).child("employeeID").getValue().toString();
                            final String paymentStatus = snapshot.child("JOBPOST-" + jobID).child("completionStatus").getValue().toString();


                            // If the current user made the post, render the job, else don't
                            if (trigger.equals(userNumber)) {

                                final long tempJobID = jobID;
                                // Get values of the database fields
                                String employerName = snapshot.child("JOBPOST-" + jobID).child("employerName").getValue().toString();
                                String jobDetails = snapshot.child("JOBPOST-" + jobID).child("jobDetails").getValue().toString();
                                String jobTitle = snapshot.child("JOBPOST-" + jobID).child("jobTitle").getValue().toString();
                                String jobType = snapshot.child("JOBPOST-" + jobID).child("jobType").getValue().toString();
                                String salary = snapshot.child("JOBPOST-" + jobID).child("salary").getValue().toString();

                                // String completionStatus = snapshot.child("JOBPOST-" + jobID).child("completionStatus").getValue().toString();

                                // Create the text views
                                final TextView jobIDTextview = new TextView(getApplicationContext());
                                final TextView jobTitleTextview = new TextView(getApplicationContext());
                                final TextView employerNameTextview = new TextView(getApplicationContext());
                                final TextView jobTypeTextview = new TextView(getApplicationContext());
                                final TextView jobDetailsTextview = new TextView(getApplicationContext());
                                final TextView salaryTextview = new TextView(getApplicationContext());

                                // Set teh values that were received from the dabase to the text views
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


                                // Add those text views to the layout so the user can see them
                                myLayout.addView(jobTitleTextview);
                                myLayout.addView(jobIDTextview);
                                myLayout.addView(jobTypeTextview);
                                myLayout.addView(employerNameTextview);
                                myLayout.addView(jobDetailsTextview);
                                myLayout.addView(salaryTextview);

                                // Creating the edit button for each post
                                LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT);
                                final Button completeBtn = new Button(getApplicationContext());
                                // if the status is completed, then it should be shown as completed and not clickable
                                if (paymentStatus.equals("Completed")) {
                                    completeBtn.setText("Job is completed");
                                    completeBtn.setBackgroundColor(Color.rgb(236, 186, 160));

                                    LinearLayout editLayout = (LinearLayout) findViewById(R.id.layoutdisplay);
                                    editLayout.addView(completeBtn, editParams);

                                } else {


                                    completeBtn.setText("Mark the Jobs as Completed ");
                                    completeBtn.setBackgroundColor(Color.rgb(210, 146, 111));
                                    int editJobID = (int) jobID;
                                    //final int editJobIDMinusOne = editJobID;
                                    completeBtn.setId(editJobID);
                                    //final int editID_ = completeBtn.getId();


                                    LinearLayout editLayout = (LinearLayout) findViewById(R.id.layoutdisplay);
                                    editLayout.addView(completeBtn, editParams);

                                    //When cancel button is clicked, send a bundleof info back to editpostactivity to reload the page
                                    completeBtn.setOnClickListener(new View.OnClickListener() {
                                                                       public void onClick(View view) {


                                                                           reff2.child("JOBPOST-" + tempJobID).child("completionStatus").setValue("Completed");
                                                                           completeBtn.setText("Job is completed");
                                                                           completeBtn.setBackgroundColor(Color.rgb(0, 255, 0));

                                                                           setContentView(R.layout.acceptedjobs);


                                                                       }
                                                                   }
                                    );
                                }
                            }
                        }
                    /*
                    else{
                            jobID++;
                    }

                     */
                    }
                }

                // Creating a back to dashboard button at the bottom of all the posts
                acceptedJobsBackToMainBtn = findViewById(R.id.acceptedJobsBackToDBBtn);
                acceptedJobsBackToMainBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        intent.putExtra("User", userNumber);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w( "loadPost:onCancelled", error.toException());
            }
        });





    }

}

