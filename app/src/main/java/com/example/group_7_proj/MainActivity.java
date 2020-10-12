package com.example.group_7_proj;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import java.net.PasswordAuthentication;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    TextView emailHint, passwordHint;
    Button loginBtn, signupBtn, backBtn;
    ImageButton googleLoginBtn;
    User userSample;
    DatabaseReference userRef;
    AlertDialog.Builder builder;
    static DatabaseReference emailRef = null;
    static DatabaseReference passwordRef = null;
    String emailStr, passwordStr;
    long maxId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        email = (EditText)findViewById(R.id.emailText);
        password = (EditText)findViewById(R.id.passwordText);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        signupBtn = (Button)findViewById(R.id.signUpBtnLGP);
        googleLoginBtn = (ImageButton)findViewById(R.id.googleLoginBtn);
        backBtn = (Button)findViewById(R.id.backBtnDB);
        emailHint = (TextView)findViewById(R.id.emailHint);
        passwordHint = (TextView)findViewById(R.id.passwordHint);

        builder = new AlertDialog.Builder(this);

        userRef = FirebaseDatabase.getInstance().getReference().child("User");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxId = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        email.setOnKeyListener(new View.OnKeyListener() {
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
        });

        password.setOnKeyListener(new View.OnKeyListener() {
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
        });

        // login
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Email em = new Email(email.getText().toString());
                Password pw = new Password(password.getText().toString());

                // if invalid email or password
                if(em.isInvalid() || pw.isInvalid()){
                    if(pw.isEmpty()){
                        passwordHint.setText("Password missing");
                    }
                    if(em.isEmpty()){
                        emailHint.setText("Email missing");
                    }
                    Toast.makeText(MainActivity.this, "Email or password is in wrong format", Toast.LENGTH_LONG).show();
                }

                /*// if email or password is not registered
                else if(em.notReg() || pw.notReg()){
                    Toast.makeText(MainActivity.this, "Email or password is incorrect", Toast.LENGTH_LONG).show();
                }*/

                // if logs in
                else{
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "You are signed in", Toast.LENGTH_LONG).show();
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

        // back button on dashboard
        /*backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setContentView(R.layout.login_main);
            }
        });*/

    }
}