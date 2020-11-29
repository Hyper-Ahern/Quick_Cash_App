package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class PreferenceActivityTest {
    @Rule
    public ActivityScenarioRule<PrefActivity> PreferenceActivityRule =
            new ActivityScenarioRule<>(PrefActivity.class);

    @Test
    public void ButtonCanBackToDash() {
        onView((withId(R.id.preference)));
        onView(withId(R.id.pref_backdashBtn)).perform(click());
        onView(withId(R.id.dashboard));

    }
}
