package com.andreisingeleytsev.petsapp.di

import android.app.Application
import androidx.room.Room
import com.andreisingeleytsev.petsapp.data.MainDataBase
import com.andreisingeleytsev.ingetinapp.data.repository.ItemIdRepository
import com.andreisingeleytsev.petsapp.data.repository.implementations.ItemIdRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PetsAppModule{
    @Provides
    @Singleton
    fun provideMainDatabase(app: Application): MainDataBase {
        return Room.databaseBuilder(
            app,
            MainDataBase::class.java,
            "event_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideIdItemRepository(dataBase: MainDataBase): ItemIdRepository {
        return ItemIdRepositoryImpl(dataBase.noteItemDao)
    }
}

