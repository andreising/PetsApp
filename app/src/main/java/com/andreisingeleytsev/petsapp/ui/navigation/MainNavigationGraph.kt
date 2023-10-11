package com.andreisingeleytsev.petsapp.ui.navigation


import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andreisingeleytsev.petsapp.ui.photo.CameraView
import com.andreisingeleytsev.petsapp.ui.screens.favourites_screen.FavouriteScreen
import com.andreisingeleytsev.petsapp.ui.screens.home_screen.HomeScreen
import com.andreisingeleytsev.petsapp.ui.screens.pet_info_screen.PetInfoScreen
import com.andreisingeleytsev.petsapp.ui.utils.Routes
import java.io.File
import java.util.concurrent.Executor


@Composable
fun MainNavigationGraph(
    showCamera: MutableState<Boolean>,
    showPhoto: MutableState<Boolean>,
    photoUri: MutableState<Uri?>,
    navHostController: NavHostController,
    outputDirectory: File,
    cameraExecutor: Executor,
    onImageCaptured: (Uri) -> Unit
) {
    NavHost(navController = navHostController, startDestination = Routes.HOME_SCREEN) {
        composable(Routes.HOME_SCREEN) {
            HomeScreen(navHostController)
        }
        composable(Routes.PETS_SCREEN + "/{id}") {
            PetInfoScreen(navHostController)
        }
        composable(Routes.FAVOURITES_SCREEN) {
            FavouriteScreen()
        }
        composable(Routes.CAMERA_SCREEN) {
            CameraView(
                showCamera = showCamera,
            showPhoto = showPhoto,
                photoUri = photoUri,
                outputDirectory = outputDirectory,
                executor = cameraExecutor,
                onImageCaptured = onImageCaptured,
                onError = { Log.e("kilo", "View error:", it) })
        }
    }
}
