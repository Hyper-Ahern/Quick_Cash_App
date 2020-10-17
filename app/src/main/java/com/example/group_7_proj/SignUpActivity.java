package com.example.group_7_proj;

import android.content.Intent;
import android.os.Bundle;

import com.example.group_7_proj.CustomDataTypes.Email;
import com.example.group_7_proj.CustomDataTypes.Password;
import com.example.group_7_proj.CustomDataTypes.Username;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;



public class SignUpActivity extends AppCompatActivity {
    private Button signUpBtn;
    private Button captchaBtn;

    private EditText etUName;
    private EditText etEmail;
    private EditText etPassword;

    private Username name;
    private Email email;
    private Password password;

    private Boolean nameValid, emailValid, passwordValid;

    AlertDialog.Builder builder;

    private TextView nameErrorMessage,emailErrorMessage,passwordErrorMessage,reenterPasswordErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);

        etUName  = findViewById(R.id.name);
        etEmail  = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        signUpBtn = findViewById(R.id.signUpBtn);

        name = new Username((etUName).getText().toString());
        email = new Email((etEmail).getText().toString());
        password = new Password((etPassword).getText().toString());


        nameValid = false;
        emailValid = false;
        passwordValid = false; //password.isWeak();

        nameErrorMessage = findViewById(R.id.nameErrorMessage);
        emailErrorMessage = findViewById(R.id.emailErrorMessage);
        passwordErrorMessage = findViewById(R.id.passwordErrorMessage);
        reenterPasswordErrorMessage = findViewById(R.id.passwordErrorMessage2);

        builder = new AlertDialog.Builder(this);

        etEmail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Email em = new Email(email.getValue());
                if(em.isEmpty()){
                    emailErrorMessage.setText("Email missing");
                }
                else if(!em.matchesFormat()){
                    emailErrorMessage.setText("Email invalid");
                }
                else{
                    emailErrorMessage.setText("");
                }
                return false;
            }
        });

        etPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Password pw = new Password(password.getValue());

                if(pw.isLessThan8Chars()){
                    passwordErrorMessage.setText("Password too short");
                }
                else if(pw.isWeak()){
                    passwordErrorMessage.setText("Password is weak");
                }
                else{
                    passwordErrorMessage.setText("");
                }
                return false;
            }
        });

        // SIGN UP
        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Email em = new Email(email.getValue());
                Password pw = new Password(password.getValue());

                // if invalid email or password
                if(!(em.isInvalid() || pw.isInvalid())){
                    if(pw.isEmpty()){
                        passwordErrorMessage.setText("Password missing");
                    }
                    if(em.isEmpty()){
                        emailErrorMessage.setText("Email missing");
                    }
                    Toast.makeText(SignUpActivity.this, "Email or password is in wrong format", Toast.LENGTH_LONG).show();
                }

                // if email and password format is correct
                else{
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
                                    if(email.getValue().equals(emailFB) && password.getValue().equals(passwordFB)){
                                        found = true;
                                        System.out.println("found="+found);
                                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(SignUpActivity.this, "You are signed in", Toast.LENGTH_LONG).show();
                                    }
                                }
                                if(!found){
                                    //create new user in db
                                }
                            }
                        }
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });


       // protected void displayErrorMsg(TextView errorMessage){
            //errorMessage.setVisibility(View.visible);
        // }
        // sign up
        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), PaymentInfoActivity.class);
                startActivity(intent);
            }
        });

       // protected void displayErrorMsg(TextView errorMessage){
            //errorMessage.setVisibility(View.visible);
        // }

    //@Override
    /*protected void onValid(){
        if (nameValid && emailValid && passwordValid) {
            submitBtn.setEnabled(true);
        }
    }*/

    

    //protected void onSubmit(){
       // submitBtn.setOnClickListener(new View.OnClickListner(){
            //@Override
            //public void onClick(View view){
                //CHECK If fields valid, if so submit, if not do not submit

            //}
        //});
    //}
}}