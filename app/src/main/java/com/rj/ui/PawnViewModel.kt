package com.rj.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rj.models.PawnItem
import com.rj.models.PawnItemRepository
import com.rj.models.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PawnViewModel @Inject constructor(
    private val pawnItemRepository: PawnItemRepository
    ): ViewModel(){

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
                val client = pawnItemRepository.fetchPawnItems()
                _pawnItemsListLiveData.postValue(ScreenState.Success(client.result))
            }catch (e: Exception){
                _pawnItemsListLiveData.postValue(ScreenState.Error(null, e.message.toString()))
            }
        }
    }
}