package com.example.group_7_proj;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.group_7_proj.CustomDataTypes.GeoLocation;
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


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JobPostEspressoTest {
    @Rule
    public ActivityScenarioRule<JobPostActivity> jobpostActivityRule =
            new ActivityScenarioRule<>(JobPostActivity.class);
    static FirebaseDatabase database = null;
    static DatabaseReference jobRef = null;
    static DatabaseReference userRef = null;
    static GeoLocation jobGeoTag, userGeoTag;
    static String userID = "";
    static String longiJ, latiJ, longiU, latiU;
    static long maxId = 0;

    @BeforeClass
    public static void setup(){
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference().child("user");
        jobRef = database.getReference().child("jobTypeTest");

        /*jobRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                *//*if(snapshot.exists()) {
                    maxId = snapshot.getChildrenCount();
                    userID = snapshot.child("JOBPOST-"+String.valueOf(maxId)).child("userID").getValue(String.class);
                    longiJ = snapshot.child("JOBPOST-"+String.valueOf(maxId)).child("geoTag").child("longitude").getValue(String.class);
                    latiJ = snapshot.child("JOBPOST-"+String.valueOf(maxId)).child("geoTag").child("latitude").getValue(String.class);
                }*//*
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                *//*if(snapshot.exists()) {
                    longiU = snapshot.child("USER-"+userID).child("geoTag").child("longitude").getValue(String.class);
                    latiU = snapshot.child("USER-"+userID).child("geoTag").child("latitude").getValue(String.class);
                }*//*
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/
        }

    /**INPUT VALIDATION**/
    @Test
    // checks if the details text box is empty and displays appropriate message
    public void emptyDetails() {
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("Babysitter")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Babysitting"))).perform(click());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Invalid Detail info")));
    }

    @Test
    // checks if the titles text box is empty and displays appropriate message
    public void emptyTitle() {
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Babysitting"))).perform(click());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("1sdfasd")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Invalid Job Title info")));
    }

    @Test
    // checks if the titles text box is empty and displays appropriate message
    public void specialCharactersStringTitle() {
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("!!&")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Babysitting"))).perform(click());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("1sdfasd")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Invalid Job Title info")));
    }

    @Test
    // checks if the salary box is empty and displays appropriate message
    public void emptySalary() {
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("Babysitter")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Babysitting"))).perform(click());
        onView(withId(R.id.salaryInputText)).perform(typeText("")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("1sdfasd")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Invalid Salary info")));
    }

    @Test
    // checks if the employer text box is empty and displays appropriate message
    public void emptyEmployer() {
        onView(withId(R.id.employerNameText)).perform(typeText("")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("Babysitter")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Babysitting"))).perform(click());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("1sdfasd")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Invalid Employer info")));
    }

    @Test
    public void checkValidJobType(){
        onView(withId(R.id.employerNameText)).perform(typeText("ABC Inc")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("computer repair")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Computer"))).perform(click());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("1sdfasd")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        pressBack();
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Job Posted Successfully")));
    }

    @Test
    public void checkInvalidJobType(){
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("Babysitter")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("--Please select--"))).perform(click());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("1sdfasd")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Invalid Job Type info")));
    }

    @Test
    public void checkGeoTagAddedToJob(){
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("Babysitter")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Computer"))).perform(click());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("1sdfasd")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        pressBack();
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Job Posted Successfully")));
    }

    @AfterClass
    public static void teardown(){
        database = null;
    }
}
