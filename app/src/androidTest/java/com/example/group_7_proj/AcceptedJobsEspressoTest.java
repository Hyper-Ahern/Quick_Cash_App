package com.example.group_7_proj;

import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AcceptedJobsEspressoTest {
    /*
    @Rule
    public ActivityScenarioRule<AcceptedJobsActivity> checkThePage
            = new ActivityScenarioRule<>(AcceptedJobsActivity.class);
    //clicked category displays check?
    //if clicked after clicked, is unchecked
    @Test
    public void checkTheActivityExistence(){
        onView(withId(R.id.acceptedjobs));

    }

    @Test
    public void checkTheButtonBackToMain(){
        onView(withId(R.id.acceptedJobsBackToDBBtn)).perform(click());

    }

     */

    //need data of user ID to do the following test
    @Rule
    public ActivityScenarioRule<LoginActivity> checkTheFunctionalityOfButton
            = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void loginAndCheckAcceptedJobs(){
        onView(withId(R.id.login));
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abc@xyz.com"));
        pressBack();
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abCD12#$"));
        pressBack();
        onView(withId(R.id.loginBtn)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.popuplayout));
        onView(withId(R.id.cancelmatchbutton)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.acceptedJobsBtn)).perform(click());
    }

    @Test
    public void clickMarkAsCompleted(){
        onView(withId(R.id.login));
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abc@xyz.com"));
        pressBack();
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abCD12#$"));
        pressBack();
        onView(withId(R.id.loginBtn)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.popuplayout));
        onView(withId(R.id.cancelmatchbutton)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.acceptedJobsBtn)).perform(click());
        onView(withId(R.id.acceptedjobs));
        //SystemClock.sleep(1000);

    }




}
