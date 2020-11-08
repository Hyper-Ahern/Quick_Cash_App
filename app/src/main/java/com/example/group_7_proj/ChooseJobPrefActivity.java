package com.example.group_7_proj;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChooseJobPrefActivity extends AppCompatActivity {
    private Button submitBtn;
    private CheckBox deliveryCategory, cleanCategory, compCategory, babysitCategory, otherCategory;

    private ArrayList<String> selectedJobCategories;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        submitBtn = findViewById(R.id.btnPickJobPref);

        deliveryCategory = findViewById(R.id.ckbxDelivery);
        cleanCategory = findViewById(R.id.ckbxClean);
        compCategory = findViewById(R.id.ckbxComp);
        babysitCategory = findViewById(R.id.ckbxBabysit);
        otherCategory = findViewById(R.id.ckbxOther);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_jobpref);

        String firebaseFirstLevel = "user";
        Intent callerIntent = getIntent();
        Bundle fromCaller = callerIntent.getBundleExtra("package");
        final Long userNumber = fromCaller.getLong("User") + 1;
        final String firebaseSecondLevel = ("USER-" + userNumber);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(firebaseFirstLevel).child(firebaseSecondLevel);


        //submit btn onclick

        //Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        //startActivity(intent);
    }
}
