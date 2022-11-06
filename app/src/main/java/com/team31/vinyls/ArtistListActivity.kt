package com.team31.vinyls

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.team31.vinyls.ui.main.ArtistListFragment

class ArtistListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_list)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ArtistListFragment.newInstance())
                .commitNow()
        }
    }
}