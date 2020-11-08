package com.example.group_7_proj;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

public class ChoosePrefEspressoTest {
    @Rule
    public ActivityScenarioRule<ChooseJobPrefActivity> chooseJobPrefRule
            = new ActivityScenarioRule<>(ChooseJobPrefActivity.class);
    //clicked category displays check?
    //if clicked after clicked, is unchecked

    @Test
    public void positiveDeliveryChecked(){//select delivery, displays checked?
        onView(withId(R.id.ckbxDelivery)).perform(click());
        onView(withId(R.id.ckbxDelivery)).check(matches(isChecked()));
    }
    @Test
    public void positiveBabysitChecked(){//select delivery, displays checked?
        onView(withId(R.id.ckbxBabysit)).perform(click());
        onView(withId(R.id.ckbxBabysit)).check(matches(isChecked()));
    }
    @Test
    public void positiveCompChecked(){//select delivery, displays checked?
        onView(withId(R.id.ckbxComp)).perform(click());
        onView(withId(R.id.ckbxComp)).check(matches(isChecked()));
    }
    @Test
    public void positiveCleanChecked(){//select delivery, displays checked?
        onView(withId(R.id.ckbxClean)).perform(click());
        onView(withId(R.id.ckbxClean)).check(matches(isChecked()));
    }
    @Test
    public void positiveOtherChecked(){//select delivery, displays checked?
        onView(withId(R.id.ckbxOther)).perform(click());
        onView(withId(R.id.ckbxOther)).check(matches(isChecked()));
    }
    @Test
    public void positiveUnChecked(){//select delivery, displays checked?
        onView(withId(R.id.ckbxDelivery)).perform(click());
        onView(withId(R.id.ckbxDelivery)).perform(click());
        onView(withId(R.id.ckbxDelivery)).check(matches(isNotChecked()));
    }

}
