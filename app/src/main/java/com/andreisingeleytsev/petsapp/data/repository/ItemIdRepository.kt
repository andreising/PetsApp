package com.andreisingeleytsev.ingetinapp.data.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreisingeleytsev.petsapp.data.entities.IdItem
import kotlinx.coroutines.flow.Flow

interface ItemIdRepository {
    suspend fun insertItem(item: IdItem)
    suspend fun deleteItem(item: IdItem)
    fun getItems(): Flow<List<IdItem>>
    suspend fun inDatabase(petId: Int): Boolean
}