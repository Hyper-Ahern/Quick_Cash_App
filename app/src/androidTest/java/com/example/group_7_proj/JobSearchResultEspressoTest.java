package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class JobSearchResultEspressoTest {
    @Rule
    public ActivityScenarioRule<JobCatSearchResultActivity> jobSearchResultActivityRule =
            new ActivityScenarioRule<>(JobCatSearchResultActivity.class);

    /**INPUT VALIDATION**/
    @Test
    // checks if the details text box is empty and displays appropriate message
    public void ButtonBackToJobPostView() {
        onView(withId(R.id.jobsearchresults)).perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        onView(withId(R.id.clearResults)).perform(click());
        onView(withId(R.id.jobPostView));
    }



}


