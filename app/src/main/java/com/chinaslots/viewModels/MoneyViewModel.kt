package com.chinaslots.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoneyViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    private val myMoneyPrefs =
        application.getSharedPreferences(MONEY_PREFS_NAME, Context.MODE_PRIVATE)
    private var moneyCount = myMoneyPrefs.getInt(MONEY_PREFS_KEY, 1000)
        set(value) {
            field = value
            moneyLiveData.value = value
        }

    val moneyLiveData = MutableLiveData(moneyCount)

    val errorLiveData = MutableLiveData(false)


    fun buyItem(count: Int) {
        if (moneyCount >= count) {
            moneyCount -= count
            myMoneyPrefs.edit()
                .putInt(MONEY_PREFS_KEY, moneyCount)
                .apply()
        }else errorLiveData.value = true

    }

}


