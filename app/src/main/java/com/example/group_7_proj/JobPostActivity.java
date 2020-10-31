package com.example.group_7_proj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobPostActivity extends AppCompatActivity {
    EditText Employername,jobtitle,salaryinput,jobdetails;
    String jobType;
    Button submitjobpost,backdashBtn;
    TextView validtextview;
    DatabaseReference rootRef;

    long maxId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String firebaseFirstLevel = "jobPost";

        rootRef = FirebaseDatabase.getInstance().getReference().child(firebaseFirstLevel);






        setContentView(R.layout.jobpost);

        Spinner jobTypeList = (Spinner) findViewById(R.id.jobType);

        this.addJobTypeList(jobTypeList);



        Employername = findViewById(R.id.employerNameText);
        jobtitle = findViewById(R.id.jobTitleText);
        jobType = jobTypeList.getSelectedItem().toString();
        salaryinput = findViewById(R.id.salaryInputText);
        jobdetails = findViewById(R.id.jobDetailsText);
        submitjobpost= findViewById(R.id.submitBtnJobPost);
        validtextview =findViewById(R.id.inputStatusTextview);

        validtextview.setVisibility(View.GONE);
        backdashBtn = findViewById(R.id.BackdashBtn);


        backdashBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxId = (snapshot.getChildrenCount());
                    System.out.println(maxId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        submitjobpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Emname = Employername.getText().toString();
                String jobtitle1 = jobtitle.getText().toString();

                String salary = salaryinput.getText().toString();
                String detail = jobdetails.getText().toString();

                final JobPost j1 = new JobPost(Emname,jobtitle1,jobType,salary,detail);
                if(!j1.InvalidEmployerName())
                {
                    validtextview.setText("Invalid Employer info");
                    validtextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobDetails())
                {
                    validtextview.setText("Invalid Detail info");
                    validtextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobTitle())
                {
                    validtextview.setText("Invalid Job Title info");
                    validtextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidSalary())
                {
                    validtextview.setText("Invalid Salary info");
                    validtextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobTypes()){
                    validtextview.setText("Invalid Job Type info");
                    validtextview.setVisibility(View.VISIBLE);
                }

                else
                {
                    System.out.println(maxId);
                    rootRef.child("JOBPOST-"+String.valueOf(maxId + 1)).setValue(j1);
                    Toast.makeText(JobPostActivity.this, "Job Posted successfully",Toast.LENGTH_LONG).show();
                    validtextview.setVisibility(View.GONE);
                }
            }
        });

    }



public void addJobTypeList(Spinner jobTypeList) {

        //jobTypeList = (Spinner) findViewById(R.id.jobType);
        List<String> jobTypes = new ArrayList<String>();
        jobTypes.add("--Please select--");
        jobTypes.add("Dog walking");
        jobTypes.add("Babysitting");
        jobTypes.add("Cleaning");
        jobTypes.add("Computer");
        jobTypes.add("Delivery");
        jobTypes.add("Other");
        @SuppressLint("ResourceType") ArrayAdapter<String> jobTypeListAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, jobTypes);
        jobTypeListAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        jobTypeList.setAdapter(jobTypeListAdapter);
    }


}

