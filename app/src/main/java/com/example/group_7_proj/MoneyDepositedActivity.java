package com.example.group_7_proj;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

//job name from list of job posts paid
//on paid trigger popup for respective employee

//how to open to respective employee

//jobname clickable to post?

//on click of okay btn dismiss popup


/**
 * Popup for Employee to confirm the payment/money has been deposited into account
 * after action from employer
 *
 * properties:
 * jobName = corresponding job name of job payed to employee
 */
public class MoneyDepositedActivity extends AppCompatActivity {

    private String jobName;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_deposited_popup);

        builder = new AlertDialog.Builder(this);

    }
}
