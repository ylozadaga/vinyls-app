package com.example.vinyls.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinyls.models.Album
import android.util.Log
import com.example.vinyls.models.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumListViewModel(application: Application) :  AndroidViewModel(application) {
        private val albumRepository = AlbumRepository(application)

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
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = albumRepository.refreshData()
                    _albums.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){ //se procesa la excepcion
            Log.d("Error", e.toString())
            _eventNetworkError.postValue(true)
        }
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