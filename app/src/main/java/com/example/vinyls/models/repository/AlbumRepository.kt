package com.example.vinyls.models.repository

import android.app.Application
import com.example.vinyls.models.Album
import com.example.vinyls.models.networkAdapter.NetworkServiceAdapter


class AlbumRepository (val application: Application){
    suspend fun refreshData(): List<Album>{
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
}