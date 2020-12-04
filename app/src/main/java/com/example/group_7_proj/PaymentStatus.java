package com.example.group_7_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.group_7_proj.CustomDataTypes.JobPost;
import com.example.group_7_proj.CustomDataTypes.PaypalIndex;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.group_7_proj.PaymentModel;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentStatus extends AppCompatActivity {

    Button paymentStatusBacktoMain;

    //PaypalIndex paypalIndex;
    String userNumber;

    TextView txtId, txtAmount, txtStatus, intentChecker;
    DatabaseReference reff,reff2,refPayPal;
    PaymentModel responseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paypal_payment_status);

        txtId = findViewById(R.id.txtId);
        txtAmount = findViewById(R.id.txtAmount);
        txtStatus = findViewById(R.id.txtStatus);
        intentChecker = findViewById(R.id.intentChecker);

        Intent intent = getIntent();

        GsonBuilder builder = new GsonBuilder();
        Gson mGson = builder.create();

        responseData = mGson.fromJson(intent.getStringExtra("PaymentDetails"),PaymentModel.class);

        final String jobID = intent.getStringExtra("JobID");

        final String paypalUserNum =  intent.getStringExtra("userNumPaypal");

        String paymentAmount = intent.getStringExtra("Amount");

        showDetails(paymentAmount,jobID);

        // Creating a back to dashboard button at the bottom of all the posts
        paymentStatusBacktoMain = findViewById(R.id.pmtStatusBacktoMain);
        paymentStatusBacktoMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.putExtra("User",paypalUserNum);
                startActivity(intent);
            }
        });
    }

    private void showDetails(String paymentAmount, final String jobID) {
        txtId.setText("ID -- "+responseData.getResponse().getId());
        txtStatus.setText("Status -- "+responseData.getResponse().getState());
        reff2 = FirebaseDatabase.getInstance().getReference().child("JOBPOST");
        refPayPal = FirebaseDatabase.getInstance().getReference().child("paypalIndex");
        reff2.child(jobID).child("paymentStatus").setValue("Paid");

        // when a job is completed and paid, it should will be deleted
        reff = FirebaseDatabase.getInstance().getReference().child("JOBPOST");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long maxPost = (snapshot.getChildrenCount());
                System.out.print(maxPost);
                if ((snapshot.child(jobID).child("completionStatus").getValue().toString()).equals("Completed")) {


                    DatabaseReference lastNode = FirebaseDatabase.getInstance().getReference().child("JOBPOST").child("JOBPOST-" + maxPost);
                    DatabaseReference toPath = FirebaseDatabase.getInstance().getReference().child("JOBPOST").child(jobID);

                    String Emname = snapshot.child("JOBPOST-" + maxPost).child("employerName").getValue().toString();
                    String jobtitle1 = snapshot.child("JOBPOST-" + maxPost).child("jobTitle").getValue().toString();
                    String salary = snapshot.child("JOBPOST-" + maxPost).child("salary").getValue().toString();
                    String detail = snapshot.child("JOBPOST-" + maxPost).child("jobDetails").getValue().toString();
                    String jobType = snapshot.child("JOBPOST-" + maxPost).child("jobType").getValue().toString();
                    int intUserNum = Integer.parseInt(snapshot.child("JOBPOST-" + maxPost).child("userID").getValue().toString());
                    String completionStatus = snapshot.child("JOBPOST-" + maxPost).child("completionStatus").getValue().toString();
                    String paymentStatus = snapshot.child("JOBPOST-" + maxPost).child("paymentStatus").getValue().toString();
                    Object geoLocation = snapshot.child("JOBPOST-" + maxPost).child("geoLocation").getValue();
                    //int employeeID = Integer.parseInt(snapshot.child("JOBPOST-" + maxPost).child("employeeID").getValue().toString());
                    String employeeID = snapshot.child("JOBPOST-" + maxPost).child("employeeID").getValue().toString();

                    final JobPost j1 = new JobPost(Emname, jobtitle1, jobType, salary, detail, intUserNum, paymentStatus, completionStatus);

                    toPath.setValue(j1);
                    toPath.child("employeeID").setValue(employeeID);
                    toPath.child("geoLocation").setValue(geoLocation);

                    lastNode.removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        txtAmount.setText("Amount -- $" + paymentAmount);

        }
}
