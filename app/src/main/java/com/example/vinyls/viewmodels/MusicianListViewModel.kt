package com.example.vinyls.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinyls.models.Musician
import com.example.vinyls.models.NetworkServiceAdapter

class MusicianListViewModel(application: Application) : AndroidViewModel(application) {

    private val _musicians = MutableLiveData<List<Musician>>()

    val musicians : LiveData<List<Musician>>
        get() = _musicians

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError : LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        NetworkServiceAdapter.getInstance(getApplication()).getMusicians({
            _musicians.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
            },{
                _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown(){
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory{
        override fun  <T : ViewModel> create(modelClass: Class<T>) : T {
            if(modelClass.isAssignableFrom(MusicianListViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MusicianListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}