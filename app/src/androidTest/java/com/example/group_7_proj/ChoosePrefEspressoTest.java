package com.example.group_7_proj;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ChoosePrefEspressoTest {

    @Test
    public void chooseSingleOption(){
        onView(withId(R.id.multi_spinner)).perform(click()).perform();
    }

    @Test
    public void chooseNoOption(){}

    @Test
    public void chooseMultipleOptions(){}
}
