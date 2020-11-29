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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.group_7_proj.CustomDataTypes.GeoLocation;
import com.example.group_7_proj.CustomDataTypes.JobPost;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobPostActivity extends AppCompatActivity {
    EditText employerNameText, jobTitleText, salaryText, jobDetailsText;
    String jobType;
    Button submitJobPostBtn, backToDashBtn, popupyes, popupno;
    TextView validTextview,displayjobpreferencetextview;
    DatabaseReference userRef, jobRef;
    Double longitude, latitude;
    GeoLocation empGeoTag;
    String userNumber;
    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    int potEmpCount = 0;
    long maxID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobpost);
        Intent callerIntent = getIntent();
        userNumber = callerIntent.getStringExtra("User");

        userRef = FirebaseDatabase.getInstance().getReference().child("user");
        jobRef = FirebaseDatabase.getInstance().getReference().child("jobTypeTest");
        Spinner jobTypeList = (Spinner) findViewById(R.id.jobType);
        this.addJobTypeList(jobTypeList);

        employerNameText = findViewById(R.id.employerNameText);
        jobTitleText = findViewById(R.id.jobTitleText);
        salaryText = findViewById(R.id.salaryInputText);
        jobDetailsText = findViewById(R.id.jobDetailsText);
        submitJobPostBtn = findViewById(R.id.submitBtnJobPost);
        validTextview =findViewById(R.id.inputStatusTextview);
        validTextview.setVisibility(View.GONE);
        backToDashBtn = findViewById(R.id.BackdashBtn);

        backToDashBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.putExtra("User", userNumber);
                startActivity(intent);
            }
        });

        jobRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxID = (snapshot.getChildrenCount());
                    System.out.println(maxID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    longitude = Double.parseDouble(snapshot.child("USER-"+String.valueOf(userNumber)).child("geoTag").child("longitude").getValue().toString());
                    latitude = Double.parseDouble(snapshot.child("USER-"+String.valueOf(userNumber)).child("geoTag").child("latitude").getValue().toString());
                    empGeoTag = new GeoLocation(longitude,latitude);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        submitJobPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner jobTypeList = (Spinner) findViewById(R.id.jobType);
                jobType = jobTypeList.getSelectedItem().toString();

                String Emname = employerNameText.getText().toString();
                String jobtitle1 = jobTitleText.getText().toString();
                String salary = salaryText.getText().toString();
                String detail = jobDetailsText.getText().toString();

                final JobPost j1 = new JobPost(Emname,jobtitle1,jobType,salary,detail);
                j1.setGeoLocation(empGeoTag);
                j1.setUserID(Integer.parseInt(userNumber));

                if(!j1.InvalidEmployerName())
                {
                    validTextview.setText("Invalid Employer info");
                    validTextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobDetails())
                {
                    validTextview.setText("Invalid Detail info");
                    validTextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobTitle())
                {
                    validTextview.setText("Invalid Job Title info");
                    validTextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidSalary())
                {
                    validTextview.setText("Invalid Salary info");
                    validTextview.setVisibility(View.VISIBLE);
                }
                else if(!j1.InvalidJobTypes()){
                    validTextview.setText("Invalid Job Type info");
                    validTextview.setVisibility(View.VISIBLE);
                }
                else
                {
                    jobRef.child("JOBPOST-"+String.valueOf(maxID + 1)).setValue(j1);
                    validTextview.setText("Job Posted Successfully");
                    Toast.makeText(JobPostActivity.this, "Job Posted Successfully",Toast.LENGTH_LONG).show();
                    validTextview.setVisibility(View.GONE);
                    int potEmpCount = calculatePotEmpCount(jobType);
                    potentialEmpDialogPopUp(String.valueOf(potEmpCount));

                }
            }

        });

    }

    public void addJobTypeList(Spinner jobTypeList) {
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

    public void potentialEmpDialogPopUp(final String potEmpCount) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View PopupView = getLayoutInflater().inflate(R.layout.popup, null);
        displayjobpreferencetextview = PopupView.findViewById(R.id.matchpreferencetextview);
        popupyes = PopupView.findViewById(R.id.seematchedjobbutton);
        popupno = PopupView.findViewById(R.id.cancelmatchbutton);
        displayjobpreferencetextview.setText("We detected "+potEmpCount+" potential employees. Do you want to see the matches?");
        dialogBuilder.setView(PopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        popupyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PotentialEmployeeActivity.class);
                intent.putExtra("Job Type", jobType);
                startActivity(intent);

            }
        });
        popupno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public int calculatePotEmpCount(final String jobType){
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    maxID = (snapshot.getChildrenCount());
                    ArrayList<String> userPref;

                    for (int userID = 1; userID < maxID + 1; userID++) {
                        userPref = new ArrayList<String>();
                        int maxPref = (int) snapshot.child("USER-" + userID).child("Job Preferences").getChildrenCount();
                        for (int p = 0; p < maxPref; p++) {
                            userPref.add(snapshot.child("USER-" + userID).child("Job Preferences").child(String.valueOf(p)).getValue().toString());
                        }
                        if(userPref.contains(jobType)){
                            potEmpCount+= 1;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return potEmpCount;
    }
}

