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
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinyls.R
import com.example.vinyls.view.FragmentMenuVisitante
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationFragmentMenuVisitante {
    lateinit var navController: TestNavHostController
    lateinit var homeMenuScenario : FragmentScenario<FragmentMenuVisitante>
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
    fun navigate_to_artists_list(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonArtistGuest)).perform(ViewActions.click())
        Assert.assertEquals(navController.currentDestination?.id, R.id.fragmentMusicianList)

    }

    @Test
    fun navigate_to_collectors_list(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonCollectorsGuest)).perform(ViewActions.click())
        Assert.assertEquals(navController.currentDestination?.id, R.id.fragmentCollectorList)
    }

    @Test
    fun check_textview_top(){
        Espresso.onView(ViewMatchers.withId(R.id.textViewGuest))
            .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("Visitante"))))

    }
    @Test
    fun check_button_albumes(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonAlbumsGuest))
            .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("ALBUMES"))))

    }
    @Test
    fun check_button_artistas(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonArtistGuest))
            .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("ARTISTAS"))))

    }

    @Test
    fun check_button_coleccionistas(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonCollectorsGuest))
            .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("COLECCIONISTAS"))))

    }
}