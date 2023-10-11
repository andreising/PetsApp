package com.andreisingeleytsev.petsapp.data.repository.implementations

import com.andreisingeleytsev.ingetinapp.data.repository.ItemIdRepository
import com.andreisingeleytsev.petsapp.data.dao.IdItemDao
import com.andreisingeleytsev.petsapp.data.entities.IdItem
import kotlinx.coroutines.flow.Flow

class ItemIdRepositoryImpl(
    private val dao: IdItemDao
) : ItemIdRepository {
    override suspend fun insertItem(item: IdItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: IdItem) {
        dao.deleteItem(item)
    }

    override fun getItems(): Flow<List<IdItem>> {
        return dao.getItems()
    }

    override suspend fun inDatabase(petId: Int): Boolean {
        return dao.getPetByID(petId).isNotEmpty()
    }


}