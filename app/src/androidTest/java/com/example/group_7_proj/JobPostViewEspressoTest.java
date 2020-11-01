package com.example.group_7_proj;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.hamcrest.Matcher;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class JobPostViewEspressoTest {
    @Rule
    public ActivityScenarioRule<JobPostviewActivity> jobpostviewActivityRule =
            new ActivityScenarioRule<>(JobPostviewActivity.class);

    /**INPUT VALIDATION**/
    @Test
    // checks if the details text box is empty and displays appropriate message
    public void ButtonCanBackToDash() {
        onView(withId(R.id.jobpostview)).perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        onView(withId(R.id.backdashbtn)).perform(click());
        onView(withId(R.id.dashboard));
    }

/*    @Test
    // checks if the titles text box is empty and displays appropriate message
    public void LayoutDisplayFine() {
        swipeUp();
        swipeUp();
        onView(withId(R.id.backdashbtn)).perform(click());
        onView(withId(R.id.postJobBtnDB)).perform(click());
        onView(withId(R.id.jobPost));
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("")).perform(closeSoftKeyboard());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("Farming")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.BackdashBtn)).perform(click());
        onView(withId(R.id.allJobsBtnDB)).perform(click());
        onView(withId(R.id.jobpostview)).perform(swipeUp());
        onView(withId(R.id.layoutdisplay));
    }*/

    @Test
    // checks if the buttons are displayed
    public void CategoryButtonsInDisplay() {
        onView(withId(R.id.categoryBtn1));
        onView(withId(R.id.categoryBtn2));
        onView(withId(R.id.categoryBtn3));
        onView(withId(R.id.categoryBtn4));
        onView(withId(R.id.categoryBtnOther));
    }

    @Test
    // checks if the buttons are displayed
    public void CategoryFilterCheck() {
        onView(withId(R.id.jobpostview));
        onView(withId(R.id.categoryBtn1));
        onView(withId(R.id.jobpostsearch));
    }



}


