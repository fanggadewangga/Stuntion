package com.killjoy.stuntion.features.presentation.screen.support.detail

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.components.ErrorLayout
import com.killjoy.stuntion.features.presentation.utils.components.LoadingAnimation
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.features.presentation.utils.getCurrentLocation
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SupportDetailScreen(navController: NavController, donationId: String) {

    val viewModel = hiltViewModel<SupportDetailViewModel>()
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = Color.Transparent, darkIcons = true)
        setNavigationBarColor(color = Color.White, darkIcons = true)
    }
    val donationResponse = viewModel.donationResponse.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 10f)
    }
    val userMarkerState = MarkerState(position = LatLng(0.0, 0.0))
    val donationMarkerState = MarkerState(position = LatLng(0.0, 0.0))
    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            viewModel.isPermissionGranted.value = isGranted
            if (isGranted) {
                getCurrentLocation(context) {
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 10f)
                    userMarkerState.position = it
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
                cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 10f)
                userMarkerState.position = it
            }
        }

        else -> {
            SideEffect {
                permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.fetchDonationDetail(donationId)
    }

    when (donationResponse.value) {
        is Resource.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                LoadingAnimation(
                    circleSize = 16.dp,
                    spaceBetweenCircle = 10.dp,
                    travelDistance = 24.dp
                )
            }
        }

        is Resource.Error -> ErrorLayout()
        is Resource.Empty -> ErrorLayout("Something went wrong")
        is Resource.Success -> {
            val donation = remember {
                donationResponse.value.data!!
            }
            donationMarkerState.position = LatLng(donation.lat, donation.lon)
            Scaffold(
                bottomBar = {
                    StuntionButton(
                        onClick = {

                        },
                        contentPadding = PaddingValues(vertical = 8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        StuntionText(
                            text = "Support",
                            color = Color.White,
                            textStyle = Type.labelLarge(),
                        )
                    }
                },
                modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 14).dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
                ) {
                    // Image
                    item {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            AsyncImage(
                                model = donation.imageUrl,
                                contentDescription = "Support image",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier.height(
                                    (LocalConfiguration.current.screenHeightDp * 0.25).dp
                                )
                            )
                            Image(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Arrow back icon",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 48.dp)
                                    .align(Alignment.TopStart)
                                    .clickable {
                                        navController.popBackStack()
                                    }
                            )
                        }
                    }

                    // Title
                    item {
                        StuntionText(
                            text = donationResponse.value.data!!.title,
                            textStyle = Type.titleLarge(),
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    // Fee
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        ) {
                            StuntionText(
                                text = "Rp${donationResponse.value.data!!.fee}",
                                textStyle = Type.titleMedium(),
                                color = PrimaryBlue
                            )
                            StuntionText(
                                text = "or",
                                textStyle = Type.titleMedium(),
                                color = Color.Gray
                            )
                            StuntionText(
                                text = "1 Item",
                                textStyle = Type.titleMedium(),
                                color = PrimaryBlue
                            )
                        }
                    }

                    // Collected
                    item {
                        StuntionText(
                            text = "Collected Rp${donationResponse.value.data!!.currentValue * donationResponse.value.data!!.fee} from Rp${donationResponse.value.data!!.maxValue * donationResponse.value.data!!.fee} or ${donationResponse.value.data!!.currentValue} Item from ${donationResponse.value.data!!.maxValue} Item",
                            textStyle = Type.bodySmall(),
                            color = Color.Gray,
                            maxLine = 2,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }

                    // Progress
                    item {
                        LinearProgressIndicator(
                            progress = (donationResponse.value.data!!.currentValue / donationResponse.value.data!!.maxValue.toFloat()),
                            backgroundColor = Color.LightGray,
                            color = PrimaryBlue,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(12.dp)
                                .padding(horizontal = 16.dp)
                                .clip(shape = RoundedCornerShape(16.dp))
                        )
                    }

                    // Supporters and days counter
                    item {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            // Supporter count
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = R.drawable.ic_supporter,
                                    contentDescription = "Person icon",
                                    modifier = Modifier.size(20.dp)
                                )
                                StuntionText(
                                    text = "${donationResponse.value.data!!.currentValue}",
                                    textStyle = Type.titleSmall(),
                                )
                                StuntionText(
                                    text = "support",
                                    textStyle = Type.bodySmall(),
                                )
                            }

                            // Days counter
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = R.drawable.ic_clock,
                                    contentDescription = "Person icon",
                                    modifier = Modifier.size(20.dp)
                                )
                                StuntionText(
                                    text = donationResponse.value.data!!.dayRemaining.toString(),
                                    textStyle = Type.titleSmall(),
                                )
                                StuntionText(
                                    text = "days more",
                                    textStyle = Type.bodySmall(),
                                )
                            }
                        }
                    }

                    // Divider
                    item {
                        Divider(
                            color = Color.Gray,
                            thickness = 0.5.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }


                    // User Profile
                    item {
                        StuntionText(
                            text = "Uploaded By",
                            textStyle = Type.titleMedium(),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    item {
                        Card(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                            {
                                // Expert Image
                                AsyncImage(
                                    model = donationResponse.value.data!!.userAvatarUrl,
                                    contentDescription = "User Avatar",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                )

                                Spacer(modifier = Modifier.width(8.dp))
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(6.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    // User name
                                    StuntionText(
                                        text = donationResponse.value.data!!.name,
                                        textStyle = Type.labelLarge()
                                    )
                                    // User Location
                                    StuntionText(
                                        text = donationResponse.value.data!!.address,
                                        textStyle = Type.bodySmall(),
                                        color = Color.Gray
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        AsyncImage(
                                            model = R.drawable.ic_person,
                                            contentDescription = "Person icon",
                                            modifier = Modifier.size(12.dp)
                                        )
                                        StuntionText(
                                            text = "Contact Info",
                                            textStyle = Type.labelMedium(),
                                            color = PrimaryBlue,
                                        )
                                    }
                                }
                            }
                        }
                    }

                    item {
                        if (viewModel.isPermissionGranted.value) {
                            GoogleMap(
                                cameraPositionState = cameraPositionState,
                                properties = MapProperties(
                                    isMyLocationEnabled = true
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                                    .height(240.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            ) {
                                Marker(
                                    title = "My location",
                                    state = userMarkerState
                                )
                                Marker(
                                    title = "${donation.name}\'s location",
                                    state = donationMarkerState
                                )
                                Polyline(
                                    points = listOf(
                                        LatLng(
                                            userMarkerState.position.latitude,
                                            userMarkerState.position.longitude
                                        ),
                                        LatLng(donation.lat, donation.lon)
                                    ),
                                    width = 8f,
                                    color = PrimaryBlue,
                                    startCap = RoundCap()
                                )
                            }
                        } else {
                            Log.d("Maps", "Permission is not granted")
                        }
                    }

                    // Information
                    item {
                        StuntionText(
                            text = "Information",
                            textStyle = Type.titleMedium(),
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
                        )
                    }
                    item {
                        AnimatedVisibility(
                            visible = viewModel.isDescriptionVisibleState.value,
                            enter = fadeIn() + slideInVertically(),
                            exit = fadeOut() + slideOutVertically()
                        ) {
                            StuntionText(
                                text = donationResponse.value.data!!.story,
                                textStyle = Type.bodyMedium(),
                                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {
                            StuntionText(
                                text = "View All",
                                color = PrimaryBlue,
                                textStyle = Type.labelMedium(),
                                modifier = Modifier
                                    .clickable {
                                        viewModel.isDescriptionVisibleState.value =
                                            !viewModel.isDescriptionVisibleState.value
                                    }
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}