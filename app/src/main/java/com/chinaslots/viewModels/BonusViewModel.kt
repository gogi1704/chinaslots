package com.chinaslots.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.chinaslots.models.BonusModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class BonusViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val moneyPrefs =
        application.getSharedPreferences(MONEY_PREFS_NAME, Context.MODE_PRIVATE)

    private val bonuses = listOf(
        2000, 100, 500, 1000, 100, 100, 100, 5000, 100
    )
    private var useMoney = 0

    var data = listOf(
        BonusModel(1, bonuses[Random().nextInt(9)]),
        BonusModel(2, bonuses[Random().nextInt(9)]),
        BonusModel(3, bonuses[Random().nextInt(9)]),
        BonusModel(4, bonuses[Random().nextInt(9)]),
        BonusModel(5, bonuses[Random().nextInt(9)]),
        BonusModel(6, bonuses[Random().nextInt(9)]),
        BonusModel(7, bonuses[Random().nextInt(9)]),
        BonusModel(8, bonuses[Random().nextInt(9)]),
        BonusModel(9, bonuses[Random().nextInt(9)]),
    )
    val bonusesLiveData = MutableLiveData(data)

    fun clickItem(id: Int) {
        data = data.map { if (it.id == id) it.copy(isWin = true) else it }
        useMoney = data.filter { it.isWin }[0].countBonus
    }

    fun collectMoney() {
        var mo = moneyPrefs.getInt(MONEY_PREFS_KEY, 1000)
        mo += useMoney
        moneyPrefs.edit()
            .putInt(MONEY_PREFS_KEY, mo)
            .apply()
    }

    fun win() {
        var mo = moneyPrefs.getInt(MONEY_PREFS_KEY, 1000)
        mo += 500
        moneyPrefs.edit()
            .putInt(MONEY_PREFS_KEY, mo)
            .apply()
    }

}

const val MONEY_PREFS_NAME = "MONEY_PREFS_NAME"
const val MONEY_PREFS_KEY = "MONEY_PREFS_KEY"