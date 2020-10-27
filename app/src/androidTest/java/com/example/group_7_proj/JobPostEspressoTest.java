package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;



import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class JobPostEspressoTest {
    @Rule
    public ActivityScenarioRule<JobPostActivity> jobpostActivityRule =
            new ActivityScenarioRule<>(JobPostActivity.class);

    /**INPUT VALIDATION**/
    @Test
    // checks if the details text box is empty and displays appropriate message
    public void emptyDetails() {
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("Babysitter")).perform(closeSoftKeyboard());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Invalid Detail info")));
        //onView(withId(R.id.detailsHint)).check(matches(withText("Please insert details")));
    }

    @Test
    // checks if the titles text box is empty and displays appropriate message
    public void emptyTitle() {
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("")).perform(closeSoftKeyboard());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("1sdfasd")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Invalid Job Title info")));
       // onView(withId(R.id.titleHint)).check(matches(withText("Please insert title")));
    }

    @Test
    // checks if the titles text box is empty and displays appropriate message
    public void specialCharactersStringTitle() {
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("!!&")).perform(closeSoftKeyboard());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("1sdfasd")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Invalid Job Title info")));
        // onView(withId(R.id.titleHint)).check(matches(withText("Please insert title")));
    }

    @Test
    // checks if the salary box is empty and displays appropriate message
    public void emptySalary() {
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("Babysitter")).perform(closeSoftKeyboard());
        onView(withId(R.id.salaryInputText)).perform(typeText("")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("1sdfasd")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Invalid Salary info")));
        //onView(withId(R.id.salaryHint)).check(matches(withText("Please insert a Salary")));
    }

    @Test
    // checks if the employer text box is empty and displays appropriate message
    public void emptyEmployer() {
        onView(withId(R.id.employerNameText)).perform(typeText("")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("Babysitter")).perform(closeSoftKeyboard());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("1sdfasd")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.inputStatusTextview)).check(matches(withText("Invalid Employer info")));
       // onView(withId(R.id.employerHint)).check(matches(withText("Please insert employer Title")));
    }


    /**NAVIGATION**/

    /* JUST A TEMPLATE ~
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

