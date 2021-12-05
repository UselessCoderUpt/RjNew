package com.rj.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rj.network.ApiClient
import com.rj.network.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repository: Repository
                        = Repository(ApiClient.apiService)): ViewModel() {

    private var _pawnItemsListLiveData = MutableLiveData<ScreenState<List<PawnItem>?>>()
    val PawnItemsListLiveData: LiveData<ScreenState<List<PawnItem>?>>
           get() = _pawnItemsListLiveData

    init {
        fetchPawnItems()
    }

    private fun fetchPawnItems() {
        _pawnItemsListLiveData.postValue(ScreenState.Loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("rjVModelThreadName", Thread.currentThread().name)
            try {
                val client = repository.fetchPawnItems()
                _pawnItemsListLiveData.postValue(ScreenState.Success(client.result))
            }catch (e: Exception){
                _pawnItemsListLiveData.postValue(ScreenState.Error(null, e.message.toString()))
            }
        }
    }
}