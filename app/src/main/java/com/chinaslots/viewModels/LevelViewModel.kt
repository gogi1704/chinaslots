package com.chinaslots.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.chinaslots.models.LevelModel
import com.chinaslots.repository.LevelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelViewModel @Inject constructor(
    application: Application,
    private val repository: LevelRepository
) :
    AndroidViewModel(application) {
    private val isFirstPref = application.getSharedPreferences(IS_FIRST_NAME, Context.MODE_PRIVATE)

    private val _levelData: MutableStateFlow<List<LevelModel>> = MutableStateFlow(emptyList())
    val levelData: LiveData<List<LevelModel>> get() = _levelData.asLiveData(Dispatchers.Default)

    private var levels = listOf<LevelModel>()
        set(value) {
            field = value
            levelsLiveData.value = value
        }

    val levelsLiveData = MutableLiveData(levels)

    fun openLevel(id: Int) {
//        val newLevels = levelData.value?.map {
//            if (it.id == id) it.copy(isOpen = true) else it
//
//        }

        viewModelScope.launch {
            val updatedLevel = repository.getById(id).copy(isOpen = true)
            repository.putOne(updatedLevel)
        }

    }


    private fun updateLevels() {
        viewModelScope.launch {
            repository.getAll().collect() {
                _levelData.value = it.map { LevelModel(it.id, it.skill, it.isOpen) }
            }
        }

    }

    init {
        if (isFirstPref.getBoolean(IS_FIRST_KEY, true)) {
            viewModelScope.launch {
                repository.putData()
                delay(100)
                isFirstPref.edit()
                    .putBoolean(IS_FIRST_KEY, false)
                    .apply()
            }
        }

        updateLevels()
    }

}

const val IS_FIRST_NAME = "IS_FIRST_NAME"
const val IS_FIRST_KEY = "IS_FIRST_KEY"