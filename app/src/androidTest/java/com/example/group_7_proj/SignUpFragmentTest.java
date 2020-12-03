
package com.example.group_7_proj;

import com.example.group_7_proj.CustomDataTypes.Email;
import com.example.group_7_proj.CustomDataTypes.Password;

import android.app.Activity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class SignUpFragmentTest {
   @Rule
   public ActivityScenarioRule<SignUpActivity> signUpActivityRule
           = new ActivityScenarioRule<>(SignUpActivity.class);

    private String uName = "username1";
    private String uEmail = "email@domain.com";
    private String uPassword = "abCD23$%";

    @Test
    public void PositiveSignUp() {
        onView(withId(R.id.name)).perform(click()).perform(typeText(uName));
        onView(withId(R.id.email)).perform(click()).perform(typeText(uEmail));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(click()).perform(typeText(uPassword));
        closeSoftKeyboard();
        onView(withId(R.id.reenterPassword)).perform(click()).perform(typeText(uPassword));
        closeSoftKeyboard();
        onView(withId(R.id.signUpBtn)).perform(click());
        onView(withId(R.id.paymentInfo));
        onView(withId(R.id.cardNumber)).perform(typeText("1111222233334444"));
        onView(withId(R.id.expiryDate)).perform(typeText("10/22"));
        onView(withId(R.id.CVV)).perform(typeText("100"));
        onView(withId(R.id.cardHolderName)).perform(typeText("john doe")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.paymentSubmitButton)).perform(click());
        onView((withId(R.id.preference)));
        onView(withId(R.id.pref_babysitting)).perform(click());
        onView(withId(R.id.pref_delivery)).perform(click());
        onView(withId(R.id.pref_other)).perform(click());
        onView(withId(R.id.pref_backdashBtn)).perform(click());
        onView(withId(R.id.popuplayout));
        onView(withId(R.id.cancelmatchbutton)).perform(click());
        onView(withId(R.id.dashboard));
    }
    @Test
    public void NegativeNameSignUp() { //display name error message
        onView(withId(R.id.name)).perform(click()).perform(typeText(""));
        onView(withId(R.id.email)).perform(click()).perform(typeText(uEmail));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(click()).perform(typeText(uPassword));
        closeSoftKeyboard();
        onView(withId(R.id.reenterPassword)).perform(click()).perform(typeText(uPassword));
        closeSoftKeyboard();
        onView(withId(R.id.signUpBtn)).perform(click());
        onView(withId(R.id.nameErrorMessage)).check((matches(isDisplayed())));
    }

    @Test
    public void NegativeEmailSignUp() { //display email error message
        onView(withId(R.id.name)).perform(click()).perform(typeText(uName));
        onView(withId(R.id.email)).perform(click()).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(click()).perform(typeText(uPassword));
        closeSoftKeyboard();
        onView(withId(R.id.reenterPassword)).perform(click()).perform(typeText(uPassword));
        closeSoftKeyboard();
        onView(withId(R.id.signUpBtn)).perform(click());
        onView(withId(R.id.emailErrorMessage)).check((matches(isDisplayed())));
    }

    @Test
    public void NegativePasswordSignUp() { //display password error message
        onView(withId(R.id.name)).perform(click()).perform(typeText(uName));
        onView(withId(R.id.email)).perform(click()).perform(typeText(uEmail));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(click()).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.reenterPassword)).perform(click()).perform(typeText(uPassword));
        closeSoftKeyboard();
        onView(withId(R.id.signUpBtn)).perform(click());
        onView(withId(R.id.passwordErrorMessage)).check((matches(isDisplayed())));
    }

    @Test
    public void NegativeReenterPasswordSignUp() { //display reenter password error message - empty
        onView(withId(R.id.name)).perform(click()).perform(typeText(uName));
        onView(withId(R.id.email)).perform(click()).perform(typeText(uEmail));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(click()).perform(typeText(uPassword));
        closeSoftKeyboard();
        onView(withId(R.id.reenterPassword)).perform(click()).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.signUpBtn)).perform(click());
        onView(withId(R.id.passwordErrorMessage2)).check((matches(isDisplayed())));
    }

    @Test
    public void NegativeReenterPassword2SignUp() { //display reenter password error message - does not match original password
        onView(withId(R.id.name)).perform(click()).perform(typeText(uName));
        onView(withId(R.id.email)).perform(click()).perform(typeText(uEmail));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(click()).perform(typeText(uPassword));
        closeSoftKeyboard();
        onView(withId(R.id.reenterPassword)).perform(click()).perform(typeText("no"));
        closeSoftKeyboard();
        onView(withId(R.id.signup)).perform(click());
        pressBack();
        onView(withId(R.id.signUpBtn)).perform(click());
        onView(withId(R.id.passwordErrorMessage2)).check((matches(isDisplayed())));
    }
}
