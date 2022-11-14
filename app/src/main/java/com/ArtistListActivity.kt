package com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.main.ArtistListFragment
import com.example.vinyls.R

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