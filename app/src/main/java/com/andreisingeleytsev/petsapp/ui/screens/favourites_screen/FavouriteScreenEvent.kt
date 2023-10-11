package com.andreisingeleytsev.petsapp.ui.screens.favourites_screen

import com.andreisingeleytsev.petsapp.data.entities.IdItem

sealed class FavouriteScreenEvent{
    data class OnDelete(val idItem: IdItem): FavouriteScreenEvent()
}
