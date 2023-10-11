package com.andreisingeleytsev.petsapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "id_item")
data class IdItem(
    @PrimaryKey
    val id: Int? = null,
    val petId: Int
)
