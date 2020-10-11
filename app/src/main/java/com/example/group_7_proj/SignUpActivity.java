package com.example.group_7_proj;

import android.os.Bundle;

import com.example.group_7_proj.CustomDataTypes.Email;
import com.example.group_7_proj.CustomDataTypes.Password;
import com.example.group_7_proj.CustomDataTypes.Username;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;




public class SignUpActivity extends AppCompatActivity {
    private Button submitBtn;
    private Button captchaBtn;

    private EditText etUName;
    private EditText etEmail;
    private EditText etPassword;

    private Username name;
    private Email email;
    private Password password;

    private Boolean nameValid, emailValid, passwordValid;

    private TextView nameErrorMessage,emailErrorMessage,passwordErrorMessage,reenterPasswordErrorMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);

        etUName  = findViewById(R.id.name);
        etEmail  = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);

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