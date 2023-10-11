package com.andreisingeleytsev.petsapp.ui.screens.main_screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andreisingeleytsev.petsapp.R
import com.andreisingeleytsev.petsapp.ui.navigation.MainNavigationGraph
import com.andreisingeleytsev.petsapp.ui.theme.Secondary
import com.andreisingeleytsev.petsapp.ui.utils.Fonts
import com.andreisingeleytsev.petsapp.ui.utils.Routes
import java.io.File
import java.util.concurrent.Executor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    showCamera: MutableState<Boolean>,
    showPhoto: MutableState<Boolean>,
    photoUri: MutableState<Uri?>,
    outputDirectory: File,
               executor: Executor,
               onImageCaptured: (Uri) -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            if (currentRoute == Routes.FAVOURITES_SCREEN) Text(
                text = stringResource(R.string.favourites), style = TextStyle(
                    fontFamily = Fonts.font,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Secondary
                )
            )
        }, navigationIcon = {
            if (currentRoute != Routes.HOME_SCREEN) Image(
                painter = painterResource(id = R.drawable.back_btn),
                contentDescription = null,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ))
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            MainNavigationGraph(
                showCamera = showCamera,
                showPhoto = showPhoto,
                photoUri = photoUri,
                navHostController = navController, outputDirectory = outputDirectory,
                cameraExecutor = executor,
                onImageCaptured = onImageCaptured
            )
        }
    }
}