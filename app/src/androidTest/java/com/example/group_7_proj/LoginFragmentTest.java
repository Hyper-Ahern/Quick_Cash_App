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
public class LoginFragmentTest{

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**INPUT VALIDATION**/
    @Test
    // checks if the email text box is empty and displays appropriate message
    public void emptyEmail() {
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abCD56&*"));
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.emailHint)).check(matches(withText("Email address missing")));
    }

    @Test
    // checks if the password text box is empty and displays appropriate message
    public void emptyPassword() {
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abc@xyz.com"));
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.passwordHint)).check(matches(withText("Password missing")));
    }

    @Test
    // checks for invalid format email
    public void invalidEmail() {
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abcxyz.com"));
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abCD56&*"));
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.emailHint)).check(matches(withText("Email invalid")));
    }

    @Test
    // checks for invalid format password by character length
    public void invalidLengthPassword() {
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abc@xyz.com"));
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("aB4#"));
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.emailHint)).check(matches(withText("Password too short")));
    }

    @Test
    // checks for invalid format password by character type
    public void invalidTypePassword() {
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abc@xyz.com"));
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abCD4567"));
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.emailHint)).check(matches(withText("Password too weak")));
    }

    @Test
    // checks if email and password matches
    public void emailNPasswordMatches(){
    }

    /**NAVIGATION**/
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

}
