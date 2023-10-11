package com.andreisingeleytsev.petsapp.ui.screens.home_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.andreisingeleytsev.petsapp.R
import com.andreisingeleytsev.petsapp.ui.theme.Secondary
import com.andreisingeleytsev.petsapp.ui.theme.Secondary2
import com.andreisingeleytsev.petsapp.ui.utils.Fonts
import com.andreisingeleytsev.petsapp.ui.utils.Routes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navHostController: NavHostController){
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
    val isDogSelected = remember {
        mutableStateOf(false)
    }
    val isCatSelected = remember {
        mutableStateOf(false)
    }
    val isParrotSelected = remember {
        mutableStateOf(false)
    }
    val isSquirrelSelected = remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier
        .padding(32.dp)
        .fillMaxSize()
        .background(Color.White)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CategoryPetsUI(isSelected = isDogSelected, imgId = R.drawable.dog_img)
            CategoryPetsUI(isSelected = isCatSelected, imgId = R.drawable.cat_img)
            CategoryPetsUI(isSelected = isParrotSelected, imgId = R.drawable.parrot_img)
            CategoryPetsUI(isSelected = isSquirrelSelected, imgId = R.drawable.squrell_img)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            Image(
                painter = painterResource(id = R.drawable.home_pet_img),
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.FillBounds
            )
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.fav_button),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        navHostController.navigate(Routes.FAVOURITES_SCREEN)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(12){
                val list = petsArray[it].split("|")
                var name = list[0]
                var type = list[1]
                if (isDogSelected.value||isCatSelected.value||isParrotSelected.value||isSquirrelSelected.value) {
                    if (isDogSelected.value) {
                        if (it in 0..2) PetCard(image = drawables[it], name = name, type = type){
                            navHostController.navigate(Routes.PETS_SCREEN + "$it")
                        }
                    }
                    if (isCatSelected.value) {
                        if (it in 3..5) PetCard(image = drawables[it], name = name, type = type){
                            navHostController.navigate(Routes.PETS_SCREEN + "/$it")
                        }
                    }
                    if (isParrotSelected.value) {
                        if (it in 6..9) PetCard(image = drawables[it], name = name, type = type){
                            navHostController.navigate(Routes.PETS_SCREEN + "/$it")
                        }
                    }
                    if (isSquirrelSelected.value) {
                        if (it in 10..12) PetCard(image = drawables[it], name = name, type = type){
                            navHostController.navigate(Routes.PETS_SCREEN + "/$it")
                        }
                    }
                } else PetCard(image = drawables[it], name = name, type = type){
                    navHostController.navigate(Routes.PETS_SCREEN + "/$it")
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun CategoryPetsUI(isSelected: MutableState<Boolean>, imgId: Int) {
    Card(
        modifier = Modifier
            .size(70.dp)
            .clickable {
                isSelected.value = !isSelected.value
            }, border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected.value) Secondary
            else Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = imgId),
            contentDescription = null,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            tint = if (isSelected.value) Color.White
            else Color.Black
        )
    }
}

@Composable
fun PetCard(image: Int, name: String, type: String, onNavigate:()-> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onNavigate.invoke()
        }, shape = RoundedCornerShape(32.dp)) {
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = name, style = TextStyle(
                fontFamily = Fonts.font,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            ))
            Text(text = type, style = TextStyle(
                fontFamily = Fonts.font,
                fontSize = 8.sp
            ))

        }
    }
}


