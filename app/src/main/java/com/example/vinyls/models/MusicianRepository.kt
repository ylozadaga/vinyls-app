package com.example.vinyls.models

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinyls.models.Musician
import com.example.vinyls.models.NetworkServiceAdapter


class MusicianRepository (val application: Application){
    suspend fun refreshData(): List<Musician>{
        return NetworkServiceAdapter.getInstance(application).getMusicians()
    }
}
