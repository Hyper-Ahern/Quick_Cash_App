package com.example.group_7_proj;

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

/*Abdullah*/
public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    TextView emailHint, passwordHint;
    Button loginBtn, signupBtn, backBtn;
    ImageButton googleLoginBtn;
    User userSample;
    DatabaseReference userRef;
    AlertDialog.Builder builder;

    Button btLocation;
    TextView textView;
    Geocoder geocoder;
    List<Address> addresses;
    FusedLocationProviderClient fusedLocationProviderClient;

//    Double lat = 44.6488;
//    Double longi = -63.5752;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        btLocation = findViewById(R.id.get_location_btn);
        textView = (TextView) findViewById(R.id.geoTag);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(LoginActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }
                else {
                    ActivityCompat.requestPermissions(LoginActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });

//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        try {
//            addresses = geocoder.getFromLocation(lat, longi, 1);
//            addresses = geocoder.
//
//
//            String address = addresses.get(0).getAddressLine(0);
//            String area = addresses.get(0).getLocality();
//            String city = addresses.get(0).getAdminArea();
//            String country = addresses.get(0).getCountryName();
//            String postalcode = addresses.get(0).getPostalCode();
//
//            String fullAddress = address+", "+area+", "+city+", "+country+", "+postalcode;
//
//            textView.setText(fullAddress);
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }





        email = (EditText)findViewById(R.id.emailText);
        password = (EditText)findViewById(R.id.passwordText);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        signupBtn = (Button)findViewById(R.id.signUpBtnLGP);
        googleLoginBtn = (ImageButton)findViewById(R.id.googleLoginBtn);
        backBtn = (Button)findViewById(R.id.backBtnDB);
        emailHint = (TextView)findViewById(R.id.emailHint);
        passwordHint = (TextView)findViewById(R.id.passwordHint);
        emailHint.setVisibility(View.GONE);
        passwordHint.setVisibility(View.GONE);

        builder = new AlertDialog.Builder(this);

      /*  email.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Email em = new Email(email.getText().toString());
                if(em.isEmpty()){
                    emailHint.setText("Email missing");
                }
                else if(!em.matchesFormat()){
                    emailHint.setText("Email invalid");
                }
                else{
                    emailHint.setText("");
                }
                return false;
            }
        });*/

        /*password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Password pw = new Password(password.getText().toString());

                if(pw.isLessThan8Chars()){
                    passwordHint.setText("Password too short");
                }
                else if(pw.isWeak()){
                    passwordHint.setText("Password is weak");
                }
                else{
                    passwordHint.setText("");
                }
                return false;
            }
        });*/

        // login
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Email em = new Email(email.getText().toString());
                Password pw = new Password(password.getText().toString());

                /*// if invalid email or password
                if((em.isInvalid() || pw.isInvalid())){
                    if(pw.isEmpty()){
                        passwordHint.setText("Password missing");
                    }
                    if(em.isEmpty()){
                        emailHint.setText("Email missing");
                    }
                    Toast.makeText(LoginActivity.this, "Email or password is in wrong format", Toast.LENGTH_LONG).show();
                }*/

                if(pw.isEmpty()){
                    passwordHint.setText("Password missing");
                    passwordHint.setVisibility(View.VISIBLE);
                }
                else if(em.isEmpty()){
                    emailHint.setVisibility(View.VISIBLE);
                    emailHint.setText("Email missing");
                }
                else {
                    FirebaseDatabase database = null;
                    DatabaseReference userRef = null;
                    database = FirebaseDatabase.getInstance();
                    userRef = database.getReference().child("user");
                    userRef.addValueEventListener(new ValueEventListener() {
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            long maxId;
                            String emailFB;
                            String passwordFB;
                            boolean found;
                            if (snapshot.exists()) {
                                maxId = (snapshot.getChildrenCount());
                                found = false;
                                for (int i = 1; i < maxId + 1; i++) {
                                    emailFB = snapshot.child("USER-" + i).child("email").getValue(String.class);
                                    passwordFB = snapshot.child("USER-" + i).child("password").getValue(String.class);
                                    if (email.getText().toString().equals(emailFB) && password.getText().toString().equals(passwordFB)) {
                                        found = true;
                                        break;
                                    }
                                }
                                if (found) {
                                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this, "You are signed in", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Email or password is incorrect", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }

        });

        // sign up
        signupBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        // google login
        googleLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                builder.setMessage("login info will be displayed here").setTitle("Login with Google")
                        .setNeutralButton("LOGIN", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id){
                            }
                        });

                //Creating dialog box
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    private void getLocation() {fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
        @Override
        public void onComplete(@NonNull Task<Location> task) {
            Location location =task.getResult();
            if (location != null){
                Geocoder geocoder = new Geocoder(LoginActivity.this, Locale.getDefault());

                try {

                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                            location.getLongitude(),1);

                    String fullAddress = addresses.get(0).getLatitude()+", "+ addresses.get(0).getLongitude();

                    textView.setText(fullAddress);

                }catch (IOException e){
                    e.printStackTrace();
                }


            }
        }
    });

    }

    /*Abdullah's abandoned but 4hrs of hard work code*/
    public static boolean userExists(final String email, final String password){
        FirebaseDatabase database = null;
        DatabaseReference userRef = null;
        final boolean[] found = null;
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference().child("user");
        userRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long maxId;
                String emailFB;
                String passwordFB;
                if(snapshot.exists()){
                    maxId = (snapshot.getChildrenCount());
                    for(int i=1; i<maxId; i++) {
                        emailFB = snapshot.child("USER-" + i).child("email").getValue(String.class);
                        passwordFB = snapshot.child("USER-" + i).child("password").getValue(String.class);
                        if(email.equals(emailFB) && password.equals(passwordFB)){
                            found[0] = true;
                            System.out.println("i="+i+",em="+emailFB+",pw="+passwordFB+",found="+found[0]);
                            i = (int)maxId;
                        }
                    }
                }
            }
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        System.out.println("found="+found[0]);
        return found[0];
    }

}