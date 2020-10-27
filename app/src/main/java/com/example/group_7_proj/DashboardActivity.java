package com.example.group_7_proj;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Button;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
    Button backBtn, postAJobBtn, payEmployeeBtn, allJobPostBtn;
    Location userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationFinder();
        setContentView(R.layout.dashboard);
        backBtn = (Button)findViewById(R.id.backBtnDB);
        postAJobBtn = (Button)findViewById(R.id.postJobBtnDB);
        payEmployeeBtn = (Button)findViewById(R.id.payEmpBtnDB);
        allJobPostBtn = (Button)findViewById(R.id.allJobsBtnDB);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        postAJobBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), JobPostActivity.class);
                startActivity(intent);
            }
        });

        payEmployeeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), PaymentInfoActivity.class);
                startActivity(intent);
            }
        });

        allJobPostBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), JobPostviewActivity.class);
                startActivity(intent);
            }
        });



    }

    //Section for location:
    public void locationFinder(){
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //then yes we run program
        }
        else {
            Toast.makeText(this, "Location permission is needed to show you relevant jobs near you.", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if (requestCode == 1) { //ACCESS_COARSE_LOCATION
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Permission not granted, the app will not function normally without your location.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}