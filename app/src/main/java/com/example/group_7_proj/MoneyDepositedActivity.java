package com.example.group_7_proj;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

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
    private String contentTitle;
    private String contentText;
    private Intent intent;
    private PendingIntent pendingIntent;

    NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.make_payment_popup);
        jobName = "JobName"; //get job name from parameter pass?
        contentTitle = "Job Completed"; //new String(jobName+ " has been completed")
        contentText = new String("Money desposited for " + jobName +"!");

        intent = new Intent(this, this.getClass()); //accepted job post page
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0); //accepted job post page


        builder = new NotificationCompat.Builder(this)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);
    }
}
