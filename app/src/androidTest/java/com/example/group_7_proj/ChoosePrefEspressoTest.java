package com.example.group_7_proj;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

public class ChoosePrefEspressoTest {

    //does selected/pressed match what's in selected list

    @Test
    public void chooseSingleOption(){//selects first option
        onView(withId(R.id.multi_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.etPickedJobTypes)).check(matches(withText("choosen type")));
        onView(withId(R.id.btnPickJobPref)).perform(click());
       // onData(allOf(is(instanceOf(String.class)), is(selectionText))).perform(click());
    }

    @Test
    public void chooseNoOption(){
        onView(withId(R.id.multi_spinner)).perform(click());
        onView(withId(R.id.btnPickJobPref)).perform(click());
    }

    @Test
    public void chooseMultipleOptions(){
        onView(withId(R.id.multi_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.etPickedJobTypes)).check(matches(withText("choosen types")));
        onView(withId(R.id.btnPickJobPref)).perform(click());
    }
}
