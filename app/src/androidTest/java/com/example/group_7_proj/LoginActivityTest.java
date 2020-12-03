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
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    /*Abdullah*/
    /**INPUT VALIDATION**/
    @Test
    // checks if the email text box is empty and displays appropriate message
    public void emptyEmail() {
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abCD56&*"));
        pressBack();
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.emailHint)).check(matches(withText("Email missing")));
    }

    @Test
    // checks if the password text box is empty and displays appropriate message
    public void emptyPassword() {
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abc@xyz.com"));
        pressBack();
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.passwordHint)).check(matches(withText("Password missing")));
    }

/*    @Test
    // checks for invalid format email
    public void invalidEmail() {
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abcxyz.com"));
        onView(withId(R.id.emailHint)).check(matches(withText("Email invalid")));
    }

    @Test
    // checks for invalid format password by character length
    public void invalidLengthPassword() {
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("aB4#"));
        onView(withId(R.id.passwordHint)).check(matches(withText("Password too short")));
    }


    // checks for invalid format password by character type
    public void invalidTypePassword() {
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abCD4567"));
        onView(withId(R.id.emailHint)).check(matches(withText("Password too weak")));
    }*/

    /*Abdullah*/
    /**NAVIGATION**/
    @Test
    // checks if app is able to successfully navigate to dashboard
    public void navToDashboard() {
        onView(withId(R.id.login));
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abc@xyz.com"));
        pressBack();
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abCD12#$"));
        pressBack();
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.dashboard));
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

    @Test
// checks if app is able to successfully navigate to dashboard
    public void popupexist() {
        onView(withId(R.id.login));
        onView(withId(R.id.emailText)).perform(click()).perform(typeText("abc@xyz.com"));
        pressBack();
        onView(withId(R.id.passwordText)).perform(click()).perform(typeText("abCD12#$"));
        pressBack();
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.popuplayout));
    }

}
