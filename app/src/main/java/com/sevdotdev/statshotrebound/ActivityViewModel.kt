package com.sevdotdev.statshotrebound

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdotdev.statshotrebound.domain.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val dataManager: DataManager): ViewModel(){

    fun update() = viewModelScope.launch {
        while (isActive) {
            withContext(Dispatchers.IO){
                dataManager.updateDataFromServer()
            }
            delay(300000)
        }
    }
}