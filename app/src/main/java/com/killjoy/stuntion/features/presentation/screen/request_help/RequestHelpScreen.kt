package com.killjoy.stuntion.features.presentation.screen.request_help

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.screen.request_help.confirmation.ConfirmationScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.detail_information.DetailInformationScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.help_target.HelpTargetScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.personal_data.PersonalDataScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.title.TitleScreen
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.LoadingAnimation
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.features.presentation.utils.getCurrentLocation
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import es.dmoral.toasty.Toasty
import java.time.LocalDate

@Composable
fun RequestHelpScreen(navController: NavController) {

    val viewModel = hiltViewModel<RequestHelpViewModel>()
    val context = LocalContext.current
    val donationResponse = viewModel.postDonationResponse.collectAsStateWithLifecycle()
    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            viewModel.isPermissionGranted.value = isGranted
            if (isGranted) {
                getCurrentLocation(context) {
                    viewModel.userPositionState.value = LatLng(it.latitude, it.longitude)
                }
            }
        }
    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ),
        -> {
            viewModel.isPermissionGranted.value = true
            getCurrentLocation(context) {
                viewModel.userPositionState.value = LatLng(it.latitude, it.longitude)
            }
        }
        else -> viewModel.isPermissionGranted.value = false
    }


    LaunchedEffect(donationResponse.value) {
        when (donationResponse.value) {
            is Resource.Success -> {
                Toasty.success(context, "Successfully added a support request!", Toast.LENGTH_SHORT)
                    .show()
                navController.navigate(Screen.RequestHelpSuccessScreen.route) {
                    popUpTo(Screen.RequestHelpScreen.route) {
                        inclusive = true
                    }
                }
            }
            is Resource.Error -> Toasty.error(context, donationResponse.value.message.toString(), Toast.LENGTH_SHORT).show()
            is Resource.Loading -> {}
            is Resource.Empty -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top bar
        Box(modifier = Modifier.fillMaxWidth()) {
            StuntionText(
                text = "Request For Help",
                textStyle = Type.titleLarge(),
                modifier = Modifier
                    .align(
                        Alignment.TopCenter
                    )
                    .padding(top = (LocalConfiguration.current.screenHeightDp / 14).dp)
            )
        }
        Divider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )

        // Form to
        Spacer(modifier = Modifier.height(16.dp))
        StuntionText(
            text = "Form To Request For Help",
            textStyle = Type.titleMedium(),
            modifier = Modifier.padding(start = 16.dp)
        )
        StuntionText(
            text = "This form is used for those of you who wish to request additional food assistance. Please fill in and fill in correctly",
            textStyle = Type.bodyMedium(),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Divider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        when (viewModel.currentStep.value) {
            1 -> PersonalDataScreen()
            2 -> HelpTargetScreen()
            3 -> TitleScreen()
            4 -> DetailInformationScreen()
            else -> ConfirmationScreen()
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            if (donationResponse.value is Resource.Loading)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LoadingAnimation()
                }
            else {
                StuntionButton(
                    backgroundColor = Color.White,
                    borderColor = PrimaryBlue,
                    borderWidth = 0.5.dp,
                    onClick = {
                        if (viewModel.currentStep.value > 1)
                            viewModel.currentStep.value -= 1
                        else navController.navigate(Screen.SupportScreen.route)
                    },
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 2.2).dp)
                ) {
                    StuntionText(
                        text = "Back",
                        color = PrimaryBlue,
                        textStyle = Type.labelLarge()
                    )
                }
                StuntionButton(
                    onClick = {
                        if (viewModel.currentStep.value < 5)
                            viewModel.currentStep.value += 1
                        else {
                            when (viewModel.selectedDuration.value) {
                                viewModel.listOfDuration[0] -> viewModel.endDate.value =
                                    LocalDate.now().plusDays(10)

                                viewModel.listOfDuration[1] -> viewModel.endDate.value =
                                    LocalDate.now().plusDays(30)

                                viewModel.listOfDuration[2] -> viewModel.endDate.value =
                                    LocalDate.now().plusDays(60)
                            }
                            if (viewModel.isFormValid.value) {
                                if (viewModel.isPermissionGranted.value)
                                    viewModel.postNewDonation()
                                else
                                    permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                            }
                            else
                                Toasty.warning(
                                    context,
                                    "Please complete all forms first!",
                                    Toast.LENGTH_SHORT
                                ).show()
                        }
                    },
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 2.2).dp)
                ) {
                    StuntionText(
                        text = if (viewModel.currentStep.value <= viewModel.listOfStep.size) "Next" else "Finish",
                        color = Color.White,
                        textStyle = Type.labelLarge()
                    )
                }
            }
        }
    }
}