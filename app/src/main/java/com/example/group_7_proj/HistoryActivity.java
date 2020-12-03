package com.example.group_7_proj;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.group_7_proj.CustomDataTypes.JobPost;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class HistoryActivity extends AppCompatActivity {
    Button historyBackToMainBtn;
    DatabaseReference reff,refPayPal;
    Object lastNode;
    long maxPost = 0;
    String userNumber = "";
    public static long jobID;

    String CHANNEL_ID = "HistoryActivity";
    int notificationId = 1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        Intent callerIntent = getIntent();
        userNumber = callerIntent.getStringExtra("User");
        // call the notification function
        createNotificationChannel();

        reff = FirebaseDatabase.getInstance().getReference().child("JOBPOST");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {

                maxPost = (snapshot.getChildrenCount());
                LinearLayout myLayout = (LinearLayout) findViewById(R.id.layoutdisplay);

                // Iterarate through all the job posts and render only those that are user created
                for (jobID = 1; jobID < maxPost+1; jobID++) {
                    if (snapshot.hasChild("JOBPOST-" + jobID)) {
                    String trigger = snapshot.child("JOBPOST-" + jobID).child("userID").getValue().toString();
                    //final DataSnapshot lastNode = (DataSnapshot) snapshot.child("JOBPOST-" + maxPost).getValue();

                    // If the current user made the post, render the job, else don't
                    if (trigger.equals(userNumber)) {

                        final long tempJobID = jobID;
                        final String userNumberPaypal = userNumber;

                        // Get values of the database fields
                        String employerName = snapshot.child("JOBPOST-" + jobID).child("employerName").getValue().toString();
                        String jobDetails = snapshot.child("JOBPOST-" + jobID).child("jobDetails").getValue().toString();
                        String jobTitle = snapshot.child("JOBPOST-" + jobID).child("jobTitle").getValue().toString();
                        String jobType = snapshot.child("JOBPOST-" + jobID).child("jobType").getValue().toString();
                        String salary = snapshot.child("JOBPOST-" + jobID).child("salary").getValue().toString();

                        // Create the text views
                        final TextView jobIDTextview = new TextView(getApplicationContext());
                        final TextView jobTitleTextview = new TextView(getApplicationContext());
                        final TextView employerNameTextview = new TextView(getApplicationContext());
                        final TextView jobTypeTextview = new TextView(getApplicationContext());
                        final TextView jobDetailsTextview = new TextView(getApplicationContext());
                        final TextView salaryTextview = new TextView(getApplicationContext());

                        // Set teh values that were received from the dabase to the text views
                        jobTitleTextview.setText(jobTitle);
                        jobTitleTextview.setTextSize(30);
                        jobTitleTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        jobIDTextview.setText("Job ID: " + jobID);
                        jobIDTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        employerNameTextview.setText("Employer Name: " + employerName);
                        employerNameTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        jobTypeTextview.setText("Job Type: " + jobType);
                        jobTypeTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        jobDetailsTextview.setText("Details: " + jobDetails);
                        jobDetailsTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));
                        salaryTextview.setText("Salary: " + salary + "\n");
                        salaryTextview.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.common_google_signin_btn_text_dark_default));


                        // Add those text views to the layout so the user can see them
                        myLayout.addView(jobTitleTextview);
                        myLayout.addView(jobIDTextview);
                        myLayout.addView(jobTypeTextview);
                        myLayout.addView(employerNameTextview);
                        myLayout.addView(jobDetailsTextview);
                        myLayout.addView(salaryTextview);

                        // Creating the edit button for each post
                        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        Button edtBtn = new Button(getApplicationContext());
                        int editJobID = (int) jobID;
                        final int editJobIDMinusOne = editJobID;
                        edtBtn.setId(editJobID);
                        final int editID_ = edtBtn.getId();
                        edtBtn.setText("Edit ");
                        edtBtn.setBackgroundColor(Color.rgb(152,88,54));
                        LinearLayout editLayout = (LinearLayout) findViewById(R.id.layoutdisplay);
                        editLayout.addView(edtBtn, editParams);

                        //When cancel button is clicked, send a bundleof info back to editpostactivity to reload the page
                        edtBtn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), EditPostActivity.class);
                                Bundle mBundle = new Bundle();
                                mBundle.putString("postID", "JOBPOST-" + editJobIDMinusOne);
                                mBundle.putString("User", userNumber);
                                intent.putExtras(mBundle);
                                startActivity(intent);
                            }
                        });

                        // Creating the delete button for each post
                        LinearLayout.LayoutParams deleteParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        Button dltBtn = new Button(getApplicationContext());
                        int deleteJobID = (int) jobID;
                        final int deleteJobIDMinusOne = deleteJobID;
                        dltBtn.setId(deleteJobID);
                        final int deleteID_ = dltBtn.getId();
                        dltBtn.setText("Delete ");
                        dltBtn.setBackgroundColor(Color.rgb(210,146,111));
                        LinearLayout deleteLayout = (LinearLayout) findViewById(R.id.layoutdisplay);
                        deleteLayout.addView(dltBtn, deleteParams);


                        //When delete is clicked, remove the child from the database and re render the page
                        dltBtn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                System.out.println("JOBPOST-" + jobID);





                                    DatabaseReference lastNode = FirebaseDatabase.getInstance().getReference().child("JOBPOST").child("JOBPOST-" + maxPost);
                                    DatabaseReference toPath = FirebaseDatabase.getInstance().getReference().child("JOBPOST").child("JOBPOST-" + deleteJobIDMinusOne);




                                    String Emname = snapshot.child("JOBPOST-" + maxPost).child("employerName").getValue().toString();
                                    String jobtitle1 = snapshot.child("JOBPOST-" + maxPost).child("jobTitle").getValue().toString();
                                    String salary = snapshot.child("JOBPOST-" + maxPost).child("salary").getValue().toString();
                                    String detail = snapshot.child("JOBPOST-" + maxPost).child("jobDetails").getValue().toString();
                                    String jobType = snapshot.child("JOBPOST-" + maxPost).child("jobType").getValue().toString();
                                    int intUserNum = Integer.parseInt(snapshot.child("JOBPOST-" + maxPost).child("userID").getValue().toString());
                                    String completionStatus = snapshot.child("JOBPOST-" + maxPost).child("completionStatus").getValue().toString();
                                    String paymentStatus = snapshot.child("JOBPOST-" + maxPost).child("paymentStatus").getValue().toString();
                                    int employeeID = Integer.parseInt(snapshot.child("JOBPOST-" + maxPost).child("employeeID").getValue().toString());
                                    Object geoLocation = snapshot.child("JOBPOST-" + maxPost).child("geoLocation").getValue();

                                    final JobPost j1 = new JobPost(Emname,jobtitle1,jobType,salary,detail,intUserNum,paymentStatus,completionStatus);

                                    toPath.setValue(j1);
                                    toPath.child("employeeID").setValue(employeeID);
                                    toPath.child("geoLocation").setValue(geoLocation);

                                    lastNode.removeValue();
                                    // if a job post has been deleted, then the maxPost should minus by 1
                                    maxPost--;

                                    Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                                    intent.putExtra("User", userNumber);
                                    startActivity(intent);
                            }
                        });

                        //Create a Make a payment button
                        LinearLayout.LayoutParams makePaymentParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        Button mkPaymentBtn = new Button(getApplicationContext());
                        // int deleteJobID = (int) jobID;
                        //final int deleteJobIDMinusOne = deleteJobID;
                        mkPaymentBtn.setText("Make A Payment");
                        mkPaymentBtn.setBackgroundColor(Color.rgb(236,186,160));
                        LinearLayout mkPaymentLayout = (LinearLayout) findViewById(R.id.layoutdisplay);
                        mkPaymentLayout.addView(mkPaymentBtn, makePaymentParams);

                        mkPaymentBtn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {

                                // Send notification when the button is clicked
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(HistoryActivity.this, CHANNEL_ID)

                                        .setContentTitle("Hahaha")
                                        .setContentText("World")
                                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                                        .setAutoCancel(true);


                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(HistoryActivity.this);

                                // notificationId is a unique int for each notification that you must define
                                notificationManager.notify(notificationId, builder.build());

                                Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
                                intent.putExtra("JobID", "JOBPOST-" + tempJobID);
                                intent.putExtra("UserNumPaypal", userNumberPaypal);
                                startActivity(intent);
                            }
                        });
                    }
                }
                    //else{jobID++;}
            }

                // Creating a back to dashboard button at the bottom of all the posts
                historyBackToMainBtn = findViewById(R.id.historyBackToDBBtn);
                historyBackToMainBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        intent.putExtra("User", userNumber);

                        startActivity(intent);
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "test";//getString(R.string.channel_name);
            String description = "testDescrip";//getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
