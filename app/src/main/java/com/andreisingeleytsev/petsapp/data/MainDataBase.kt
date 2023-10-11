package com.andreisingeleytsev.petsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreisingeleytsev.petsapp.data.dao.IdItemDao
import com.andreisingeleytsev.petsapp.data.entities.IdItem

@Database(
    entities = [IdItem::class],
    version = 1
)
abstract class MainDataBase: RoomDatabase() {
    abstract val noteItemDao: IdItemDao
}