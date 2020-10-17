package com.example.group_7_proj;

import android.content.Intent;
import android.os.Bundle;

import com.example.group_7_proj.CustomDataTypes.Email;
import com.example.group_7_proj.CustomDataTypes.Password;
import com.example.group_7_proj.CustomDataTypes.User;
import com.example.group_7_proj.CustomDataTypes.Name;
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

    private Name name;
    private Email email;
    private Password password;
    private User user;

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

        name = new Name((etUName).getText().toString());
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
                    passwordErrorMessage.setText("Password should be between 8 and 20 characters");
                }
                else if(pw.isWeak()){
                    passwordErrorMessage.setText("Password should include at least one of each: captial letter, lowercase letter, special character(!@#$%^&*(),.:;'') ");
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

                // if invalid name, email or password
                if(!( em.isInvalid() || pw.isInvalid())){
                    if(pw.isEmpty()){
                        passwordErrorMessage.setText("Please enter a valid password");
                    }
                    if(em.isEmpty()){
                        emailErrorMessage.setText("Please enter a valid email address");
                    }
                    Toast.makeText(SignUpActivity.this, "Email or password is in wrong format", Toast.LENGTH_LONG).show();
                }

                // if signup info format is correct
                else{
                    FirebaseDatabase database = null;
                    DatabaseReference userRef = null;
                    database = FirebaseDatabase.getInstance();
                    userRef = database.getReference().child("user");
                    final DatabaseReference finalUserRef = userRef; //Recommended fix,
                    userRef.addValueEventListener(new ValueEventListener() {
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            long maxId;
                            String emailFB;
                            boolean found;
                            if(snapshot.exists()){
                                maxId = (snapshot.getChildrenCount());
                                found = false;
                                for(int i=1; i<maxId+1; i++) {
                                    emailFB = snapshot.child("USER-" + i).child("email").getValue(String.class);
                                    if(email.getValue().equals(emailFB)){
                                        found = true;
                                        System.out.println("signup: email found in db="+found);
                                        Toast.makeText(SignUpActivity.this, "An account is already associated with this email", Toast.LENGTH_LONG).show();
                                    }
                                }
                                if(!found){
                                    user = new User(name, email, password);
                                     finalUserRef.child("USER-"+String.valueOf(maxId + 1)).setValue(user);
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
        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), PaymentInfoActivity.class);
                startActivity(intent);
            }
        });
}}