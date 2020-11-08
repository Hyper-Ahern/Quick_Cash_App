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

import java.util.HashMap;


public class JobPostViewActivity extends AppCompatActivity {
    Button backToMainBtn;
    Button cat1Btn, cat2Btn, cat3Btn, cat4Btn, cat5Btn, otherBtn, searchBtn;
    EditText searchBarText;
    String jobType;
    DatabaseReference reff;
    long maxpost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobpostview);

        // insert code here
        reff = FirebaseDatabase.getInstance().getReference().child("jobPostTypeTest");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maxpost = (snapshot.getChildrenCount());
                HashMap<String,JobPost> viewPost= new HashMap<>();
                for(long i = maxpost; i >= 1; i--) {
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
                    final TextView jobpostnum = new TextView(getApplicationContext());

                    int hashMapPosition = i+1;
                    String salary = "Salary: ";
                    jobpostnum.setText("Jobpost"+hashMapPosition);
                    jobpostnum.setTextSize(30);
                    jobpostnum.setId(i+1);
                    myLayout.addView(jobpostnum);

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
                backToMainBtn = findViewById(R.id.clearResults);
                backToMainBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    }
                });

                cat1Btn = (Button)findViewById(R.id.categoryBtn1);
                cat2Btn = (Button)findViewById(R.id.categoryBtn2);
                cat3Btn = (Button)findViewById(R.id.categoryBtn3);
                cat4Btn = (Button)findViewById(R.id.categoryBtn4);
                cat5Btn = (Button)findViewById(R.id.categoryBtn5);
                otherBtn = (Button)findViewById(R.id.categoryBtnOther);
                searchBtn = (Button)findViewById(R.id.searchBtn);

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
                searchBarText = findViewById(R.id.searchBar);
                String searchText = searchBarText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), JobTextSearchResultActivity.class);
                intent.putExtra("Search Text", searchText);
                startActivity(intent);
            }
        });
    }
}