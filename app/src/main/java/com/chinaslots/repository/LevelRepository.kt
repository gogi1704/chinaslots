package com.chinaslots.repository

import com.chinaslots.db.LevelDao
import com.chinaslots.db.di.LevelEntity
import com.chinaslots.models.LevelModel
import com.chinaslots.models.LevelModel.Companion.HARD
import com.chinaslots.models.LevelModel.Companion.MEDIUM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class LevelRepository @Inject constructor(private val dao: LevelDao) {


    fun getAll(): Flow<List<LevelEntity>> {
        return dao.getAll()
    }


    suspend fun putAll(list: List<LevelModel>) =
        dao.insertLevels(list.map { LevelEntity(it.id, it.skill, it.isOpen) })

    suspend fun putOne(level:LevelModel) =
       dao.insertLevel(LevelEntity(level.id , level.skill , level.isOpen))


    suspend fun getById(id: Int): LevelModel {
        val model = dao.getById(id)
        return LevelModel(model.id, model.skill, model.isOpen)
    }

    suspend fun putData() = dao.insertLevels(
        listOf(
            LevelEntity(
                isOpen = true
            ),
            LevelEntity(
            ),
            LevelEntity(
            ),
            LevelEntity(
            ),
            LevelEntity(
            ),
            LevelEntity(
                skill = HARD
            ),
            LevelEntity(
                skill = HARD
            ),
            LevelEntity(
                skill = HARD
            ),
            LevelEntity(
                skill = HARD
            ),
            LevelEntity(
                skill = HARD
            ),
            LevelEntity(
                skill = HARD
            ),
        )
    )

}

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule() {


    @Provides
    @Singleton
    fun provideRepository(dao: LevelDao) = LevelRepository(dao)
}