package com.example.group_7_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditPostActivity extends AppCompatActivity {
    Button editPostBackToMainBtn, submitBtn;
    DatabaseReference reff;
    String jobID = "";
    String userNumber = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editpost);

        //Get ids to return back to the History activity
        Intent callerIntent = getIntent();
        jobID = callerIntent.getStringExtra("postID");
        userNumber = callerIntent.getStringExtra("User");


        reff = FirebaseDatabase.getInstance().getReference().child("jobPostTypeTest");
        reff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutDisplaySearchInner);

                // Get values of the database fields
                String employerName = snapshot.child(jobID).child("employerName").getValue().toString();
                String jobDetails = snapshot.child(jobID).child("jobDetails").getValue().toString();
                String jobTitle = snapshot.child(jobID).child("jobTitle").getValue().toString();
                String jobType = snapshot.child(jobID).child("jobType").getValue().toString();
                String salary = snapshot.child(jobID).child("salary").getValue().toString();

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
                jobIDTextview.setText("Job ID: " + jobID);
                employerNameTextview.setText("Employer Name: " + employerName);
                jobTypeTextview.setText("Job Type: " + jobType);
                jobDetailsTextview.setText("Details: " + jobDetails);
                salaryTextview.setText("Salary: " + salary + "\n");

                // Add those text views to the layout so the user can see them
                myLayout.addView(jobTitleTextview);
                myLayout.addView(jobIDTextview);
                myLayout.addView(jobTypeTextview);
                myLayout.addView(employerNameTextview);
                myLayout.addView(jobDetailsTextview);
                myLayout.addView(salaryTextview);


                // Creating a back to History button at the bottom of all the posts
                editPostBackToMainBtn = findViewById(R.id.editPostCancelBtn);
                editPostBackToMainBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                        intent.putExtra("User", userNumber);
                        startActivity(intent);
                    }
                });

                // Creating a back to dashboard button at the bottom of all the posts
                submitBtn = findViewById(R.id.editPostSubmitBtn);
                submitBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //sasdasd
                    }
                });
      }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
    });
    }
}