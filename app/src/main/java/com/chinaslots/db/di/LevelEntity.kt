package com.chinaslots.db.di

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chinaslots.models.LevelModel
@Entity
data class LevelEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val skill: String = LevelModel.LOW,
    val isOpen: Boolean = false
)
