package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

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

    // do not delete - Abdullah
    /*@Test
    // checks if app is able to successfully navigate to dashboard
    public void navToAllJobs() {
        onView(withId(R.id.dashboard));
        onView(withId(R.id.backBtnDB)).perform(click());
        onView(withId(R.id.allJobs));
    }

    @Test
    // checks if app is able to successfully navigate to dashboard
    public void navToPostAJob() {
        onView(withId(R.id.dashboard));
        onView(withId(R.id.backBtnDB)).perform(click());
        onView(withId(R.id.postAJob));
    }*/



}