package com.chinaslots.models

data class LevelModel(
    val id: Int,
    val skill: String = LOW,
    val isOpen: Boolean = false
) {
    companion object {
        const val LOW = "LOW"
        const val MEDIUM = "MEDIUM"
        const val HARD = "HARD"
    }
}
