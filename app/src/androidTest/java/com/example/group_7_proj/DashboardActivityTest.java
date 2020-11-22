package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DashboardActivityTest {

    @Rule
    public ActivityScenarioRule<DashboardActivity> activityRule =
            new ActivityScenarioRule<>(DashboardActivity.class);

    /*Abdullah*/
    /**NAVIGATION**/

    @Test
    // checks if app is able to successfully navigate to dashboard
    public void navToLogin() {
        onView(withId(R.id.dashboard));
        onView(withId(R.id.backBtnDB)).perform(click());
        onView(withId(R.id.login));
    }


    /*@Test
    // checks if app is able to successfully navigate to dashboard
    public void navToAllJobs() {
        onView(withId(R.id.dashboard));
        onView(withId(R.id.backBtnDB)).perform(click());
        onView(withId(R.id.allJobs));
    }*/

    @Test
    // checks if app is able to successfully navigate to dashboard
    public void navToPostAJob() {
        onView(withId(R.id.dashboard));
        onView(withId(R.id.postJobBtnDB)).perform(click());
        onView(withId(R.id.jobPost));
    }

    @Test
    // checks if app is able to successfully navigate to dashboard
    public void navToPayEmployee() {
        onView(withId(R.id.dashboard));
        onView(withId(R.id.payEmpBtnDB)).perform(click());
        onView(withId(R.id.paymentInfo));
    }

    @Test
    // check if app is able to navigate to Accepted Jobs it3 RB&AZ US AT9.1
    public void navToAcceptedJobs(){
        onView(withId(R.id.dashboard));
        onView(withId(R.id.acceptedJobs)).perform(click());
        onView(withId(R.id.acceptedJobView));
    }
    @Test
    // check if app is able to use the 'Make a payment' button in 'History' page
    public void clickMakeAPayment(){
        onView(withId(R.id.dashboard));
        onView(withId(R.id.historyBtn)).perform(click());
        onView(withId(R.id.mkPaymentBtn));

    }





}
