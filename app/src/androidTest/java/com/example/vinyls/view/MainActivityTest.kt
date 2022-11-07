package com.example.vinyls.view

import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinyls.R
import org.hamcrest.Matchers.containsString
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {}

    @Test
    fun homeIconNavigateToMainPage() {
        onView(withId(R.id.tvVisitante)).perform(click())

        SystemClock.sleep(1000);
        onView(withId(R.id.imHouse)).perform(click())

        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun validateVisitorsText() {
        onView(withId(R.id.tvVisitante)).check(matches(withText(containsString("Usuario visitante"))))
    }

    @Test
    fun validateCollectorText() {
        onView(withId(R.id.tvColeccionista)).check(matches(withText(containsString("Coleccionista"))))
    }

    @Test
    fun navigateToAlbumList() {
        onView(withId(R.id.tvColeccionista)).perform(click())
        onView(withId(R.id.fragmentAlbumList)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToAlbumDetails() {
        onView(withId(R.id.tvColeccionista)).perform(click())

        SystemClock.sleep(2000);

        onView(withId(R.id.albumsRv)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

}