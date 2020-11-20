package com.example.group_7_proj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class HistoryActivity extends AppCompatActivity {
    Button historyBackToMainBtn, historySearchBtn;
    EditText searchBarText;
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

        reff = FirebaseDatabase.getInstance().getReference().child("jobPostTypeTest");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maxPost = (snapshot.getChildrenCount());
                LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutdisplay);


                for(jobID = 1; jobID < maxPost+1; jobID++) {
                    String trigger = snapshot.child("JOBPOST-"+jobID).child("userID").getValue().toString();

                    if (trigger.equals(userNumber)) {
                        String employerName = snapshot.child("JOBPOST-" + jobID).child("employerName").getValue().toString();
                        String jobDetails = snapshot.child("JOBPOST-" + jobID).child("jobDetails").getValue().toString();
                        String jobTitle = snapshot.child("JOBPOST-" + jobID).child("jobTitle").getValue().toString();
                        String jobType = snapshot.child("JOBPOST-" + jobID).child("jobType").getValue().toString();
                        String salary = snapshot.child("JOBPOST-" + jobID).child("salary").getValue().toString();

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


                        // Creating the edit button
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        Button btn = new Button(getApplicationContext());
                        int x = (int) jobID;
                        btn.setId(x);
                        final int id_ = btn.getId();
                        btn.setText("Edit ");
                        btn.setBackgroundColor(Color.rgb(0, 191, 255));
                        LinearLayout layout =(LinearLayout) findViewById(R.id.layoutdisplay);
                        layout.addView(btn, params);

                        btn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), EditPostActivity.class);
                                intent.putExtra("postID","JOBPOST-" + jobID);
                                startActivity(intent);
                            }
                        });

                        // Creating the delete button
                        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        Button btn1 = new Button(getApplicationContext());
                        int y = (int) jobID;
                        btn1.setId(y);
                        final int id1_ = btn1.getId();
                        btn1.setText("Delete ");
                        btn1.setBackgroundColor(Color.rgb(255, 0, 0));
                        LinearLayout layout1 =(LinearLayout) findViewById(R.id.layoutdisplay);
                        layout1.addView(btn1, params1);

                        btn1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Toast.makeText(view.getContext(),
                                        "DELETE = " + id1_, Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
                    }

                }

                historyBackToMainBtn = findViewById(R.id.historyBackToDBBtn);
                historyBackToMainBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        intent.putExtra("User", userNumber);
                        startActivity(intent);
                    }
                });

                historySearchBtn = (Button)findViewById(R.id.historySearchBtn);

                searchClicking(historySearchBtn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void searchClicking(Button btn){
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                searchBarText = findViewById(R.id.historySearchBar);
                String searchText = searchBarText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), HistoryJobTextSearchResultActivity.class);
                intent.putExtra("Search Text", searchText);
                startActivity(intent);
            }
        });
    }
}
