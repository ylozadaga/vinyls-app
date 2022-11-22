package com.example.vinyls.models.repository

import android.app.Application
import com.example.vinyls.models.Collector
import com.example.vinyls.models.networkAdapter.NetworkServiceAdapter

class CollectorRepository (val application: Application){
    suspend fun refreshData(): List<Collector>{
        return NetworkServiceAdapter.getInstance(application).getCollectors()
    }
}