package com.killjoy.stuntion.features.presentation.screen.expert

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.ErrorLayout
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExpertDetailScreen(navController: NavController, expertId: String) {
    val viewModel = hiltViewModel<ExpertDetailViewModel>()
    val systemUiController = rememberSystemUiController()
    val expertResponse = viewModel.expertResponse.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        systemUiController.apply {
            setStatusBarColor(color = Color.Transparent, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
        viewModel.fetchExpertDetail(expertId)
    }

    when (expertResponse.value) {
        is Resource.Loading -> {}
        is Resource.Success -> {
            Scaffold(
                bottomBar = {
                    Card(
                        elevation = 8.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .background(Color.White)
                        ) {
                            Column {
                                StuntionText(
                                    text = "Consulting fee",
                                    textStyle = Type.labelMedium(),
                                    color = LightGray
                                )
                                StuntionText(
                                    text = "IDR${expertResponse.value.data?.fee}",
                                    textStyle = Type.titleMedium(),
                                    color = PrimaryBlue
                                )
                            }
                            StuntionButton(
                                onClick = {
                                    navController.navigate(Screen.ChatRoomScreen.route)
                                },
                                modifier = Modifier.width(160.dp)
                            ) {
                                StuntionText(
                                    text = "Chat Now",
                                    textStyle = Type.labelLarge(),
                                    color = Color.White
                                )
                            }
                        }
                    }
                },
                modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = (LocalConfiguration.current.screenHeightDp / 8).dp)
                ) {

                    // Image
                    Box(modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(
                            model = expertResponse.value.data?.avatarUrl,
                            contentDescription = "Expert image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.height(280.dp)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 48.dp)
                        ) {
                            Image(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Arrow back icon",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .align(Alignment.TopStart)
                                    .clickable {

                                    }
                            )
                            Image(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share icon",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .align(Alignment.TopEnd)
                                    .clickable { }
                            )
                        }

                        // Online status
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .background(
                                        color = LightBlue,
                                        shape = RoundedCornerShape(100.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            ) {
                                AsyncImage(
                                    model = R.drawable.ic_green_circle,
                                    contentDescription = "Green circle icon",
                                    modifier = Modifier.size(9.dp)
                                )
                                StuntionText(
                                    text = "Online",
                                    textStyle = Type.labelSmall(),
                                    color = Color.Green
                                )
                            }
                        }
                    }

                    // Name
                    Spacer(modifier = Modifier.height(24.dp))
                    expertResponse.value.data?.name?.let { it1 ->
                        StuntionText(
                            text = it1,
                            textStyle = Type.titleMedium()
                        )
                    }

                    // Role
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        expertResponse.value.data?.categories?.forEachIndexed { index, category ->
                            StuntionText(
                                text = category,
                                textStyle = Type.bodySmall(),
                                color = LightGray
                            )
                            if (index+1 < expertResponse.value.data?.categories!!.size)
                                StuntionText(
                                    text = " - ",
                                    textStyle = Type.bodySmall(),
                                    color = LightGray
                                )
                        }
                    }

                    // Rating and experience
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                        // Rating
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            AsyncImage(
                                model = R.drawable.ic_star,
                                contentDescription = "Star icon",
                                modifier = Modifier.size(38.dp)
                            )
                            Column {
                                StuntionText(text = "${expertResponse.value.data?.rating} Out of 5.0", textStyle = Type.titleSmall())
                                StuntionText(
                                    text = "500 Patiens review",
                                    textStyle = Type.bodySmall()
                                )
                            }
                        }

                        // Experience
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            AsyncImage(
                                model = R.drawable.ic_bag,
                                contentDescription = "Star icon",
                                modifier = Modifier.size(38.dp)
                            )
                            Column {
                                StuntionText(text = "${expertResponse.value.data?.experienceYear} Year", textStyle = Type.titleSmall())
                                StuntionText(text = "Experience", textStyle = Type.bodySmall())
                            }
                        }
                    }

                    // STR
                    Spacer(modifier = Modifier.height(24.dp))
                    Card(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                AsyncImage(
                                    model = R.drawable.ic_garuda,
                                    contentDescription = "Garuda icon",
                                    modifier = Modifier.size(24.dp)
                                )
                                StuntionText(text = "STR Number", textStyle = Type.titleMedium())
                            }
                            expertResponse.value.data?.str?.let { it1 -> StuntionText(text = it1, textStyle = Type.bodyMedium()) }
                        }
                    }

                    // Education
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {

                            // Title
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = R.drawable.ic_education,
                                        contentDescription = "Garuda icon",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    StuntionText(text = "Education", textStyle = Type.titleMedium())
                                }

                                Image(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Arrow down icon",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .align(Alignment.CenterEnd)
                                )
                            }

                            // Value
                            expertResponse.value.data?.educations?.forEach {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    StuntionText(
                                        text = it,
                                        textStyle = Type.bodyMedium()
                                    )
                                    StuntionText(
                                        text = "2018",
                                        textStyle = Type.bodyMedium(),
                                        modifier = Modifier.align(
                                            Alignment.CenterEnd
                                        )
                                    )
                                }
                            }
                        }
                    }

                    // Workplace
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {

                            // Title
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = R.drawable.ic_workplace,
                                        contentDescription = "Workplace icon",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    StuntionText(text = "Workplace", textStyle = Type.titleMedium())
                                }

                                Image(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Arrow down icon",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .align(Alignment.CenterEnd)
                                )
                            }

                            // Value
                            expertResponse.value.data?.workplaces?.forEach {
                                StuntionText(
                                    text = it,
                                    textStyle = Type.bodyMedium()
                                )
                            }
                        }
                    }
                }
            }
        }
        is Resource.Error -> ErrorLayout()
        is Resource.Empty -> ErrorLayout("Something went wrong")
    }
}