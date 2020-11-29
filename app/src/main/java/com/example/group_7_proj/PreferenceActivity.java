package com.example.group_7_proj;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PreferenceActivity extends AppCompatActivity {
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


        String firebaseFirstLevel = "user";
        final String firebaseSecondLevel = ("USER-" + String.valueOf(userNumber));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(firebaseFirstLevel).child(firebaseSecondLevel);
        //DatabaseReference cardRef = rootRef.child("cards");


        title = (TextView)findViewById(R.id.pref_title);
        submitBtn = (Button) findViewById(R.id.pref_SubmitBtn);
        backBtn = (Button) findViewById(R.id.pref_backdashBtn);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

            }

        });
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.putExtra("User", userNumber);
                startActivity(intent);
            }
        });


    }
}
