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

    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etReEnterPassword;

    private Name name;
    private Email email;
    private Password password, reEnterPassword;
    private User user;

    private Boolean nameValid, emailValid, passwordValid, reEnterPasswordValid;

    AlertDialog.Builder builder;

    private TextView nameErrorMessage,emailErrorMessage,passwordErrorMessage,reEnterPasswordErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);

        etName  = findViewById(R.id.name);
        etEmail  = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etReEnterPassword = findViewById(R.id.reenterPassword);
        signUpBtn = findViewById(R.id.signUpBtn);

        nameValid = false;
        emailValid = false;
        passwordValid = false;
        reEnterPasswordValid = false;

        nameErrorMessage = findViewById(R.id.nameErrorMessage);
        emailErrorMessage = findViewById(R.id.emailErrorMessage);
        passwordErrorMessage = findViewById(R.id.passwordErrorMessage);
        reEnterPasswordErrorMessage = findViewById(R.id.passwordErrorMessage2);

        builder = new AlertDialog.Builder(this);
        // SIGN UP
        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                name = new Name((etName).getText().toString());
                email = new Email((etEmail).getText().toString());
                password = new Password((etPassword).getText().toString());
                reEnterPassword = new Password((etReEnterPassword).getText().toString());

                Name nm = new Name(name.returnValue());
                Email em = new Email(email.getValue());
                Password pw = new Password(password.getValue());
                Password reEnterpw = new Password(reEnterPassword.getValue());

                    if(nm.isEmpty()){
                        nameErrorMessage.setText("Name missing");
                        nameErrorMessage.setVisibility(View.VISIBLE);
                    }
                    if(!nm.matchesFormat()){
                        nameErrorMessage.setText("Please only enter alphanumeric characters");
                        nameErrorMessage.setVisibility(View.VISIBLE);
                    }
                    if (!nm.isEmpty() && nm.matchesFormat()){
                        nameValid = true;
                        nameErrorMessage.setText("");
                        nameErrorMessage.setVisibility(View.INVISIBLE);
                    }
                    if(em.isEmpty()){
                        emailErrorMessage.setText("Email missing");
                        emailErrorMessage.setVisibility(View.VISIBLE);
                    }
                    if(!em.matchesFormat()){
                        emailErrorMessage.setText("Email invalid");
                        emailErrorMessage.setVisibility(View.VISIBLE);
                    }
                    if (!em.isEmpty() && em.matchesFormat()){
                        emailValid = true;
                        emailErrorMessage.setText("");
                        emailErrorMessage.setVisibility(View.INVISIBLE);
                    }
                    if(pw.isLessThan8Chars()){
                        passwordErrorMessage.setText("Password should be between 8 and 20 characters");
                        passwordErrorMessage.setVisibility(View.VISIBLE);
                    }
                    if(pw.isWeak()){
                        passwordErrorMessage.setText("Password should include at least one of each: captial letter, lowercase letter, special character(!@#$%^&*(),.:;'') ");
                        passwordErrorMessage.setVisibility(View.VISIBLE);
                    }
                    if (!pw.isLessThan8Chars() && !pw.isWeak()){
                        passwordValid = true;
                        passwordErrorMessage.setText("");
                        passwordErrorMessage.setVisibility(View.INVISIBLE);
                    }
                    if(reEnterpw.isLessThan8Chars()){
                        reEnterPasswordErrorMessage.setText("Password should be between 8 and 20 characters");
                        reEnterPasswordErrorMessage.setVisibility(View.VISIBLE);
                    }
                    if(!(reEnterpw.equals(pw))){
                        reEnterPasswordErrorMessage.setText("Your password's do not match");
                        reEnterPasswordErrorMessage.setVisibility(View.VISIBLE);
                    }
                    if (!reEnterpw.isLessThan8Chars() && reEnterpw.equals(pw)){
                        reEnterPasswordValid = true;
                        reEnterPasswordErrorMessage.setText("");
                        reEnterPasswordErrorMessage.setVisibility(View.INVISIBLE);


                }
                    if((nameValid && passwordValid && emailValid && reEnterPasswordValid)){
                    nameErrorMessage.setVisibility(View.INVISIBLE);
                    passwordErrorMessage.setVisibility(View.INVISIBLE);
                    emailErrorMessage.setVisibility(View.INVISIBLE);
                    reEnterPasswordErrorMessage.setVisibility(View.INVISIBLE);
                }



                // if signup info format is correct check if user already in db
                if (nameValid && passwordValid && emailValid && reEnterPasswordValid){
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
                                    //Intent intent = new Intent(getApplicationContext(), PaymentInfoActivity.class);
                                    //startActivity(intent);
                                    Toast.makeText(SignUpActivity.this, "Welcome "+user.getName()+"!", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });

}}