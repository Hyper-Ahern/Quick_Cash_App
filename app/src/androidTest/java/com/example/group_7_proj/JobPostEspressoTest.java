package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class JobPostEspressoTest {
    @Rule
    public ActivityScenarioRule<MainActivity> jobpostActivityRule =
            new ActivityScenarioRule<>(JobPost.class);

    /**INPUT VALIDATION**/
    @Test
    // checks if the details text box is empty and displays appropriate message
    public void emptyDetails() {
        onView(withId(R.id.EmployerName)).perform(typeText("CBC"));
        onView(withId(R.id.jobTitle)).perform(typeText("Babysitter"));
        onView(withId(R.id.salaryInput)).perform(typeText("14"));
        onView(withId(R.id.jobDetails).perform(typeText(null)));
        onView(withId(R.id.submitJobpost)).check(matches(withText("Invalid Detail info")));
        //onView(withId(R.id.detailsHint)).check(matches(withText("Please insert details")));
    }

    @Test
    // checks if the titles text box is empty and displays appropriate message
    public void emptyTitle() {
        onView(withId(R.id.EmployerName)).perform(typeText("CBC"));
        onView(withId(R.id.jobTitle)).perform(typeText(null));
        onView(withId(R.id.salaryInput)).perform(typeText("14"));
        onView(withId(R.id.jobDetails).perform(typeText("1sdfasdfasdfasdf")));
        onView(withId(R.id.submitJobpost)).check(matches(withText("Invalid Job Title info")));
       // onView(withId(R.id.titleHint)).check(matches(withText("Please insert title")));
    }

    @Test
    // checks if the salary box is empty and displays appropriate message
    public void emptySalary() {
        onView(withId(R.id.EmployerName)).perform(typeText("CBC"));
        onView(withId(R.id.jobTitle)).perform(typeText("Babysitter"));
        onView(withId(R.id.salaryInput)).perform(typeText(null));
        onView(withId(R.id.jobDetails).perform(typeText("1sdfasdfasdfasdf")));
        onView(withId(R.id.submitJobpost)).check(matches(withText("Invalid Salary info")));
        //onView(withId(R.id.salaryHint)).check(matches(withText("Please insert a Salary")));
    }

    @Test
    // checks if the employer text box is empty and displays appropriate message
    public void emptyEmployer() {
        onView(withId(R.id.EmployerName)).perform(typeText(null));
        onView(withId(R.id.jobTitle)).perform(typeText("Babysitter"));
        onView(withId(R.id.salaryInput)).perform(typeText("14"));
        onView(withId(R.id.jobDetails).perform(typeText("1sdfasdfasdfasdf")));
        onView(withId(R.id.submitJobpost)).check(matches(withText("Invalid Employer info")));
       // onView(withId(R.id.employerHint)).check(matches(withText("Please insert employer Title")));
    }


    /**NAVIGATION**/
    /*
    @Test
    // checks if app is able to successfully navigate to dashboard
    public void navToDashboard() {
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abc@xyz.com"));
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abcDEF123!@#"));
        pressBack();
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.dashboard));
    }

    @Test
    // checks if app is able to successfully navigate to dashboard
    public void navOutOfDashboard() {
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abc@xyz.com"));
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abcDEF123!@#"));
        pressBack();
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.dashboard));
        onView(withId(R.id.backBtnDB)).perform(click());
        onView(withId(R.id.login));
    }

    @Test
    // navigate to signup page without email and password
    public void navToSignUp() {
        onView(withId(R.id.signUpBtnLGP)).perform(click());
        onView(withId(R.id.signup));
    }

    @Test
    // check if dialog box pops up when user logs in with Google
    public void loginWithGoogle() {
        onView(withId(R.id.googleLoginBtn)).perform(click());
        onView(withText("Login with Google")).check(matches(isDisplayed()));
    }
    */
}
}
