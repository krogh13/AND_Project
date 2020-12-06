package com.example.todoprojectv2;

import android.view.View;
import android.widget.NumberPicker;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.todoprojectv2.overview.OverviewAdapter;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoUIOverviewTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    /*
    * The thread is set to sleep when the sign in by firebase authentication, otherwise the testing cannot be done due to interference.
    * If the firebase authentication is disabled there is no need for setting the thread to sleep.
    * */

    @Test
    public void addNewToDoTestingTitleMatch() throws InterruptedException {
        threadSleeper(10000);

        onView(withId(R.id.fab_add_todo)).perform(click());
        onView(withId(R.id.editText_title)).perform(replaceText("Espresso UI Test"), closeSoftKeyboard());
        onView(withId(R.id.editText_description)).perform(replaceText("This is a test for espresso UI Testing"), closeSoftKeyboard());
        ViewInteraction numPicker = onView(withClassName(Matchers.equalTo(NumberPicker.class.getName())));
        numPicker.perform(setNumber(3));
        onView(withId(R.id.button_add)).perform(scrollTo(), click());

        onView(withId(R.id.OverviewTitle)).check(matches(withText("Espresso UI Test")));

    }

    @Test
    public void deleteToDoTesting() throws InterruptedException {
        threadSleeper(10000);

        onView(withId(R.id.overviewRecyclerView)).perform(swipeLeft());
    }

    @Test
    public void addNewToDoTestingPriorityMatch() throws InterruptedException {
        threadSleeper(10000);

        onView(withId(R.id.fab_add_todo)).perform(click());
        onView(withId(R.id.editText_title)).perform(replaceText("Espresso UI Test"), closeSoftKeyboard());
        onView(withId(R.id.editText_description)).perform(replaceText("This is a test for espresso UI Testing"), closeSoftKeyboard());
        ViewInteraction numPicker = onView(withClassName(Matchers.equalTo(NumberPicker.class.getName())));
        numPicker.perform(setNumber(4));
        onView(withId(R.id.button_add)).perform(scrollTo(), click());

        onView(withId(R.id.OverviewPriority)).check(matches(withText("4")));

    }

    @Test
    public void backButtonOnAddNewToDoTesting() throws InterruptedException {
        threadSleeper(10000);

        onView(withId(R.id.fab_add_todo)).perform(click());
        onView(withId(R.id.button_cancel)).perform(scrollTo(), click());
    }

    // Used to set the thread to sleep for a specified time.
    private void threadSleeper(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    // Used for setting the priority

    /*
    * Taken from stack overflow: https://stackoverflow.com/questions/24074495/automating-number-picker-in-android-using-espresso
    */

    private static ViewAction setNumber(final int priority) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                NumberPicker numberPicker = (NumberPicker) view;
                numberPicker.setValue(priority);

            }

            @Override
            public String getDescription() {
                return "Sets the passed number into the NumberPicker";
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(NumberPicker.class);
            }
        };
    }

}