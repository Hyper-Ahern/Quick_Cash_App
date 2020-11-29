package com.example.group_7_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class JobPostViewActivity extends AppCompatActivity {
    Button backToMainBtn, cat1Btn, cat2Btn, cat3Btn, cat4Btn, cat5Btn, otherBtn, searchBtn;
    EditText searchBarText;
    DatabaseReference reff;
    long maxPost = 0;
    String userNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobpostview);
        Intent callerIntent = getIntent();
        userNumber = callerIntent.getStringExtra("User");

        reff = FirebaseDatabase.getInstance().getReference().child("jobPostTypeTest");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maxPost = (snapshot.getChildrenCount());
                LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutdisplay);

                for(long jobID = 1; jobID < maxPost+1; jobID++) {
                    String employerName = snapshot.child("JOBPOST-"+jobID).child("employerName").getValue().toString();
                    String jobDetails = snapshot.child("JOBPOST-"+jobID).child("jobDetails").getValue().toString();
                    String jobTitle = snapshot.child("JOBPOST-"+jobID).child("jobTitle").getValue().toString();
                    String jobType = snapshot.child("JOBPOST-"+jobID).child("jobType").getValue().toString();
                    String salary = snapshot.child("JOBPOST-"+jobID).child("salary").getValue().toString();

                    final TextView jobIDTextview = new TextView(getApplicationContext());
                    final TextView jobTitleTextview = new TextView(getApplicationContext());
                    final TextView employerNameTextview = new TextView(getApplicationContext());
                    final TextView jobTypeTextview = new TextView(getApplicationContext());
                    final TextView jobDetailsTextview = new TextView(getApplicationContext());
                    final TextView salaryTextview = new TextView(getApplicationContext());

                    jobTitleTextview.setText(jobTitle);
                    jobTitleTextview.setTextSize(30);
                    jobIDTextview.setText("Job ID: " + jobID);
                    employerNameTextview.setText("Employer Name: " + employerName);
                    jobTypeTextview.setText("Job Type: " + jobType);
                    jobDetailsTextview.setText("Details: " + jobDetails);
                    salaryTextview.setText("Salary: " + salary + "\n");

                    myLayout.addView(jobTitleTextview);
                    myLayout.addView(jobIDTextview);
                    myLayout.addView(jobTypeTextview);
                    myLayout.addView(employerNameTextview);
                    myLayout.addView(jobDetailsTextview);
                    myLayout.addView(salaryTextview);

                }

                backToMainBtn = findViewById(R.id.historyBackToDBBtn);
                backToMainBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        intent.putExtra("User", userNumber);
                        startActivity(intent);
                    }
                });

                cat1Btn = (Button)findViewById(R.id.categoryBtn1);
                cat2Btn = (Button)findViewById(R.id.categoryBtn2);
                cat3Btn = (Button)findViewById(R.id.categoryBtn3);
                cat4Btn = (Button)findViewById(R.id.categoryBtn4);
                cat5Btn = (Button)findViewById(R.id.categoryBtn5);
                otherBtn = (Button)findViewById(R.id.categoryBtnOther);
                searchBtn = (Button)findViewById(R.id.historySearchBtn);

                buttonClicking(cat1Btn, "Delivery");
                buttonClicking(cat2Btn, "Computer");
                buttonClicking(cat3Btn, "Babysitting");
                buttonClicking(cat4Btn, "Cleaning");
                buttonClicking(cat5Btn, "Dog walking");
                buttonClicking(otherBtn, "Other");
                searchClicking(searchBtn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void buttonClicking(Button btn, final String jobType){
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), JobCatSearchResultActivity.class);
                intent.putExtra("Job Type", jobType);
                startActivity(intent);
            }
        });
    }

    public void searchClicking(Button btn){
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                searchBarText = findViewById(R.id.historySearchBar);
                String searchText = searchBarText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), JobTextSearchResultActivity.class);
                intent.putExtra("Search Text", searchText);
                startActivity(intent);
            }
        });
    }
}
