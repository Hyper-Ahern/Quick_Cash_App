package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;

public class JobPostEspressoTests {
        @Rule
        public ActivityScenarioRule<MainActivity> activityRule =
                new ActivityScenarioRule<>(MainActivity.class);
        public void emptyDetails() {
                onView(withId(R.id.employerNameText)).perform(click()).perform(typeText("Bank"));
                onView(withId(R.id.jobTitle1)).perform(click()).perform(typeText("Babysitter"));
                onView(withId(R.id.salaryInput)).perform(click()).perform(typeText("14"));
                onView(withId(R.id.submitButton)).perform(click());
                onView(withId(R.id.Detailsview)).check(matches(withText("Please insert details")));
        }

        @Test
        // checks if the titles text box is empty and displays appropriate message
        public void emptyTitle() {
                onView(withId(R.id.employerNameText)).perform(click()).perform(typeText("Bank"));
                onView(withId(R.id.Detailsview)).perform(click()).perform(typeText("Insert relevant job details"));
                onView(withId(R.id.salaryInput)).perform(click()).perform(typeText("14"));
                onView(withId(R.id.submitButton)).perform(click());
                //onView(withId(R.id.titleHint)).check(matches(withText("Please insert title")));
        }

        @Test
        // checks if the salary box is empty and displays appropriate message
        public void emptySalary() {
                onView(withId(R.id.employerNameText)).perform(click()).perform(typeText("Bank"));
                onView(withId(R.id.Detailsview)).perform(click()).perform(typeText("Insert relevant job details"));
                onView(withId(R.id.jobTitle1)).perform(click()).perform(typeText("JobTitle"));
                onView(withId(R.id.submitButton)).perform(click());
                //onView(withId(R.id.salaryHint)).check(matches(withText("Please insert a Salary")));
        }

        @Test
        // checks if the employer text box is empty and displays appropriate message
        public void emptyEmployer() {
                onView(withId(R.id.salaryInput)).perform(click()).perform(typeText("14"));
                onView(withId(R.id.Detailsview)).perform(click()).perform(typeText("Insert relevant job details"));
                onView(withId(R.id.jobTitle1)).perform(click()).perform(typeText("JobTitle"));
                onView(withId(R.id.submitButton)).perform(click());
                //onView(withId(R.id.employerHint)).check(matches(withText("Please insert employer Title")));
        }



}



