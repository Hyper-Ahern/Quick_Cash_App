package com.example.group_7_proj;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;



import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.view.KeyEvent;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group_7_proj.CustomDataTypes.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {
    Button backBtn, postAJobBtn, payEmployeeBtn, allJobPostBtn;

    Button btLocation;
    TextView textView;
    Geocoder geocoder;
    List<Address> addresses;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationFinder();
        setContentView(R.layout.dashboard);

//        btLocation = findViewById(R.id.get_location_btn);
//        textView = (TextView) findViewById(R.id.geoTag);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

//        btLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(ActivityCompat.checkSelfPermission(DashboardActivity.this,
//                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
//                    getLocation();
//                }
//                else {
//                    ActivityCompat.requestPermissions(DashboardActivity.this,
//                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
//                }
//            }
//        });



        backBtn = (Button)findViewById(R.id.backBtnDB);
        postAJobBtn = (Button)findViewById(R.id.postJobBtnDB);
        payEmployeeBtn = (Button)findViewById(R.id.payEmpBtnDB);
        allJobPostBtn = (Button)findViewById(R.id.allJobsBtnDB);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        postAJobBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }

                Intent intent = new Intent(getApplicationContext(), JobPostActivity.class);
                startActivity(intent);
            }
        });

        payEmployeeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }

                Intent intent = new Intent(getApplicationContext(), PaymentInfoActivity.class);
                startActivity(intent);
            }
        });

        allJobPostBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }

                Intent intent = new Intent(getApplicationContext(), JobPostviewActivity.class);
                startActivity(intent);
            }
        });
    }



    public void locationFinder(){
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
        }
        else {
            Toast.makeText(this, "Location permission is needed to show you relevant " +
                    "jobs near you.", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

    public void onRequestPermissionsResult( int requestCode, String permissions [], int[] grantResults){
        if (requestCode == 1) {//ACCESS_COARSE_LOCATION
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Location Permission not granted, the app will not " +
                        "function normally without your location.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void getLocation() {fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
        @Override
        public void onComplete(@NonNull Task<Location> task) {
            Location location =task.getResult();
            String fullAddress = " ";
            if (location != null){
                Geocoder geocoder = new Geocoder(DashboardActivity.this, Locale.getDefault());

                try {

                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                            location.getLongitude(),1);

                    fullAddress = addresses.get(0).getLatitude()+", "+ addresses.get(0).getLongitude()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
                    System.out.println(fullAddress);

                    // Push location to database here


                }catch (IOException e){
                    e.printStackTrace();
                }


            }
        }

    });

    }





}