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

public class JobPreferenceMatchActivity extends AppCompatActivity {

    DatabaseReference reff;
    long maxPost = 0;
    String searchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupmatchresult);
        Intent intent = getIntent();
        int listcount = 0;
        String eachpreference = "";
        final ArrayList<String> eachpreferencetansfer = new ArrayList<>();
        searchText = intent.getStringExtra("JobPreference");
        for (int i = 0; i < searchText.length(); i++) {
            if (String.valueOf(searchText.charAt(i)).equals(" ")) {
                eachpreferencetansfer.add(eachpreference);
                //System.out.println(eachpreference);
                eachpreference = "";
                listcount++;
                //System.out.println(listcount);
            } else
                eachpreference = eachpreference + searchText.charAt(i);
        }
        reff = FirebaseDatabase.getInstance().getReference().child("jobTypeTest");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {
                maxPost = (snapshot.getChildrenCount());
                final LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutDisplaySearchInnerinpopup);
                int resultCount = 0;


                for (long jobID = 1; jobID < maxPost + 1; jobID++) {
                    boolean searchTextFoundBypre = false;
                    String jobTitle = snapshot.child("JOBPOST-" + jobID).child("jobTitle").getValue().toString();
                    String employerName = snapshot.child("JOBPOST-" + jobID).child("employerName").getValue().toString();
                    String jobDetails = snapshot.child("JOBPOST-" + jobID).child("jobDetails").getValue().toString();
                    String salary = snapshot.child("JOBPOST-" + jobID).child("salary").getValue().toString();
                    String jobType = snapshot.child("JOBPOST-" + jobID).child("jobType").getValue().toString();

                    // made search non-case sensitive
                    for (int i1 = 0; i1 < eachpreferencetansfer.size(); i1++) {
                        if (eachpreferencetansfer.get(i1).equalsIgnoreCase(jobType)
                                || jobType.equalsIgnoreCase(eachpreferencetansfer.get(i1)))
                            searchTextFoundBypre = true;
                        //System.out.println(jobType);
                        System.out.println(searchTextFoundBypre);
                    }
                    if (searchTextFoundBypre) {
                        resultCount += 1;
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

                }

                if (resultCount == 0) {
                    final TextView noResultShowing = new TextView(getApplicationContext());
                    noResultShowing.setText("No result here. Try type something else");
                    myLayout.addView(noResultShowing);
                    Toast.makeText(JobPreferenceMatchActivity.this, "Sorry We found nothing", Toast.LENGTH_LONG).show();
                }

                Button clearResultsBtn = findViewById(R.id.clearResultsinpopupBtn);
                clearResultsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
