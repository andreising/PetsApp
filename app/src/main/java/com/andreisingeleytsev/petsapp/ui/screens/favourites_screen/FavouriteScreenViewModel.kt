package com.andreisingeleytsev.petsapp.ui.screens.favourites_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.ingetinapp.data.repository.ItemIdRepository
import com.andreisingeleytsev.petsapp.data.entities.IdItem
import com.andreisingeleytsev.petsapp.ui.screens.pet_info_screen.PetIndoScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteScreenViewModel @Inject constructor(
    private val repository: ItemIdRepository
): ViewModel() {
    fun onEvent(event: FavouriteScreenEvent){
        when(event){
            is FavouriteScreenEvent.OnDelete -> {
                viewModelScope.launch {
                    repository.deleteItem(event.idItem)
                }
            }
        }
    }

    val items = repository.getItems()
}