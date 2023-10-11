package com.andreisingeleytsev.petsapp.ui.screens.pet_info_screen

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.challengeswheelapp.ui.utils.UIEvents
import com.andreisingeleytsev.ingetinapp.data.repository.ItemIdRepository
import com.andreisingeleytsev.petsapp.R
import com.andreisingeleytsev.petsapp.data.entities.IdItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetInfoScreenViewModel @Inject constructor(
    app: Application,
    savedStateHandle: SavedStateHandle,
    private val repository: ItemIdRepository
):AndroidViewModel(app) {
    fun onEvent(event: PetIndoScreenEvent){
        when(event){
            is PetIndoScreenEvent.OnSave -> {
                viewModelScope.launch {
                    if (!repository.inDatabase(id!!)) {
                        repository.insertItem(IdItem(
                            id = null, petId = id!!
                        ))
                        boolean.value = false
                    }
                }
            }
        }
    }
    private var id: Int? = null
    val boolean = mutableStateOf(false)
    val name = mutableStateOf("")
    val type = mutableStateOf("")
    val title = mutableStateOf("")
    val imageDrawable = mutableStateOf(
        R.drawable.home_pet_img
    )

    val petsArray = app.resources.getStringArray(R.array.pets)
    val drawables = listOf(
        R.drawable.dog1,
        R.drawable.dog2,
        R.drawable.dog3,
        R.drawable.cat1,
        R.drawable.cat2,
        R.drawable.cat3,
        R.drawable.parrot1,
        R.drawable.parrot2,
        R.drawable.parrot3,
        R.drawable.squirrel1,
        R.drawable.squirrel2,
        R.drawable.squirrel3
    )

    init {
        id = savedStateHandle.get<String>("id")?.toInt() ?: 1
        val pet = petsArray[id!!].split("|")
        name.value = pet[0]
        type.value = pet[1]
        title.value = pet[2]
        imageDrawable.value = drawables[id!!]
        viewModelScope.launch {
            boolean.value = !repository.inDatabase(id!!)
        }
    }



}