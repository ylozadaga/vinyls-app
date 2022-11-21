package com.example.vinyls.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinyls.Event
import com.example.vinyls.viewmodels.getOrAwaitValue
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [30]) // https://github.com/robolectric/robolectric/pull/6776
@RunWith(AndroidJUnit4::class)
class AlbumListViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Test
    fun getAlbums() {
        val albumListViewModel = AlbumListViewModel(ApplicationProvider.getApplicationContext())
        val value = albumListViewModel.albums.getOrAwaitValue()

            assertThat(value[0], not(nullValue()))
    }
}