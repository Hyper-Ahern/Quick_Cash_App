package com.example.group_7_proj;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class JobPost extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobpost);
    }






//    public long Post(String word, String definition)
//    {
//        ContentValues initialValues = new ContentValues();
//        initialValues.put(COL_Employer, Employer);
//        initialValues.put(COL_JobTitle, JobTitle);
//        initialValues.put(COL_Filtertype, Filtertype);
//        initialValues.put(COL_Salay, Salay);
//        initialValues.put(COL_ComInf,ComInf);
//        return CSCI3130FIREBASE.insert(FTS_VIRTUAL_TABLE, null, initialValues);
//    }
    public static boolean InvalidSalary(String S)
    {
        Pattern p1 = Pattern.compile("[0-90-9.0-90-9]");
        if(S.length()==0||!p1.matcher(S).find())
        {
            return false;
        }
        return true;
    }
    public static boolean InvalidEmail(String e)
    {

        if(e.length()==0||!android.util.Patterns.EMAIL_ADDRESS.matcher(e).matches())
        //direct function for check validation https://stackoverflow.com/questions/29492168/android-util-patterns-email-address-is-validating-invalid-emails/29493187
        {
            return false;
        }
        return true;
    }

}
