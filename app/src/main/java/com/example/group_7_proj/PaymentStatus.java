package com.example.group_7_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

       /* try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"),intent.getStringExtra("PaymentAmount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        showDetails(intent.getStringExtra("Amount"),intent.getStringExtra("JobID"));










        final String paypalUserNum =  intent.getStringExtra("userNumPaypal");





        // Creating a back to dashboard button at the bottom of all the posts
        reff = FirebaseDatabase.getInstance().getReference();
        paymentStatusBacktoMain = findViewById(R.id.pmtStatusBacktoMain);
        paymentStatusBacktoMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        refPayPal = FirebaseDatabase.getInstance().getReference().child("paypalIndex");
                        refPayPal.child("jobID").setValue("Empty");
                        /*
                        reff2 = FirebaseDatabase.getInstance().getReference().child("JOBPOST");

                        if (snapshot.hasChild("Empty")) {
                            reff2.child("Empty").removeValue();
                        }
                        */
                        userNumber = snapshot.child("paypalIndex").child("userID").getValue().toString();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });



                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.putExtra("User",paypalUserNum);

                startActivity(intent);


            }
        });


    }

    private void showDetails(String paymentAmount, String jobID) {
        txtId.setText("ID -- "+responseData.getResponse().getId());
        txtStatus.setText("Status -- "+responseData.getResponse().getState());
        reff2 = FirebaseDatabase.getInstance().getReference().child("JOBPOST");
        refPayPal = FirebaseDatabase.getInstance().getReference().child("paypalIndex");
        reff2.child(jobID).child("paymentStatus").setValue("Paid");
        /*
        refPayPal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("jobID").getValue().toString();
                String userNumber =snapshot.child("userID").getValue().toString();
                String checker = txtStatus.getText().toString();

                if(checker.equals("Status -- approved")){
                    reff2.child(name).child("paymentStatus").setValue("Paid");

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



        });
        */










        txtAmount.setText("Amount -- $" + paymentAmount);

        //intentChecker.setText("Checker" + name);





        }
}
