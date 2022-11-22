package com.example.vinyls.models

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinyls.models.Album
import com.example.vinyls.models.Musician
import com.example.vinyls.models.NetworkServiceAdapter


class AlbumRepository (val application: Application){
    suspend fun refreshData(): List<Album>{
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
}