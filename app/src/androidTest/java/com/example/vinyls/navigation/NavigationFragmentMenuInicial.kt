package com.example.vinyls.navigation

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinyls.R
import com.example.vinyls.view.FragmentMenuInicial
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationFragmentMenuInicial {
    lateinit var navController: TestNavHostController
    lateinit var homeMenuScenario : FragmentScenario<FragmentMenuInicial>
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
    fun navigate_to_guest_menu(){
        /*val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        val homeMenuScenario = launchFragmentInContainer<FragmentMenuInicial>(
            themeResId = R.style.Theme_Vinyls
        )

        homeMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(fragment.requireView(), navController)

        }*/

        onView(withId(R.id.btnVisitante)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.fragmentMenuVisitante)
    }

    @Test
    fun navigate_to_collector_menu(){
        onView(withId(R.id.btnColeccionista)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.fragmentMenuColeccionista)
    }
}