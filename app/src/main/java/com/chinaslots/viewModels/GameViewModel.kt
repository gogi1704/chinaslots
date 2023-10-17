package com.chinaslots.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.chinaslots.models.GameModel
import com.chinaslots.models.LevelModel.Companion.LOW
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private var checkedNow = CHECKED_STROKE
    var levelDifficult = LOW

    var attemp = 15
        set(value) {
            field = value
            attempLiveData.value = value
        }
    val attempLiveData = MutableLiveData(attemp)


    var isWinOrLose = ""
        set(value) {
            field = value
            isWinOrLoseLiveData.value = value
        }
    val isWinOrLoseLiveData = MutableLiveData(isWinOrLose)

    private var strokeCheckedId = -1
    private var stolbCheckedId = -1

    private var gameList = mutableListOf(
        GameModel(0, 0),
        GameModel(1, 1),
        GameModel(2, 2),
        GameModel(3, 3),
        GameModel(4, 4),
        GameModel(5, 2),
        GameModel(6, 1),
        GameModel(7, 3),
        GameModel(8, 4),
        GameModel(9, 0),
        GameModel(10, 4),
        GameModel(11, 3),
        GameModel(12, 2),
        GameModel(13, 1),
        GameModel(14, 3),
        GameModel(15, 4),
        GameModel(16, 0),
        GameModel(17, 1),
        GameModel(18, 2),
        GameModel(19, 0),
        GameModel(20, 0),
        GameModel(21, 1),
        GameModel(22, 2),
        GameModel(23, 3),
        GameModel(24, 4),
    )
        set(value) {
            field = value
            listLiveData.value = value
        }

    val listLiveData = MutableLiveData(gameList)


    fun checkStroke(pos: Int) {
//        strokeCheckedId = pos
        gameList = gameList.map { it.copy(isChecked = false) }.toMutableList()
        checkedNow = CHECKED_STOLB
        when (pos) {
            in 0..4 -> {
                strokeCheckedId = 0
                gameList =
                    gameList.map { if (it.id in 0..4) it.copy(isChecked = !it.isChecked) else it }
                        .toMutableList()
            }

            in 5..9 -> {
                strokeCheckedId = 1
                gameList =
                    gameList.map { if (it.id in 5..9) it.copy(isChecked = !it.isChecked) else it }
                        .toMutableList()
            }

            in 10..14 -> {
                strokeCheckedId = 2
                gameList =
                    gameList.map { if (it.id in 10..14) it.copy(isChecked = !it.isChecked) else it }
                        .toMutableList()
            }

            in 15..19 -> {
                strokeCheckedId = 3
                gameList =
                    gameList.map { if (it.id in 15..19) it.copy(isChecked = !it.isChecked) else it }
                        .toMutableList()
            }

            in 20..24 -> {
                strokeCheckedId = 4
                gameList =
                    gameList.map { if (it.id in 20..24) it.copy(isChecked = !it.isChecked) else it }
                        .toMutableList()
            }

        }
    }

    fun checkStolb(pos: Int) {
//        stolbCheckedId = pos
        var i = if (pos < 5) pos else pos % 5
        stolbCheckedId = if (pos < 5) pos else pos % 5
        val newList = gameList
        while (i < 25) {
            newList[i] = newList[i].copy(isChecked = true)
            i += 5
        }
        gameList = newList
        checkedNow = CHECKED_STROKE
    }

    fun clickItem(id: Int) {
        if (checkedNow == CHECKED_STROKE) {
            checkStroke(id)
        } else checkStolb(id)
    }

    fun swap() {
        if (strokeCheckedId != -1 && stolbCheckedId != -1) {
            if (attemp > 0) {
                attemp -= 1
                val newList = swapRowAndColumn(gameList, strokeCheckedId, stolbCheckedId)
                for (i in newList.withIndex()) {
                    newList[i.index] = i.value.copy(id = i.index)
                }
                gameList = gameList.map { it.copy(isChecked = false) }.toMutableList()
            } else {
                isWinOrLose = "LOSE"
            }

            val grid = List(5) { row ->
                List(5) { column -> gameList[row * 5 + column].image }
            }

            if (checkTable(grid)) {
                isWinOrLose = "WIN"
            }

        }
        strokeCheckedId = -1
        stolbCheckedId = -1
    }


    fun swapRowAndColumn(
        grid: MutableList<GameModel>,
        row: Int,
        column: Int
    ): MutableList<GameModel> {
        if (row < 0 || row >= 5 || column < 0 || column >= 5) {
            throw IllegalArgumentException("Invalid row or column index")
        }

        for (i in 0 until 5) {
            val temp = grid[row * 5 + i]
            grid[row * 5 + i] = grid[i * 5 + column]
            grid[i * 5 + column] = temp
        }
        return grid
    }


    fun checkTable(grid: List<List<Int>>): Boolean {
        // Проверка строк на повторяющиеся элементы
        for (row in grid) {
            if (hasConsecutiveDuplicates(row)) {
                return true
            }
        }

        // Проверка столбцов на повторяющиеся элементы
        for (columnIndex in grid[0].indices) {
            val column = mutableListOf<Int>()
            for (rowIndex in grid.indices) {
                column.add(grid[rowIndex][columnIndex])
            }
            if (hasConsecutiveDuplicates(column)) {
                return true
            }
        }

        return false
    }

    fun hasConsecutiveDuplicates(list: List<Int>): Boolean {
        if (levelDifficult == LOW) {
            var count = 1
            for (i in 1 until list.size) {
                if (list[i] == list[i - 1]) {
                    count++
                    if (count == 4) {
                        return true
                    }
                } else {
                    count = 1
                }
            }
            return false
        } else {
            var count = 1
            for (i in 1 until list.size) {
                if (list[i] == list[i - 1]) {
                    count++
                    if (count == 5) {
                        return true
                    }
                } else {
                    count = 1
                }
            }
            return false
        }

    }

    fun useDif(diff: String) {
        levelDifficult = diff
    }

//    fun hasConsecutiveDuplicates(list: List<Int>): Boolean {
//        for (i in 0 until list.size - 2) {
//            if (list[i] == list[i + 1] && list[i] == list[i + 2]) {
//                return true // Если найдены повторения подряд, возврат true
//            }
//        }
//        return false // Если повторений нет, возврат false
//    }


}

const val CHECKED_STROKE = "LINE"
const val CHECKED_STOLB = "STOLB"