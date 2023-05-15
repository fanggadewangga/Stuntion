@file:Suppress("DEPRECATION")

package com.killjoy.stuntion.features.presentation.screen.video.detail

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.ArticleItemShimmer
import com.killjoy.stuntion.features.presentation.utils.components.ErrorLayout
import com.killjoy.stuntion.features.presentation.utils.components.LoadingAnimation
import com.killjoy.stuntion.features.presentation.utils.components.OtherArticleItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Suppress("DEPRECATION")
@Composable
fun VideoDetailScreen(navController: NavController, smartstunId: String) {
    val viewModel = hiltViewModel<VideoDetailViewModel>()
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = Color.Transparent, darkIcons = true)
        setNavigationBarColor(color = Color.White, darkIcons = true)
    }
    val context = LocalContext.current
    val detailResponse = viewModel.detailResponse.collectAsStateWithLifecycle()
    val listResponse = viewModel.listResponse.collectAsStateWithLifecycle()
    val player = remember {
        ExoPlayer.Builder(context).build()
    }

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.fetchSmartstunDetail(smartstunId)
    }

    when (detailResponse.value) {
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
            val smartstun = detailResponse.value.data!!
            Log.d("Video URL", smartstun.videoUrl)
            player.apply {
                val dataSource = DefaultDataSource.Factory(context)
                val source = ProgressiveMediaSource.Factory(dataSource)
                    .createMediaSource(MediaItem.fromUri(Uri.parse(smartstun.videoUrl)))
                addMediaSource(source)
                prepare()
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = (LocalConfiguration.current.screenHeightDp / 16).dp
                    )
            ) {
                // Image
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        AndroidView(
                            factory = {
                                PlayerView(it).apply {
                                    this.player = player
                                    useController = true
                                    setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16 / 9f)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp)
                        ) {
                            Image(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Arrow back icon",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .align(Alignment.TopStart)
                                    .clickable {
                                        navController.popBackStack()
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
                    }
                }

                // Title, reviewer, and date
                item {
                    // Title
                    Spacer(modifier = Modifier.height(8.dp))
                    StuntionText(
                        text = smartstun.title,
                        textStyle = Type.titleLarge(),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        // Reviewer
                        StuntionText(
                            text = "Reviewed by: dr. ${smartstun.reviewer}",
                            textStyle = Type.bodySmall(),
                            color = PrimaryBlue,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )

                        // Date
                        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_clock),
                                contentDescription = "Clock icon"
                            )
                            StuntionText(
                                text = smartstun.timestamp,
                                textStyle = Type.bodySmall(),
                                color = PrimaryBlue,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }

                // Description
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    StuntionText(
                        text = smartstun.description,
                        textStyle = Type.bodyMedium(),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                // Reference
                item {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        StuntionText(
                            text = "Reference:",
                            color = PrimaryBlue,
                            textStyle = Type.titleSmall()
                        )
                        StuntionText(
                            text = "Parents. Accessed 2023. 11 Must-Eat Nutrients for Your Child.\n" +
                                    "US Food and Drug Administration. Accessed in 2023. Key Nutrients and Your Family's Health.\n" +
                                    "Grow by Web MD. Retrieved 2023. Nutrients Your Kids May Be Missing.",
                            textStyle = Type.bodySmall()
                        )
                    }
                }

                // Other
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    StuntionText(
                        text = "Other Article",
                        textStyle = Type.titleMedium(),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Articles
                item {
                    when (listResponse.value) {
                        is Resource.Loading -> {
                            Row {
                                ArticleItemShimmer(Modifier.width(180.dp))
                                ArticleItemShimmer(Modifier.width(180.dp))
                            }
                        }

                        is Resource.Empty -> {}
                        is Resource.Success -> {
                            val otherList = listResponse.value.data!!.shuffled().take(2)
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            ) {
                                otherList.forEach {
                                    OtherArticleItem(
                                        video = it,
                                        onClick = {
                                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                                key = "smartstunId",
                                                value = it.articleId
                                            )
                                            navController.navigate(Screen.VideoDetailScreen.route)
                                        },
                                        modifier = Modifier.width(180.dp)
                                    )
                                }
                            }
                        }

                        is Resource.Error -> {}
                    }
                }
            }
        }
    }
}