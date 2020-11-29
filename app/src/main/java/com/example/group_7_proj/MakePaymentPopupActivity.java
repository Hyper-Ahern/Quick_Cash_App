package com.example.group_7_proj;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

//login /  or on db add?
//notification for new preferred jobs,
// **notification for completed jobs / make a payment, DISMISS options
// **notification for paid jobs or money deposited / DISMISS option

public class MakePaymentPopupActivity extends AppCompatActivity {
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
        contentText = new String(jobName + " has been completed.");

        intent = new Intent(this, this.getClass()); //created job post page
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0); //created job post page


        builder = new NotificationCompat.Builder(this)
        .setContentTitle(contentTitle)
        .setContentText(contentText)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .addAction(R.drawable.ic_money, "Make A Payment", pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

    }
}
