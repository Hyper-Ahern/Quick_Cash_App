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

public class ChooseJobPrefActivity extends AppCompatActivity {
    private Button submitBtn;
    private CheckBox deliveryCategory, cleanCategory, compCategory, babysitCategory, otherCategory;

    private ArrayList<String> selectedJobCategories;

    AlertDialog.Builder builder;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_jobpref);

        submitBtn = findViewById(R.id.btnPickJobPref);

        deliveryCategory = findViewById(R.id.ckbxDelivery);
        cleanCategory = findViewById(R.id.ckbxClean);
        compCategory = findViewById(R.id.ckbxComp);
        babysitCategory = findViewById(R.id.ckbxBabysit);
        otherCategory = findViewById(R.id.ckbxOther);



       // String firebaseFirstLevel = "user";
       // Intent callerIntent = getIntent();
       // Bundle fromCaller = callerIntent.getBundleExtra("package");
       // final Long userNumber = fromCaller.getLong("User") + 1;
       // final String firebaseSecondLevel = ("USER-" + userNumber);

       // final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child(firebaseFirstLevel).child(firebaseSecondLevel);

        builder = new AlertDialog.Builder(this);

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (deliveryCategory.isChecked()){
                    selectedJobCategories.add("Delivery");
                }

                //submit btn onclick

                //Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                //startActivity(intent);
            }
        });
    }
}
