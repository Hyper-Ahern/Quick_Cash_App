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
import java.util.ArrayList;

/*Abdullah*/
public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    TextView emailHint, passwordHint;
    Button loginBtn, signupBtn, backBtn;
    ImageButton googleLoginBtn;
    User userSample;
    DatabaseReference userRef;
    AlertDialog.Builder builder;

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
                        if(snapshot.exists()){
                            maxId = (snapshot.getChildrenCount());
                            found = false;
                            for(int i=1; i<maxId+1; i++) {
                                emailFB = snapshot.child("USER-" + i).child("email").getValue(String.class);
                                passwordFB = snapshot.child("USER-" + i).child("password").getValue(String.class);
                                if(email.getText().toString().equals(emailFB) && password.getText().toString().equals(passwordFB)){
                                    found = true;
                                    break;
                                }
                            }
                            if(found){
                                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this, "You are signed in", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Email or password is incorrect", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
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