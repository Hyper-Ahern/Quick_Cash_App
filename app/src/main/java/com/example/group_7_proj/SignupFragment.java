package com.example.group_7_proj;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.group_7_proj.sampledata.Email;
import com.example.group_7_proj.sampledata.Password;
import com.example.group_7_proj.sampledata.Username;

public class SignupFragment extends Activity {

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

        protected void displayErrorMsg(TextView errorMessage){
            errorMessage.setVisibility(View.visible);
        }


        //@Override
        protected void onValid(){
            if (nameValid && emailValid && passwordValid) {
                submitBtn.setEnabled(true);
            }
        }

        protected void onSubmit(){
            submitBtn.setOnClickListener(new View.OnClickListner(){
                //@Override
                public void onClick(View view){
                    //CHECK If fields valid, if so submit, if not do not submit

                }
            });
        }

    }