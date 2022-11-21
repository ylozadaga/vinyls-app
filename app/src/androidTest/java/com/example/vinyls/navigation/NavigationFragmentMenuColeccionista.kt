package com.example.vinyls.navigation

import android.os.SystemClock
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinyls.R
import com.example.vinyls.view.FragmentMenuColeccionista
import org.hamcrest.Matchers.containsString
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationFragmentMenuColeccionista {
    lateinit var navController: TestNavHostController
    lateinit var homeMenuScenario : FragmentScenario<FragmentMenuColeccionista>
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
    fun navigate_to_albums_list(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonAlbums)).perform(ViewActions.click())
        Assert.assertEquals(navController.currentDestination?.id, R.id.fragmentAlbumList)
    }
    @Test
    fun check_textview_top(){
        Espresso.onView(ViewMatchers.withId(R.id.textViewCollector))
            .check(matches(withText(containsString("Coleccionista"))))

    }

    @Test
    fun check_button_albumes(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonAlbums))
            .check(matches(withText(containsString("ALBUMES"))))

    }

    @Test
    fun check_button_crear_album(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonCreateAlbum))
            .check(matches(withText(containsString("CREAR"))))

    }
}