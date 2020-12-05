package com.example.todoprojectv2;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.todoprojectv2.overview.addtodo.AddToDo;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoUIOverviewTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAddNewToDo() throws InterruptedException {
        Thread.sleep(10000);

        onView(withId(R.id.fab_add_todo)).perform(click());
        onView(withId(R.id.editText_title)).perform(replaceText("Espresso UI Test"), closeSoftKeyboard());
        onView(withId(R.id.editText_description)).perform(replaceText("This is a test for espresso UI Testing"), closeSoftKeyboard());
        onView(withId(R.id.button_add)).perform(click());

        onView(withId(R.id.ToDoTitle)).check(matches(withText("Espresso UI Test")));
    }

}