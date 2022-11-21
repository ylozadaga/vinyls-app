package com.example.vinyls.navigation

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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
        onView(withId(R.id.btnVisitante)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.fragmentMenuVisitante)
    }

    @Test
    fun navigate_to_collector_menu(){
        onView(withId(R.id.btnColeccionista)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.fragmentMenuColeccionista)
    }
    @Test
    fun check_text_view_bienvenida(){
        onView(withId(R.id.textViewBienvenida)).check(matches(withText("Bienvenid@ a")))
    }
    @Test
    fun check_text_view_vinilo(){
        onView(withId(R.id.textViewNameApp)).check(matches(withText("ViniloApp")))
    }
    @Test
    fun check_text_view_how_in(){
        onView(withId(R.id.textViewHowIn)).check(matches(withText("¿Cómo deseas ingresar?")))
    }
    @Test
    fun check_button_collector(){
        onView(withId(R.id.btnColeccionista)).check(matches(withText("COLECCIONISTA")))
    }
    @Test
    fun check_button_guest(){
        onView(withId(R.id.btnVisitante)).check(matches(withText("VISITANTE")))
    }

}