package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
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

public class JobPostViewEspressoTest {
    @Rule
    public ActivityScenarioRule<JobPostViewActivity> jobpostviewActivityRule =
            new ActivityScenarioRule<>(JobPostViewActivity.class);

    /**INPUT VALIDATION**/
    @Test
    // checks if the details text box is empty and displays appropriate message
    public void ButtonCanBackToDash() {
        onView(withId(R.id.jobPostView)).perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        pressBack();
        onView(withId(R.id.backToDBBtn)).perform(click());
        onView(withId(R.id.dashboard));

    }

    /*@Test
    // checks if the titles text box is empty and displays appropriate message
    public void LayoutDisplayFine() {
        onView(withId(R.id.backdashbtn)).perform(click());
        onView(withId(R.id.postJobBtnDB)).perform(click());
        onView(withId(R.id.jobPost));
        onView(withId(R.id.employerNameText)).perform(typeText("CBC")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobTitleText)).perform(typeText("Babysitter")).perform(closeSoftKeyboard());
        onView(withId(R.id.jobType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Babysitting"))).perform(click());
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
        onView(withText("Job ID: 1")).check(matches(isDisplayed()));
        onView(withText("Job ID: 5")).check(matches(isDisplayed()));
        onView(withId(R.id.clearResultsBtn)).perform(click());

        onView(withId(R.id.categoryBtn3)).perform(click());
        onView(withId(R.id.jobsearchresults));
        onView(withText("Job ID: 3")).check(matches(isDisplayed()));
        onView(withText("Job ID: 6")).check(matches(isDisplayed()));
        onView(withId(R.id.clearResultsBtn)).perform(click());

        onView(withId(R.id.categoryBtn4)).perform(click());
        onView(withId(R.id.jobsearchresults));
        onView(withText("Job ID: 9")).check(matches(isDisplayed()));
        onView(withText("Job ID: 10")).check(matches(isDisplayed()));
        onView(withId(R.id.clearResultsBtn)).perform(click());

    }


    @Test
    // checks if more categories are displayed when swiped left
    public void SwipeLeftForMoreCategory() {
        onView(withId(R.id.jobPostView));
        onView(withId(R.id.categoryBtn1)).perform(swipeLeft());
        onView(withId(R.id.categoryBtn5));
        onView(withId(R.id.categoryBtn4)).perform(swipeLeft());
        onView(withId(R.id.categoryBtnOther));
    }

    @Test
    // checks if more categories are displayed when swiped left
    public void MoreCategoryFilter() {
        onView(withId(R.id.jobPostView));
        onView(withId(R.id.categoryBtn1)).perform(swipeLeft());
        onView(withId(R.id.categoryBtn4)).perform(swipeLeft());

        onView(withId(R.id.categoryBtn5)).perform(click());
        onView(withId(R.id.jobsearchresults));
        onView(withText("Job ID: 4")).check(matches(isDisplayed()));
        onView(withText("Job ID: 2")).check(matches(isDisplayed()));
        onView(withId(R.id.clearResultsBtn)).perform(click());

        onView(withId(R.id.jobPostView));
        onView(withId(R.id.categoryBtn1)).perform(swipeLeft());
        onView(withId(R.id.categoryBtn4)).perform(swipeLeft());

        onView(withId(R.id.categoryBtnOther)).perform(click());
        onView(withId(R.id.jobsearchresults));
        onView(withText("Job ID: 8")).check(matches(isDisplayed()));
        onView(withId(R.id.clearResultsBtn)).perform(click());
    }

    @Test
    public void SearchDisplayFine()
    {
        onView(withId(R.id.jobPostView));
        onView(withId(R.id.searchBar)).perform(clearText()).perform(typeText("asd")).perform(closeSoftKeyboard());
        onView(withId(R.id.searchBtn)).perform(click());
        onView(withId(R.id.jobsearchresults));
        onView(withId(R.id.layoutDisplaySearchInner));
    }



}


