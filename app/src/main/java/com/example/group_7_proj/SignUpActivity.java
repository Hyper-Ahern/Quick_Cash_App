package com.example.group_7_proj;

import android.os.Bundle;

import com.example.group_7_proj.CustomDataTypes.Email;
import com.example.group_7_proj.CustomDataTypes.Password;
import com.example.group_7_proj.CustomDataTypes.Username;

import androidx.appcompat.app.AppCompatActivity;
;

import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;




public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);
    }

    private Button submitBtn;
    private Button captchaBtn;

    private EditText etUName  = findViewById(R.id.name);
    private EditText etEmail  = findViewById(R.id.email);
    private EditText etPassword = findViewById(R.id.password);


    private Username name = new Username((etUName).getText().toString());
    private Email email = new Email((etEmail).getText().toString());
    private Password password = new Password((etPassword).getText().toString());

    private Boolean nameValid = false;
    private Boolean emailValid = false;
    private Boolean passwordValid = false; //password.isWeak();

    private TextView nameErrorMessage = findViewById(R.id.nameErrorMessage);
    private TextView emailErrorMessage = findViewById(R.id.emailErrorMessage);
    private TextView passwordErrorMessage = findViewById(R.id.passwordErrorMessage);
    private TextView reenterPasswordErrorMessage = findViewById(R.id.passwordErrorMessage2);

   // protected void displayErrorMsg(TextView errorMessage){
        //errorMessage.setVisibility(View.visible);
    //}

    //@Override
    protected void onValid(){
        if (nameValid && emailValid && passwordValid) {
            submitBtn.setEnabled(true);
        }
    }

    //protected void onSubmit(){
       // submitBtn.setOnClickListener(new View.OnClickListner(){
            //@Override
            //public void onClick(View view){
                //CHECK If fields valid, if so submit, if not do not submit

            //}
        //});
    //}
}