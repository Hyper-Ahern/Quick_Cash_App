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



public class PaymentInfoUIEspressoTest {



    @Rule
    public ActivityScenarioRule<PaymentInfoUI> payInfoActivityRule
            = new ActivityScenarioRule<>(PaymentInfoUI.class);

    @Test
    public void correctPaymentInfo(){
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
        onView(withId(R.id.statusBtn))
                .check(matches(withText("Card info OK")));
    }

    @Test
    public void shortCardNumber(){
        onView(withId(R.id.cardNumber))
                .perform(typeText("11112222"));
        onView(withId(R.id.expiryDate))
                .perform(typeText("10/22"));
        onView(withId(R.id.CVV))
                .perform(typeText("100"));
        onView(withId(R.id.cardHolderName))
                .perform(typeText("John Doe"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.paymentSubmitButton))
                .perform(click());
        onView(withId(R.id.statusBtn))
                .check(matches(withText("Invalid Card info")));
    }

    @Test
    public void longCardNumber(){
        onView(withId(R.id.cardNumber))
                .perform(typeText("11112222333344445555"));
        onView(withId(R.id.expiryDate))
                .perform(typeText("10/22"));
        onView(withId(R.id.CVV))
                .perform(typeText("100"));
        onView(withId(R.id.cardHolderName))
                .perform(typeText("John Doe"))
                .perform(closeSoftKeyboard());;
        onView(withId(R.id.paymentSubmitButton))
                .perform(click());
        onView(withId(R.id.statusBtn))
                .check(matches(withText("Invalid Card info")));
    }

    @Test
    public void shortCVV(){
        onView(withId(R.id.cardNumber))
                .perform(typeText("1111222233334444"));
        onView(withId(R.id.expiryDate))
                .perform(typeText("10/22"));
        onView(withId(R.id.CVV))
                .perform(typeText("11"));
        onView(withId(R.id.cardHolderName))
                .perform(typeText("John Doe"))
                .perform(closeSoftKeyboard());;
        onView(withId(R.id.paymentSubmitButton))
                .perform(click());
        onView(withId(R.id.statusBtn))
                .check(matches(withText("Invalid Card info")));
    }

    @Test
    public void longCVV(){
        onView(withId(R.id.cardNumber))
                .perform(typeText("1111222233334444"));
        onView(withId(R.id.expiryDate))
                .perform(typeText("10/22"));
        onView(withId(R.id.CVV))
                .perform(typeText("1111"));
        onView(withId(R.id.cardHolderName))
                .perform(typeText("John Doe"))
                .perform(closeSoftKeyboard());;
        onView(withId(R.id.paymentSubmitButton))
                .perform(click());
        onView(withId(R.id.statusBtn))
                .check(matches(withText("Invalid Card info")));
    }
}
