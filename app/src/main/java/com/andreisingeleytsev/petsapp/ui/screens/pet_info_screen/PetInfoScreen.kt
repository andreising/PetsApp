package com.andreisingeleytsev.petsapp.ui.screens.pet_info_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import com.andreisingeleytsev.petsapp.R
import com.andreisingeleytsev.petsapp.ui.theme.Secondary
import com.andreisingeleytsev.petsapp.ui.utils.Fonts
import com.andreisingeleytsev.petsapp.ui.utils.Routes

@Composable
fun PetInfoScreen(navHostController: NavHostController, viewModel: PetInfoScreenViewModel = hiltViewModel()){
    BoxWithConstraints(contentAlignment = Alignment.BottomCenter) {
        val height = maxHeight / 2
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = viewModel.imageDrawable.value),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height),
                contentScale = ContentScale.Crop
            )
        }
        Card(
            modifier = Modifier.height(height),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = viewModel.name.value, style = TextStyle(
                        fontFamily = Fonts.font,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ))
                    Text(text = viewModel.type.value, style = TextStyle(
                        fontFamily = Fonts.font,
                        fontSize = 16.sp
                    ))
                }
                Text(text = viewModel.title.value, style = TextStyle(
                    fontFamily = Fonts.font,
                    fontSize = 13.sp
                ))
                Button(
                    onClick = { viewModel.onEvent(PetIndoScreenEvent.OnSave) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Secondary
                    ), enabled = viewModel.boolean.value
                ) {
                    Text(
                        text = stringResource(R.string.add_to_favourites), style = TextStyle(
                            fontFamily = Fonts.font,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Button(
                    onClick = { navHostController.navigate(Routes.CAMERA_SCREEN) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Secondary
                    )
                ) {
                    Text(
                        text = stringResource(R.string.take_photo), style = TextStyle(
                            fontFamily = Fonts.font,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

        }
    }
}