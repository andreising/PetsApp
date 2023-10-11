package com.andreisingeleytsev.petsapp

import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.andreisingeleytsev.petsapp.ui.screens.main_screen.MainScreen
import com.andreisingeleytsev.petsapp.ui.screens.onboarding_screen.OnboardingScreen
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var photoUri: MutableState<Uri?> = mutableStateOf(null)
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)
    private var sharedPreferences: SharedPreferences? = null
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("kilo", "Permission granted")
            shouldShowCamera.value = true
        } else {
            Log.i("kilo", "Permission denied")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = this.getSharedPreferences("shared_preferences", MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        requestCameraPermission()
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
        requestCameraPermission()
        setContent {
            val isFirstLaunch = remember {
                mutableStateOf(
                    sharedPreferences?.getBoolean("first_launch", true) ?: true
                )
            }
            if (isFirstLaunch.value) OnboardingScreen {
                isFirstLaunch.value = false
                sharedPreferences?.edit()?.putBoolean("first_launch", false)?.apply()
            }
            else MainScreen(
                onImageCaptured = ::handleImageCapture,
                showCamera = shouldShowCamera,
                showPhoto = shouldShowPhoto,
                photoUri = photoUri,
                outputDirectory = outputDirectory,
                executor = cameraExecutor,
            )
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                shouldShowCamera.value = true
                Log.i("kilo", "Permission previously granted")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            ) -> Log.i("kilo", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    private fun handleImageCapture(uri: Uri) {
        photoUri.value = uri
        Log.i("kilo", "Image captured: $uri")
        shouldShowCamera.value = false

        shouldShowPhoto.value = true
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

}
