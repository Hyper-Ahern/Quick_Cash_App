package com.example.group_7_proj;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseJobPrefActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_jobpref);

        String firebaseFirstLevel = "user";
        Intent callerIntent = getIntent();
        Bundle fromCaller = callerIntent.getBundleExtra("package");
        final Long userNumber = fromCaller.getLong("User") + 1;
        final String firebaseSecondLevel = ("USER-" + userNumber);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(firebaseFirstLevel).child(firebaseSecondLevel);

        //Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        //startActivity(intent);
    }
}
