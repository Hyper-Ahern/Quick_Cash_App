package com.example.group_7_proj;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import com.example.group_7_proj.CustomDataTypes.GeoLocation;
import com.example.group_7_proj.CustomDataTypes.User;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

import java.util.ArrayList;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DashboardActivity extends AppCompatActivity {
    Button backBtn, postAJobBtn, payEmployeeBtn, allJobPostBtn, historyBtn, acceptedJobsBtn, preferenceBtn,popupyes, popupno;
    private TextView displayjobpreferencetextview;
    static boolean ifpopup = true;
    String userNumber = "";
    String displayallPreference = "";
    ArrayList<String> prelist;
    long preferenceCount = 0;
    DatabaseReference userpreference;
    FusedLocationProviderClient fusedLocationProviderClient;
    DatabaseReference rootRef;
    GeoLocation geoLoc;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        rootRef = FirebaseDatabase.getInstance().getReference().child("user");
        Intent callerIntent = getIntent();
        userNumber = callerIntent.getStringExtra("User");
        userpreference = FirebaseDatabase.getInstance().getReference().child("user").child("USER-" + userNumber).child("Job Preferences");
        userpreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                preferenceCount = (snapshot.getChildrenCount());
                prelist = new ArrayList<>((int) preferenceCount);
                for (long i = 0; i < preferenceCount; i++) {
                    String str = String.valueOf(i);
                    prelist.add((String) snapshot.child(str).getValue());
                    displayallPreference = displayallPreference + prelist.get((int) i) + " ";
                }
                if (ifpopup)
                    SeeMatchedJobPostDialog(displayallPreference);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        locationFinder();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        backBtn = (Button) findViewById(R.id.backBtnDB);
        postAJobBtn = (Button) findViewById(R.id.postJobBtnDB);
        payEmployeeBtn = (Button) findViewById(R.id.payEmpBtnDB);
        allJobPostBtn = (Button) findViewById(R.id.allJobsBtnDB);
        preferenceBtn = (Button) findViewById(R.id.preferenceBtn);
        historyBtn = (Button) findViewById(R.id.historyBtn);
        acceptedJobsBtn = (Button) findViewById(R.id.acceptedJobsBtn);


        // permission granted
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            getLocation();
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        postAJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }

                Intent intent = new Intent(getApplicationContext(), JobPostActivity.class);
                intent.putExtra("User", userNumber);
                startActivity(intent);
            }
        });

        payEmployeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }

                Intent intent = new Intent(getApplicationContext(), PaymentInfoActivity.class);
                intent.putExtra("User", userNumber);
                startActivity(intent);
            }
        });

        allJobPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }

                Intent intent = new Intent(getApplicationContext(), JobPostViewActivity.class);
                intent.putExtra("User", userNumber);
                startActivity(intent);
            }
        });
        preferenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PrefActivity.class);
                intent.putExtra("User", userNumber);
                startActivity(intent);
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                intent.putExtra("User", userNumber);
                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }
                startActivity(intent);
            }
        });


        acceptedJobsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AcceptedJobsActivity.class);
                intent.putExtra("User", userNumber);
                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }
                startActivity(intent);
            }
        });

    }

    public void SeeMatchedJobPostDialog(final String preference) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View PopupView = getLayoutInflater().inflate(R.layout.popup, null);
        displayjobpreferencetextview = PopupView.findViewById(R.id.matchpreferencetextview);
        popupyes = PopupView.findViewById(R.id.seematchedjobbutton);
        popupno = PopupView.findViewById(R.id.cancelmatchbutton);
        displayjobpreferencetextview.setText("We detected your Job Preference: " + preference + "Do you want to see the matched result?");
        dialogBuilder.setView(PopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        popupyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ifpopup = false;
                Intent intent = new Intent(getApplicationContext(), JobPreferenceMatchActivity.class);
                intent.putExtra("JobPreference", preference);
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

    public void locationFinder() {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
        } else {
            Toast.makeText(this, "Location permission is needed to show you relevant " +
                    "jobs near you.", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 1) {//ACCESS_COARSE_LOCATION
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Location Permission not granted, the app will not " +
                        "function normally without your location.", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        geoLoc = new GeoLocation(location.getLongitude(),location.getLatitude());

                        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                DatabaseReference user;
                                if(snapshot.exists()){
                                    user = rootRef.child("USER-"+userNumber);
                                    Map<String,Object> userLocationInfo = new HashMap<>();
                                    userLocationInfo.put("geoTag",geoLoc);
                                    user.updateChildren(userLocationInfo);
                                }
                            }
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
            });
        }
    }
}