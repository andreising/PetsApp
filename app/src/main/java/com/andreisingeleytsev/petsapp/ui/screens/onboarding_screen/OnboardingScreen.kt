package com.andreisingeleytsev.petsapp.ui.screens.onboarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreisingeleytsev.petsapp.ui.utils.Fonts
import com.andreisingeleytsev.petsapp.R
import com.andreisingeleytsev.petsapp.ui.theme.Secondary

@Composable
fun OnboardingScreen(onEndOnboard: ()->Unit) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val height = maxHeight
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.onboard_img),
                contentDescription = null,
                modifier = Modifier
                    .height(height / 2)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.pet_breeds), style = TextStyle(
                        color = Color.Black,
                        fontFamily = Fonts.font,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = stringResource(R.string.learn_different_categories_of_animals_and_their_species_find_the_perfect_pet_breed_for_you),
                    style = TextStyle(
                        color = Color.LightGray,
                        fontFamily = Fonts.font,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        onEndOnboard.invoke()
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Secondary
                    ), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(22.dp)
                ) {
                    Text(
                        text = stringResource(R.string.get_start),
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = Fonts.font,
                            fontSize = 26.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}