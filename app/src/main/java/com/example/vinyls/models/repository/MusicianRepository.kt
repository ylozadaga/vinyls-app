package com.example.vinyls.models.repository

import android.app.Application
import com.example.vinyls.models.Musician
import com.example.vinyls.models.networkAdapter.NetworkServiceAdapter


class MusicianRepository (val application: Application){
    suspend fun refreshData(): List<Musician>{
        return NetworkServiceAdapter.getInstance(application).getMusicians()
    }
}
