package com.example.group_7_proj;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FirebaseTests_US12{
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    static FirebaseDatabase database = null;
    static DatabaseReference userRef = null;
    static String name;
    static String email;
    static String password;
    static String rePassword;
    static long maxId = 0;

    @BeforeClass
    public static void setup(){
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference().child("User");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxId = (snapshot.getChildrenCount());
                    name = snapshot.child("USER-"+maxId).child("name").getValue(String.class);
                    email = snapshot.child("USER-"+maxId).child("email").getValue(String.class);
                    password = snapshot.child("USER-"+maxId).child("password").getValue(String.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    // check if data is stored in database
    @Test
    public void checkIfSignUpInfoIsStoreInDatabase() {
        pressBack();
        onView(withId(R.id.name)).perform(click()).perform(typeText("abcde"));
        onView(withId(R.id.email)).perform(click()).perform(typeText("abcde@xyz.com"));
        onView(withId(R.id.password)).perform(click()).perform(typeText("abCD56&*"));
        onView(withId(R.id.reenterPassword)).perform(click()).perform(typeText("abCD56&*"));
        onView(withId(R.id.signUpBtn)).perform(click());
        assertEquals("abcde",name);
        assertEquals("abcde@xyz.com",email);
        assertEquals("abCD56&*",password);
    }

    @AfterClass
    public static void teardown(){
        database = null;
    }

}
