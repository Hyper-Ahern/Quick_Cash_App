package com.example.group_7_proj;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PrefActivity extends AppCompatActivity {
    String userNumber = "";
    final String firebaseFirstLevel = "user";
    String firebaseSecondLevel = "";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference);
        Intent callerIntent = getIntent();
        userNumber = callerIntent.getStringExtra("User");

        final TextView title;
        final Button backBtn, submitBtn;
        final CheckBox babysitting = (CheckBox) findViewById(R.id.pref_babysitting);
        final CheckBox cleaning = (CheckBox) findViewById(R.id.pref_cleaning);
        final CheckBox computer = (CheckBox) findViewById(R.id.pref_computer);
        final CheckBox delivery = (CheckBox) findViewById(R.id.pref_delivery);
        final CheckBox dogWalking = (CheckBox) findViewById(R.id.pref_dog);
        final CheckBox otherCheck = (CheckBox) findViewById(R.id.pref_other);

        firebaseSecondLevel = ("USER-" + String.valueOf(userNumber));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(firebaseFirstLevel).child(firebaseSecondLevel).child("Job Preferences");

        //fills the checkboxes bases on database.
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(String.valueOf(1))){
                    if (snapshot.child("1").getValue().equals("Babysitting")) { //database shows as true
                        babysitting.setChecked(true);
                    }
                    else{
                        babysitting.setChecked(false);
                    }
                }
                else{
                    babysitting.setChecked(false);
                }
                if (snapshot.hasChild("2")){
                    if (snapshot.child("2").getValue().equals("Cleaning")) { //database shows as true
                        cleaning.setChecked(true);
                    }
                    else{
                        cleaning.setChecked(false);
                    }
                }
                else{
                    cleaning.setChecked(false);
                }

                if (snapshot.hasChild("3")){
                    if (snapshot.child("3").getValue().equals("Computer")) { //database shows as true
                        computer.setChecked(true);
                    }
                    else{
                        computer.setChecked(false);
                    }
                }
                else{
                    computer.setChecked(false);
                }

                if (snapshot.hasChild("4")){
                    if (snapshot.child("4").getValue().equals("Delivery")) { //database shows as true
                        delivery.setChecked(true);
                    }
                    else{
                        delivery.setChecked(false);
                    }
                }
                else{
                    delivery.setChecked(false);
                }

                if (snapshot.hasChild("5")){
                    if (snapshot.child("5").getValue().equals("Dog Walking")) { //database shows as true
                        dogWalking.setChecked(true);
                    }
                    else{
                        dogWalking.setChecked(false);
                    }
                }
                else{
                    dogWalking.setChecked(false);
                }

                if (snapshot.hasChild("6")){
                    if (snapshot.child("6").getValue().equals("Other")) { //database shows as true
                        otherCheck.setChecked(true);
                    }
                    else{
                        otherCheck.setChecked(false);
                    }
                }
                else{
                    otherCheck.setChecked(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){

            }
        });





        title = (TextView)findViewById(R.id.pref_title);
        backBtn = (Button) findViewById(R.id.pref_backdashBtn);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if (babysitting.isChecked()==true){
                    rootRef.child("1").setValue("Babysitting");
                }
                else{
                    rootRef.child("1").setValue(null);
                }
                if (cleaning.isChecked()==true){
                    rootRef.child("2").setValue("Cleaning");
                }
                else{
                    rootRef.child("2").setValue(null);
                }
                if (computer.isChecked()==true){
                    rootRef.child("3").setValue("Computer");
                }
                else{
                    rootRef.child("3").setValue(null);
                }
                if (delivery.isChecked()==true){
                    rootRef.child("4").setValue("Delivery");
                }
                else{
                    rootRef.child("4").setValue(null);
                }
                if (dogWalking.isChecked()==true){
                    rootRef.child("5").setValue("Dog Walking");
                }
                else{
                    rootRef.child("5").setValue(null);
                }
                if (otherCheck.isChecked()==true){
                    rootRef.child("6").setValue("Other");
                }
                else{
                    rootRef.child("6").setValue(null);
                }



                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.putExtra("User", userNumber);
                intent.putExtra("Pref", "true");
                startActivity(intent);
            }
        });
    }
}
