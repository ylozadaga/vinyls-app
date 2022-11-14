package com.example.vinyls.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinyls.models.Album
import com.example.vinyls.network.NetworkServiceAdapter
import com.example.vinyls.repositories.AlbumRepository

class AlbumListViewModel(application: Application) :  AndroidViewModel(application) {

        private val _albums = MutableLiveData<List<Album>>()

        val albums: LiveData<List<Album>>
            get() = _albums

        private var _eventNetworkError = MutableLiveData<Boolean>(false)

        val eventNetworkError: LiveData<Boolean>
            get() = _eventNetworkError

        private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

        val isNetworkErrorShown: LiveData<Boolean>
            get() = _isNetworkErrorShown

        init {
            refreshDataFromNetwork()
        }

        private fun refreshDataFromNetwork() {
            NetworkServiceAdapter.getInstance(getApplication()).getAlbums({
                _albums.postValue(it)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            },{
                _eventNetworkError.value = true
            })
        }

        fun onNetworkErrorShown() {
            _isNetworkErrorShown.value = true
        }

        class Factory(val app: Application) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(AlbumListViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return AlbumListViewModel(app) as T
                }
                throw IllegalArgumentException("Unable to construct viewmodel")
            }
        }

}