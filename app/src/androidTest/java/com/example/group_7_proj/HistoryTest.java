package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

public class HistoryTest {

        @Rule
        public ActivityScenarioRule<HistoryActivity> HistoryActivityRule =
                new ActivityScenarioRule<>(HistoryActivity.class);

        @Test
        // checks if the details text box is empty and displays appropriate message
        public void HistoryButtonCanBackToDash() {
            closeSoftKeyboard();
            onView(withId(R.id.history)).perform(swipeUp()).perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
            onView(withId(R.id.historyBackToDBBtn)).perform(click());
            onView(withId(R.id.dashboard));

        }
    }


