package com.andreisingeleytsev.petsapp.ui.screens.favourites_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andreisingeleytsev.petsapp.R
import com.andreisingeleytsev.petsapp.ui.utils.Fonts

@Composable
fun FavouriteScreen(viewModel: FavouriteScreenViewModel = hiltViewModel()) {
    val items = viewModel.items.collectAsState(initial = emptyList())
    val petsArray = LocalContext.current.resources.getStringArray(R.array.pets)
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
    LazyColumn(modifier = Modifier
        .padding(36.dp)
        .fillMaxSize()){
        items(items.value){
            val list = petsArray[it.petId].split("|")
            val image = drawables[it.petId]
            var name = list[0]
            FavItem(image = image, name = name) {
                viewModel.onEvent(FavouriteScreenEvent.OnDelete(it))
            }
        }


    }
}

@Composable
fun FavItem(image: Int, name: String, onDelete: () -> Unit) {
    Row(modifier = Modifier.padding(10.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Row {
            Card(modifier = Modifier.size(40.dp), shape = CircleShape) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = name, style = TextStyle(
                    fontFamily = Fonts.font,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Image(
            painter = painterResource(id = R.drawable.fav_button),
            contentDescription = null,
            modifier = Modifier.clickable {
                onDelete.invoke()
            })
    }
}