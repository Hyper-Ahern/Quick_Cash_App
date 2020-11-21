package com.example.group_7_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class EditPostActivity extends AppCompatActivity {
    Button editPostBackToMainBtn, submitBtn;
    EditText editTitle, editEmployeeName, editDetails, editSalary, editType;
    EditText employerNameText, jobTitleText, jobTypeText, jobSalaryText, jobDetailsText ;
    DatabaseReference reff;
    String jobID = "";
    String userNumber = "";
    public Map<String, Boolean> dataMap = new HashMap<>();

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
                String jobTitle = snapshot.child(jobID).child("jobTitle").getValue().toString();
                String employerName = snapshot.child(jobID).child("employerName").getValue().toString();
                String jobDetails = snapshot.child(jobID).child("jobDetails").getValue().toString();
                final String salary = snapshot.child(jobID).child("salary").getValue().toString();
                String jobType = snapshot.child(jobID).child("jobType").getValue().toString();


                editTitle = findViewById(R.id.editJobTitle);
                editEmployeeName = findViewById(R.id.editEmpName);
                editDetails = findViewById(R.id.editJobDetails);
                editSalary = findViewById(R.id.editSalary);
                editType = findViewById(R.id.editJobType);

                // Set the values that were received from the database to the text views
                editTitle.setText(jobTitle);
//                jobTitleTextview.setTextSize(30);
//                jobIDTextview.setText("Job ID: " + jobID);
                editEmployeeName.setText(employerName);
                editDetails.setText(jobDetails);
                editSalary.setText(salary);
                editType.setText(jobType);

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

                        jobTitleText = findViewById(R.id.editJobTitle);
                        employerNameText = findViewById(R.id.editEmpName);
                        jobDetailsText = findViewById(R.id.editJobDetails);
                        jobSalaryText = findViewById(R.id.editSalary);
                        jobTypeText = findViewById(R.id.editJobType);

                        String title = jobTitleText.getText().toString();
                        String empName = employerNameText.getText().toString();
                        String details = jobDetailsText.getText().toString();
                        String salary = jobSalaryText.getText().toString();
                        String type = jobTypeText.getText().toString();

                        System.out.println(empName);

                        reff.child(jobID).child("jobTitle").setValue(title);
                        reff.child(jobID).child("employerName").setValue(empName);
                        reff.child(jobID).child("jobDetails").setValue(details);
                        reff.child(jobID).child("salary").setValue(salary);
                        reff.child(jobID).child("jobType").setValue(type);
                    }
                });
      }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
    });
    }
}