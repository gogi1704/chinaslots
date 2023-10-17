package com.chinaslots.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chinaslots.db.di.LevelEntity

@Database(entities = [LevelEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun levelDao(): LevelDao

}