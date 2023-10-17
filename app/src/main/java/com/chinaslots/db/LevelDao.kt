package com.chinaslots.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chinaslots.db.di.LevelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLevels(list: List<LevelEntity>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLevel(list: LevelEntity)

    @Query("SELECT * FROM LevelEntity")
     fun getAll(): Flow<List<LevelEntity>>

    @Query("SELECT * FROM LevelEntity WHERE id = :id ")
    suspend fun getById(id: Int): LevelEntity
}