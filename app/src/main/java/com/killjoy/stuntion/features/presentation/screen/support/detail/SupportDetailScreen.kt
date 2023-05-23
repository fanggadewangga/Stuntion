package com.killjoy.stuntion.features.presentation.screen.support.detail

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberModalBottomSheetState
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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
import com.killjoy.stuntion.features.presentation.screen.support.payment.SupportPaymentSharedViewModel
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.DonorItem
import com.killjoy.stuntion.features.presentation.utils.components.DonorItemShimmer
import com.killjoy.stuntion.features.presentation.utils.components.ErrorLayout
import com.killjoy.stuntion.features.presentation.utils.components.LoadingAnimation
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.features.presentation.utils.components.SupportBottomSheet
import com.killjoy.stuntion.features.presentation.utils.getCurrentLocation
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.launch
import kotlin.math.floor

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SupportDetailScreen(
    navController: NavController,
    donationId: String,
    sharedViewModel: SupportPaymentSharedViewModel,
) {
    val viewModel = hiltViewModel<SupportDetailViewModel>()
    val systemUiController = rememberSystemUiController()
    val resource = LocalContext.current.resources
    val donationResponse = viewModel.donationResponse.collectAsStateWithLifecycle()
    val donorResponse = viewModel.donorResponse.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = viewModel.sheetState.value,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    BackHandler(modalBottomSheetState.isVisible) {
        coroutineScope.launch {
            if (viewModel.sendSupportStepState.value > 1)
                viewModel.sendSupportStepState.value--
            else
                modalBottomSheetState.hide()
        }
    }
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
                    viewModel.apply {
                        userLatState.value = it.latitude
                        userLonState.value = it.longitude
                        getAddressFromCoordinate(context)
                    }
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
                viewModel.apply {
                    userLatState.value = it.latitude
                    userLonState.value = it.longitude
                    getAddressFromCoordinate(context)
                }
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
        systemUiController.apply {
            setStatusBarColor(color = Color.Transparent, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
        viewModel.apply {
            fetchDonationDetail(donationId)
            fetchDonationDonors(donationId)
            fetchUserWalletBalance()
        }
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
            donationResponse.value.data?.let { donation ->
                donationMarkerState.position = LatLng(donation.lat, donation.lon)

                ModalBottomSheetLayout(
                    sheetState = modalBottomSheetState,
                    sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    sheetContent = {
                        SupportBottomSheet(
                            viewModel = viewModel,
                            donationId = donationId,
                            showErrorToast = {
                                Toasty.error(
                                    context,
                                    "Your balance is insufficient",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            navigateToSupportPaymentScreen = {
                                navController.navigate(Screen.SupportPaymentScreen.route)
                            },
                            navigateToSupportPaymentStatusScreen = {
                                navController.navigate(Screen.SupportPaymentStatusScreen.route)
                            },
                            navigateToAdditionalFoodScreen = {
                                navController.navigate(Screen.AdditionalFoodScreen.route)
                            },
                            sharedViewModel = sharedViewModel,
                            modifier = Modifier
                                .wrapContentHeight()
                                .padding(
                                    start = 16.dp,
                                    top = 16.dp,
                                    end = 16.dp,
                                    bottom = (LocalConfiguration.current.screenHeightDp / 10).dp
                                )
                        )
                    }
                ) {
                    Scaffold(
                        bottomBar = {
                            StuntionButton(
                                onClick = {
                                    coroutineScope.launch {
                                        if (modalBottomSheetState.isVisible)
                                            modalBottomSheetState.hide()
                                        else {
                                            viewModel.sheetState.value =
                                                ModalBottomSheetValue.Expanded
                                            modalBottomSheetState.animateTo(viewModel.sheetState.value)
                                        }

                                    }
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
                                    text = donation.title,
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
                                        text = "Rp${donation.fee}",
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
                                    text = "Collected Rp${donation.currentNominal.toInt()} from Rp${donation.maxValue * donation.fee} or ${
                                        (floor(
                                            donation.currentNominal / donation.fee
                                        )).toInt()
                                    } Item from ${donation.maxValue} Item",
                                    textStyle = Type.bodySmall(),
                                    color = Color.Gray,
                                    maxLine = 2,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                                )
                            }

                            // Progress
                            item {
                                LinearProgressIndicator(
                                    progress = (donation.currentNominal.toInt() / (donation.maxValue * donation.fee).toFloat()),
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
                                            text = "${donation.currentValue}",
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
                                            text = donation.dayRemaining.toString(),
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
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                    )
                                    {
                                        // Expert Image
                                        AsyncImage(
                                            model = donation.userAvatarUrl,
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
                                                text = donation.name,
                                                textStyle = Type.labelLarge()
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
                                            state = userMarkerState,
                                            icon = BitmapDescriptorFactory.fromBitmap(
                                                BitmapFactory.decodeResource(
                                                    resource,
                                                    R.drawable.ic_red_marker
                                                )
                                            )
                                        )
                                        Marker(
                                            title = "${donation.name}\'s location",
                                            state = donationMarkerState,
                                            icon = BitmapDescriptorFactory.fromBitmap(
                                                BitmapFactory.decodeResource(
                                                    resource,
                                                    R.drawable.ic_blue_marker
                                                )
                                            )
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
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                                    ) {
                                        // Pin
                                        AsyncImage(
                                            model = R.drawable.ic_red_marker,
                                            contentDescription = "Red marker",
                                            modifier = Modifier.size(23.dp)
                                        )

                                        // User Location
                                        StuntionText(
                                            text = donation.address,
                                            textStyle = Type.bodyMedium()
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
                                    modifier = Modifier.padding(
                                        start = 16.dp,
                                        top = 16.dp,
                                        end = 16.dp
                                    )
                                )
                            }
                            item {
                                AnimatedVisibility(
                                    visible = viewModel.isDescriptionVisibleState.value,
                                    enter = fadeIn() + slideInVertically(),
                                    exit = fadeOut() + slideOutVertically()
                                ) {
                                    StuntionText(
                                        text = donation.story,
                                        textStyle = Type.bodyMedium(),
                                        modifier = Modifier.padding(
                                            start = 16.dp,
                                            top = 16.dp,
                                            end = 16.dp
                                        )
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

                            // Support
                            item {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    StuntionText(
                                        text = "Support",
                                        textStyle = Type.titleMedium(),
                                    )
                                    StuntionText(
                                        text = "View All",
                                        textStyle = Type.labelMedium(),
                                        color = PrimaryBlue,
                                        modifier = Modifier.clickable {
                                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                                key = "donationId",
                                                value = donation.donationId
                                            )
                                            navController.navigate(Screen.DonorScreen.route)
                                        }
                                    )
                                }
                            }

                            if (donorResponse.value is Resource.Success && donorResponse.value.data != null) {
                                items(donorResponse.value.data!!) {
                                    DonorItem(
                                        donorResponse = it,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 4.dp)
                                    )
                                }
                            } else
                                items(6) {
                                    DonorItemShimmer(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 4.dp)
                                    )
                                }
                        }
                    }
                }
            }
        }
    }
}