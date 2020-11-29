package com.example.group_7_proj;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChooseJobPrefActivity extends AppCompatActivity {
    private Button submitBtn;
    private CheckBox deliveryCategory, cleanCategory, compCategory, babysitCategory, otherCategory;

    String userNumber = "";

    private ArrayList<String> selectedJobCategories;

    AlertDialog.Builder builder;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_jobpref);

        submitBtn = findViewById(R.id.btnPickJobPref);
        selectedJobCategories = new ArrayList<String>();

        deliveryCategory = findViewById(R.id.ckbxDelivery);
        cleanCategory = findViewById(R.id.ckbxClean);
        compCategory = findViewById(R.id.ckbxComp);
        babysitCategory = findViewById(R.id.ckbxBabysit);
        otherCategory = findViewById(R.id.ckbxOther);

        String firebaseFirstLevel = "user";
        Intent callerIntent = getIntent();
        userNumber = callerIntent.getStringExtra("User");//null on testing comment out when running espresso tests
        final String firebaseSecondLevel = ("USER-" + userNumber); //use 1 for userNumber when testing

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(firebaseFirstLevel).child(firebaseSecondLevel);

        builder = new AlertDialog.Builder(this);

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (deliveryCategory.isChecked()){
                    selectedJobCategories.add("Delivery");
                }
                if (cleanCategory.isChecked()){
                    selectedJobCategories.add("Cleaning");
                }
                if (babysitCategory.isChecked()){
                    selectedJobCategories.add("Babysitting");
                }
                if (otherCategory.isChecked()){
                    selectedJobCategories.add("Other");
                }
                Map<String, Object> userJobCategories = new HashMap<>();
                userJobCategories.put("Job Preferences",selectedJobCategories);
                rootRef.updateChildren(userJobCategories);
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.putExtra("User",userNumber);
                startActivity(intent);
            }
        });
    }
}
