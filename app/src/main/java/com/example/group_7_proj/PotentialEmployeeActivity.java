package com.example.group_7_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PotentialEmployeeActivity extends AppCompatActivity {
    DatabaseReference reff;
    long maxUser = 0;
    String jobType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.potential_employee);
        Intent intent = getIntent();
        jobType = intent.getStringExtra("Job Type");

        // insert code here
        reff = FirebaseDatabase.getInstance().getReference().child("user");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {
                maxUser = (snapshot.getChildrenCount());
                final LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutDisplaySearchInner);
                int potEmpCount = 0;
                ArrayList<String> userPref;
                Object currPref;

                for(long userID = 1; userID < maxUser+1; userID++) {
                    userPref = new ArrayList<String>();
                    int maxPref = 6;
                    for (int p = 0; p < maxPref+1; p++) {
                        currPref = snapshot.child("USER-" + userID).child("Job Preferences").child(String.valueOf(p)).getValue();
                        if(currPref!=null) {
                            userPref.add(currPref.toString());
                        }
                    }

                    String userName = snapshot.child("USER-" + userID).child("name").getValue().toString();
                    String userEmail = snapshot.child("USER-" + userID).child("email").getValue().toString();

                    if(userPref.contains(jobType)){
                        potEmpCount += 1;
                        final TextView userNameTextview = new TextView(getApplicationContext());
                        final TextView userEmailTextview = new TextView(getApplicationContext());

                        userNameTextview.setText(userName);
                        userNameTextview.setTextSize(30);
                        userNameTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        userEmailTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        userEmailTextview.setText(userEmail);

                        myLayout.addView(userNameTextview);
                        myLayout.addView(userEmailTextview);
                    }

                }

                if(potEmpCount == 0){
                    final TextView noResultShowing = new TextView(getApplicationContext());
                    noResultShowing.setText("No result here.");
                    myLayout.addView(noResultShowing);
                    Toast.makeText(PotentialEmployeeActivity.this, "Sorry We found nothing",Toast.LENGTH_LONG).show();
                }

                Button clearResultsBtn = findViewById(R.id.backBtnPE);
                clearResultsBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), JobPostActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}