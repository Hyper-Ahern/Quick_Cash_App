package com.example.group_7_proj;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MakePaymentPopupActivity extends AppCompatActivity {
    private String jobName;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_payment_popup);

        builder = new AlertDialog.Builder(this);

    }
}
