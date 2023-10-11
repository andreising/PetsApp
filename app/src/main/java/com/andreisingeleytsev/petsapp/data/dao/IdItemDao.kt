package com.andreisingeleytsev.petsapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreisingeleytsev.petsapp.data.entities.IdItem
import kotlinx.coroutines.flow.Flow

@Dao
interface IdItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: IdItem)
    @Delete
    suspend fun deleteItem(item: IdItem)
    @Query("SELECT * FROM id_item")
    fun getItems(): Flow<List<IdItem>>
    @Query("SELECT * FROM id_item WHERE petId = :petId")
    suspend fun getPetByID(petId: Int): List<IdItem>
}