package com.example.group_7_proj;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;

public class PaymentInfoUI extends AppCompatActivity {



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_info_ui);


        final EditText cardNumberField;
        final EditText expiryDateField;
        final EditText CVVField;
        final EditText nameField;
        final Button submitBtn;
        final TextView statusButton;
        final Card card;
        card = new Card();

        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Card");


        cardNumberField = (EditText) findViewById(R.id.cardNumber);
        expiryDateField = (EditText) findViewById(R.id.expiryDate);
        CVVField = (EditText) findViewById(R.id.CVV);
        nameField = (EditText) findViewById(R.id.cardHolderName);
        statusButton = (TextView)findViewById(R.id.statusBtn);
        submitBtn = (Button) findViewById(R.id.paymentSubmitButton);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                String cardNum = cardNumberField.getText().toString();
                String expiryDate = expiryDateField.getText().toString();
                String cvv =  CVVField.getText().toString();
                String cardName = nameField.getText().toString();
                PaymentValidator cardValidate = new PaymentValidator(cardNum, expiryDate, cvv, cardName);

                if (cardValidate.paymentValidate()==true) {
                    card.setCardNum(cardNum);
                    card.setExpiryDate(expiryDate);
                    card.setCVV(cvv);
                    card.setCardHolderName(cardName);
                    rootRef.push().setValue(card);
                    statusButton.setText("Card info OK");
                    //Toast.makeText(PaymentInfoUI.this, "Card registration complete",Toast.LENGTH_LONG).show();
                }
                else {
                    statusButton.setText("Invalid Card info");
                }

            }

        });


    }
}
