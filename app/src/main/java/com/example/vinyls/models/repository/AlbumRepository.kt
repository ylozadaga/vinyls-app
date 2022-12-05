package com.example.vinyls.models.repository

import android.app.Application
import com.example.vinyls.models.Album
import com.example.vinyls.models.networkAdapter.NetworkServiceAdapter
import org.json.JSONObject


class AlbumRepository (val application: Application){
    suspend fun refreshData(): List<Album>{
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
    suspend fun createAlbum(album: JSONObject): Album{
        return NetworkServiceAdapter.getInstance(application).createAlbum(album)
    }
}