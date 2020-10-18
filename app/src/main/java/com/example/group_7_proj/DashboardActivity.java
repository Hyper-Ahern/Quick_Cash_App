package com.example.group_7_proj;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {
    Button backBtn, postAJobBtn, payEmployeeBtn, allJobPostBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        backBtn = (Button)findViewById(R.id.backBtnDB);
        postAJobBtn = (Button)findViewById(R.id.postJobBtnDB);
        payEmployeeBtn = (Button)findViewById(R.id.payEmpBtnDB);
        allJobPostBtn = (Button)findViewById(R.id.allJobsBtnDB);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        postAJobBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), JobPostActivity.class);
                startActivity(intent);
            }
        });

        payEmployeeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), PaymentInfoActivity.class);
                startActivity(intent);
            }
        });

        allJobPostBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), JobPostviewActivity.class);
                startActivity(intent);
            }
        });

    }
}