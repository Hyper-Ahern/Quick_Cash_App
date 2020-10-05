package com.example.group_7_proj;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        ImageButton googleLoginBtn =(ImageButton)findViewById(R.id.googleLoginBtn);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        googleLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                    builder.setMessage("The sum is "+resultTextView.getText().toString()).setTitle("Addition Operation")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id){

                                }
                            });

                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });


    }
}