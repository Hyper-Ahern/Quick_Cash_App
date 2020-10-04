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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginFragmentTest{

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    // checks if the email text box is empty and displays appropriate message
    public void emptyEmail() {
        onView(withId(R.id.emailText)).perform(click()).perform(typeText(""));
        onView(withId(R.id.passwordText)).perform(click());
        onView(withId(R.id.emailHint)).check(matches(withText("Email address missing")));
    }

    @Test
    // checks if app is able to successfully navigate to dashboard
    public void navToDashboard() {
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abc@xyz.com"));
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abcDEF123!@#"));
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withID(R.id.dashboard));
    }

}
