package com.example.group_7_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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
    EditText editTitleField, editEmployeeNameField, editDetailsField, editSalaryField, editTypeField;
    EditText employerNameText, jobTitleText, jobTypeText, jobSalaryText, jobDetailsText ;
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


        reff = FirebaseDatabase.getInstance().getReference().child("JOBPOST");
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


                editTitleField = findViewById(R.id.editJobTitle);
                editEmployeeNameField = findViewById(R.id.editEmpName);
                editDetailsField = findViewById(R.id.editJobDetails);
                editSalaryField = findViewById(R.id.editSalary);
                editTypeField = findViewById(R.id.editJobType);

                // Set the values that were received from the database to the text views
                editTitleField.setText(jobTitle);
                editEmployeeNameField.setText(employerName);
                editDetailsField.setText(jobDetails);
                editSalaryField.setText(salary);
                editTypeField.setText(jobType);

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

                        String newTitle = jobTitleText.getText().toString();
                        String newEmpName = employerNameText.getText().toString();
                        String newDetails = jobDetailsText.getText().toString();
                        String newSalary = jobSalaryText.getText().toString();
                        String newType = jobTypeText.getText().toString();

                        reff.child(jobID).child("jobTitle").setValue(newTitle);
                        reff.child(jobID).child("employerName").setValue(newEmpName);
                        reff.child(jobID).child("jobDetails").setValue(newDetails);
                        reff.child(jobID).child("salary").setValue(newSalary);
                        reff.child(jobID).child("jobType").setValue(newType);
                    }
                });
      }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
    });
    }
}