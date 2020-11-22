package com.example.group_7_proj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HistoryActivity extends AppCompatActivity {
    Button historyBackToMainBtn;
    DatabaseReference reff;
    long maxPost = 0;
    String userNumber = "";
    public static long jobID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        Intent callerIntent = getIntent();
        userNumber = callerIntent.getStringExtra("User");

        System.out.println(userNumber);
        reff = FirebaseDatabase.getInstance().getReference().child("jobPostTypeTest");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maxPost = (snapshot.getChildrenCount());
                LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutdisplay);

                // Iterarate through all the job posts and render only those that are user created
                for(jobID = 1; jobID < maxPost+1; jobID++) {
                    String trigger = snapshot.child("JOBPOST-"+jobID).child("userID").getValue().toString();

                    // If the current user made the post, render the job, else don't
                    if (trigger.equals(userNumber)) {
                        // Get values of the database fields
                        String employerName = snapshot.child("JOBPOST-" + jobID).child("employerName").getValue().toString();
                        String jobDetails = snapshot.child("JOBPOST-" + jobID).child("jobDetails").getValue().toString();
                        String jobTitle = snapshot.child("JOBPOST-" + jobID).child("jobTitle").getValue().toString();
                        String jobType = snapshot.child("JOBPOST-" + jobID).child("jobType").getValue().toString();
                        String salary = snapshot.child("JOBPOST-" + jobID).child("salary").getValue().toString();

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
                        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(400,
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        editParams.gravity = Gravity.CENTER;
                        editParams.bottomMargin = 20;
                        Button edtBtn = new Button(getApplicationContext());
                        int editJobID = (int) jobID;
                        final int editJobIDMinusOne = editJobID;
                        edtBtn.setId(editJobID);
                        final int editID_ = edtBtn.getId();
                        edtBtn.setText("Edit ");
                        edtBtn.setBackgroundColor(Color.rgb(0, 191, 255));
                        edtBtn.setTextSize(20);
                        LinearLayout editLayout =(LinearLayout) findViewById(R.id.layoutdisplay);
                        editLayout.addView(edtBtn, editParams);

                        //When cancel button is clicked, send a bundleof info back to editpostactivity to reload the page
                        edtBtn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), EditPostActivity.class);
                                Bundle mBundle = new Bundle();
                                mBundle.putString("postID", "JOBPOST-" + editJobIDMinusOne);
                                mBundle.putString("User", userNumber);
                                intent.putExtras(mBundle);
                                startActivity(intent);
                            }
                        });

                        // Creating the delete button for each post
                        LinearLayout.LayoutParams deleteParams = new LinearLayout.LayoutParams(400,
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        deleteParams.gravity = Gravity.CENTER;
                        deleteParams.bottomMargin = 20;
                        Button dltBtn = new Button(getApplicationContext());
                        int deleteJobID = (int) jobID;
                        final int deleteJobIDMinusOne = deleteJobID;
                        dltBtn.setId(deleteJobID);
                        final int deleteID_ = dltBtn.getId();
                        dltBtn.setText("Delete ");
                        dltBtn.setBackgroundColor(Color.rgb(255, 0, 0));
                        dltBtn.setTextSize(20);
                        LinearLayout deleteLayout =(LinearLayout) findViewById(R.id.layoutdisplay);
                        deleteLayout.addView(dltBtn, deleteParams);

                        //When delete is clicked, remove the child from the database and re render the page
                        dltBtn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                System.out.println("JOBPOST-" + deleteJobIDMinusOne);
                                reff.child("JOBPOST-" + deleteJobIDMinusOne).removeValue();
                                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                                intent.putExtra("User", userNumber);
                                startActivity(intent);
                            }
                        });
                    }

                }

                // Creating a back to dashboard button at the bottom of all the posts
                historyBackToMainBtn = findViewById(R.id.historyBackToDBBtn);
                historyBackToMainBtn.setOnClickListener(new View.OnClickListener(){
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

            }
        });

    }

}
