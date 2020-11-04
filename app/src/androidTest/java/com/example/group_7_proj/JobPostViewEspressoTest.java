package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class JobPostViewEspressoTest {
    @Rule
    public ActivityScenarioRule<JobPostViewActivity> jobpostviewActivityRule =
            new ActivityScenarioRule<>(JobPostViewActivity.class);

    /**INPUT VALIDATION**/
    @Test
    // checks if the details text box is empty and displays appropriate message
    public void ButtonCanBackToDash() {
        onView(withId(R.id.jobPostView)).perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        onView(withId(R.id.backdashbtn)).perform(click());
        onView(withId(R.id.dashboard));
    }

/*    @Test
    // checks if the titles text box is empty and displays appropriate message
    public void LayoutDisplayFine() {
        swipeUp();
        swipeUp();
        onView(withId(R.id.backdashbtn)).perform(click());
        onView(withId(R.id.postJobBtnDB)).perform(click());
        onView(withId(R.id.jobPost));
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("")).perform(closeSoftKeyboard());
        onView(withId(R.id.salaryInputText)).perform(typeText("14")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobDetailsText)).perform(typeText("Farming")).perform(closeSoftKeyboard());
        onView(withId(R.id.submitBtnJobPost)).perform(click());
        onView(withId(R.id.BackdashBtn)).perform(click());
        onView(withId(R.id.allJobsBtnDB)).perform(click());
        onView(withId(R.id.jobpostview)).perform(swipeUp());
        onView(withId(R.id.layoutdisplay));
    }*/

    @Test
    // checks if the buttons are displayed
    public void CategoryButtonsInDisplay() {
        onView(withId(R.id.categoryBtn1));
        onView(withId(R.id.categoryBtn2));
        onView(withId(R.id.categoryBtn3));
        onView(withId(R.id.categoryBtn4));
        onView(withId(R.id.categoryBtnOther));
    }

    @Test
    // checks if the buttons are displayed
    public void CategoryFilterCheck() {
        onView(withId(R.id.jobPostView));
        onView(withId(R.id.categoryBtn1)).perform(click());
        onView(withId(R.id.jobsearchresults));
    }



}


