package com.example.group_7_proj;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PrefActivity extends AppCompatActivity {
    String userNumber = "";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference);
        Intent callerIntent = getIntent();
        userNumber = callerIntent.getStringExtra("User");

        final TextView title;
        final Button backBtn, submitBtn;

        // checkbox section {
        final CheckBox babysitting = (CheckBox) findViewById(R.id.pref_babysitting);
        if (babysitting.isChecked()) { //database shows as true
            babysitting.setChecked(true);
        }
        else{
            babysitting.setChecked(false);
        }
        final CheckBox cleaning = (CheckBox) findViewById(R.id.pref_cleaning);
        if (babysitting.isChecked()) { //database shows as true
            cleaning.setChecked(true);
        }
        else{
            cleaning.setChecked(false);
        }
        final CheckBox computer = (CheckBox) findViewById(R.id.pref_computer);
        if (babysitting.isChecked()) { //database shows as true
            computer.setChecked(false);
        }
        else{
            computer.setChecked(true);
        }
        final CheckBox delivery = (CheckBox) findViewById(R.id.pref_delivery);
        if (babysitting.isChecked()) { //database shows as true
            delivery.setChecked(true);
        }
        else{
            delivery.setChecked(false);
        }
        final CheckBox dogWalking = (CheckBox) findViewById(R.id.pref_dog);
        if (babysitting.isChecked()) { //database shows as true
            dogWalking.setChecked(true);
        }
        else{
            dogWalking.setChecked(false);
        }
        final CheckBox otherCheck = (CheckBox) findViewById(R.id.pref_other);
        if (babysitting.isChecked()) { //database shows as true
            otherCheck.setChecked(true);
        }
        else{
            otherCheck.setChecked(false);
        }
        //}

        String firebaseFirstLevel = "user";
        final String firebaseSecondLevel = ("USER-" + String.valueOf(userNumber));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(firebaseFirstLevel).child(firebaseSecondLevel);


        title = (TextView)findViewById(R.id.pref_title);
        backBtn = (Button) findViewById(R.id.pref_backdashBtn);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.putExtra("User", userNumber);
                startActivity(intent);
            }
        });


    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.pref_babysitting:
                if (checked) {
                    // push to database
                }
                else {
                    // also push to database
                }
                break;
            case R.id.pref_cleaning:
                if (checked) {
                    // push to database
                }
                else{
                    // push to database
                }
                break;
            case R.id.pref_computer:
                if (checked){

                }
                else{

                }
            case R.id.pref_delivery:
                if (checked){

                }
                else{

                }
            case R.id.pref_dog:
                if (checked){

                }
                else{

                }
            case R.id.pref_other:
                if (checked){

                }
                else{

                }
        }
    }
}
