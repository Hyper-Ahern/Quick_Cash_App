package com.example.group_7_proj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.location.FusedLocationProviderClient;

import com.example.group_7_proj.CustomDataTypes.GeoLocation;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class JobPostActivity extends AppCompatActivity {
    EditText Employername,jobtitle,salaryinput,jobdetails;
    Button submitjobpost,backdashBtn;
    TextView validtextview;
    DatabaseReference rootRef;
    FusedLocationProviderClient fusedLocationProviderClient;
    long maxId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String firebaseFirstLevel = "jobPost";
        rootRef = FirebaseDatabase.getInstance().getReference().child(firebaseFirstLevel);

        setContentView(R.layout.jobpost);
        Employername = findViewById(R.id.employerNameText);
        jobtitle = findViewById(R.id.jobTitleText);
        salaryinput = findViewById(R.id.salaryInputText);
        jobdetails = findViewById(R.id.jobDetailsText);
        submitjobpost= findViewById(R.id.submitBtnJobPost);
        validtextview =findViewById(R.id.inputStatusTextview);
        validtextview.setVisibility(View.GONE);
        backdashBtn = findViewById(R.id.BackdashBtn);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

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
                final JobPost j1 = new JobPost(Emname,jobtitle1,salary,detail);
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
    /*
    @SuppressLint("MissingPermission")
    private void getLocation() {fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
        @Override
        public void onComplete(@NonNull Task<Location> task) {
            Location location =task.getResult();
            String fullAddress = " ";
            GeoLocation jobLoc = null;
            if (location != null){
                jobLoc = new GeoLocation(location.getLongitude(), location.getLatitude());
                Geocoder geocoder = new Geocoder(JobPostActivity.this, Locale.getDefault());
            }
        }
    });
    }
    */
}
