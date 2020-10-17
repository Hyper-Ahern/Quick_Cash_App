package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.firebase.FirebaseApp;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class AddCardInfoToFirebaseEspressoTest {


    @Rule
    public ActivityScenarioRule<PaymentInfoActivity> payInfoActivityRule
            = new ActivityScenarioRule<>(PaymentInfoActivity.class);

    @Test
    public void addCardInfo_2(){
        onView(withId(R.id.cardNumber))
                .perform(typeText("9999888877776666"));
        onView(withId(R.id.expiryDate))
                .perform(typeText("10/22"));
        onView(withId(R.id.CVV))
                .perform(typeText("333"));
        onView(withId(R.id.cardHolderName))
                .perform(typeText("Hello World"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.paymentSubmitButton))
                .perform(click());
/*        onView(withId(R.id.statusBtn))
                .check(matches(withText("Card info OK")));*/
        onView(withId(R.id.dashboard));
    }

    @Test
    public void addCardInfo_1(){
        onView(withId(R.id.cardNumber))
                .perform(typeText("1111222233334444"));
        onView(withId(R.id.expiryDate))
                .perform(typeText("10/22"));
        onView(withId(R.id.CVV))
                .perform(typeText("100"));
        onView(withId(R.id.cardHolderName))
                .perform(typeText("john doe"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.paymentSubmitButton))
                .perform(click());
        /*onView(withId(R.id.statusBtn))
                .check(matches(withText("Card info OK")));*/
        onView(withId(R.id.dashboard));
    }







}
