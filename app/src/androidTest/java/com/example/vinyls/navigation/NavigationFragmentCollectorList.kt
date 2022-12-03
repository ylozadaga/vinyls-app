package com.example.vinyls.navigation

import android.os.SystemClock
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.vinyls.R
import com.example.vinyls.view.FragmentCollectorList
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NavigationFragmentCollectorList {
    lateinit var navController: TestNavHostController
    lateinit var homeMenuScenario : FragmentScenario<FragmentCollectorList>

    @Before
    fun setup(){
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        homeMenuScenario = launchFragmentInContainer(
            themeResId = R.style.Theme_Vinyls
        )
        homeMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(fragment.requireView(), navController)

        }
    }
    @Test
    fun navigate_to_collector_detail(){
        SystemClock.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.collectorRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, ViewActions.click()
            )
        )
        Assert.assertEquals(navController.currentDestination?.id, R.id.fragmentCollectorDetail)
    }
    @Test
    fun check_textview_top(){
        Espresso.onView(ViewMatchers.withId(R.id.textViewCollector))
            .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("Coleccionistas"))))

    }
}