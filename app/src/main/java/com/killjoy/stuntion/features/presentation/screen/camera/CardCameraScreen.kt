package com.killjoy.stuntion.features.presentation.screen.camera

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.Type
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CamSelector
import com.ujizin.camposer.state.ImageCaptureResult
import com.ujizin.camposer.state.rememberCamSelector
import com.ujizin.camposer.state.rememberCameraState
import java.io.File

@Composable
fun CardCameraScreen(navController: NavController) {
    val cameraState = rememberCameraState()
    val camSelector by rememberCamSelector(CamSelector.Back)
    val context = LocalContext.current
    val permission = Manifest.permission.CAMERA
    val photoFile = File(
        "${context.getExternalFilesDir(null)}/", "${System.currentTimeMillis()}}.jpg"
    )
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) Log.d("Camera", "Granted")
        else Log.d("Camera", "Not Granted")
    }
    val imageBitmap = remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(Unit) {
        requestCameraPermission(context, permission, requestPermissionLauncher)
    }

    CameraPreview(
        cameraState = cameraState,
        camSelector = camSelector,
    ) {
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = (LocalConfiguration.current.screenHeightDp / 4).dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(16.dp))
                    .fillMaxWidth(0.8f)
                    .height((LocalConfiguration.current.screenHeightDp / 4).dp)
            )
            Column(
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(top = ((LocalConfiguration.current.screenHeightDp / 3).dp))
            ) {
                StuntionText(
                    text = "Place your KTP in the frame",
                    textStyle = Type.bodySmall(),
                    color = Color.White,
                )
                AsyncImage(
                    model = R.drawable.ic_camera_button,
                    contentDescription = "Camera button",
                    modifier = Modifier
                        .size(56.dp)
                        .clickable {
                            cameraState.takePicture(photoFile) {
                                if (it is ImageCaptureResult.Success) {
                                    val input =
                                        context.contentResolver.openInputStream(it.savedUri!!)
                                    val bitmap = BitmapFactory.decodeStream(input)
                                    if (bitmap != null) {
                                        imageBitmap.value = bitmap
                                        Log.d("IMAGE", bitmap.toString())
                                    }
                                    else
                                        Log.d("ERROR", "FAILED TO TAKE PICTURE")
                                    navController.navigate(Screen.IdentityVerificationScreen.route)
                                } else {
                                    Log.d("ERROR", it.toString())
                                }
                            }
                        }
                )
            }
        }
    }
}