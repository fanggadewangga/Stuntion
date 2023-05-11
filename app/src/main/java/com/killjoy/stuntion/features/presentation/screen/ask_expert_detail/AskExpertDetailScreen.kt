package com.killjoy.stuntion.features.presentation.screen.ask_expert_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.ErrorLayout
import com.killjoy.stuntion.features.presentation.utils.components.LoadingAnimation
import com.killjoy.stuntion.features.presentation.utils.components.QuestionItem
import com.killjoy.stuntion.features.presentation.utils.components.QuestionItemShimmer
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.Gray
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun AskExpertDetailScreen(navController: NavController, questionId: String) {

    val viewModel = hiltViewModel<AskExpertDetailViewModel>()
    val questionListResponse = viewModel.questionListResponse.collectAsStateWithLifecycle()
    val questionResponse = viewModel.questionResponse.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.fetchQuestionDetail(questionId)
    }

    when (questionResponse.value) {
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

        is Resource.Success -> {
            questionResponse.value.data.let { data ->
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(
                            top = 32.dp,
                            bottom = (LocalConfiguration.current.screenHeightDp / 17).dp
                        )
                ) {
                    // Top bar
                    item {
                        StuntionTopBar(
                            title = "Ask Expert",
                            onBackPressed = { navController.popBackStack() })
                    }


                    // Question
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(start = 16.dp, top = 24.dp, end = 16.dp)
                        ) {

                            // Image
                            AsyncImage(
                                model = data?.userAvatarUrl,
                                contentDescription = "User Avatar",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.width(8.dp))
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                // Title
                                data?.let {
                                    StuntionText(
                                        text = it.title,
                                        textStyle = Type.titleMedium()
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp, end = 16.dp)
                                ) {
                                    // User
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        modifier = Modifier.wrapContentWidth()
                                    ) {
                                        AsyncImage(
                                            model = R.drawable.ic_person,
                                            contentDescription = "Person icon",
                                            modifier = Modifier.size(16.dp)
                                        )
                                        questionResponse.value.data?.let {
                                            StuntionText(
                                                text = it.userName,
                                                textStyle = Type.bodySmall(),
                                                color = Gray,
                                                maxLine = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    }

                                    // Time
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        modifier = Modifier.wrapContentWidth()
                                    ) {
                                        AsyncImage(
                                            model = R.drawable.ic_clock,
                                            contentDescription = "Clock icon",
                                            modifier = Modifier.size(16.dp)
                                        )
                                        questionResponse.value.data?.let {
                                            StuntionText(
                                                text = it.timestamp,
                                                textStyle = Type.bodySmall(),
                                                color = Gray
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Description
                        questionResponse.value.data?.let {
                            StuntionText(
                                text = it.question,
                                textStyle = Type.bodyMedium(),
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        Divider(
                            color = LightGray, modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 16.dp)
                        )
                    }

                    // Answer
                    item {
                        // Answered by
                        StuntionText(
                            text = "Answered By",
                            textStyle = Type.titleMedium(),
                            modifier = Modifier.padding(start = 16.dp)
                        )


                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .clickable {
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        key = "expertId",
                                        value = questionResponse.value.data?.expertId
                                    )
                                    navController.navigate(Screen.ExpertDetailScreen.route)
                                }
                        ) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                            {
                                // Expert Image
                                AsyncImage(
                                    model = questionResponse.value.data?.expertAvatarUrl,
                                    contentDescription = "Expert Avatar",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(72.dp)
                                        .clip(CircleShape)
                                )

                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.fillMaxWidth()) {

                                    // Expert name
                                    data?.expertName?.let {
                                        StuntionText(
                                            text = it,
                                            textStyle = Type.titleMedium()
                                        )
                                    }

                                    // Expert role
                                    Row {
                                        data?.expertCategories?.forEachIndexed { index, category ->
                                            StuntionText(
                                                text = category,
                                                textStyle = Type.bodySmall(),
                                                color = LightGray
                                            )
                                            if (index + 1 < data.expertCategories.size
                                            )
                                                StuntionText(
                                                    text = " - ",
                                                    textStyle = Type.bodySmall(),
                                                    color = LightGray
                                                )
                                        }
                                    }


                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 4.dp, end = 8.dp)
                                    ) {
                                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                            // Experience
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                                modifier = Modifier.wrapContentWidth()
                                            ) {
                                                AsyncImage(
                                                    model = R.drawable.ic_bag,
                                                    contentDescription = "Bag icon",
                                                    modifier = Modifier.size(16.dp)
                                                )
                                                StuntionText(
                                                    text = data?.expertExperience.toString(),
                                                    textStyle = Type.bodySmall(),
                                                    color = Gray,
                                                    maxLine = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                            }

                                            // Time
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                                modifier = Modifier.wrapContentWidth()
                                            ) {
                                                AsyncImage(
                                                    model = R.drawable.ic_star,
                                                    contentDescription = "Star icon",
                                                    modifier = Modifier.size(16.dp)
                                                )
                                                StuntionText(
                                                    text = data?.expertRating.toString(),
                                                    textStyle = Type.bodySmall(),
                                                    color = Gray
                                                )
                                            }
                                        }


                                        // Chat Button
                                        Spacer(modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 4).dp))
                                        StuntionButton(
                                            onClick = {

                                            },
                                            contentPadding = PaddingValues(
                                                horizontal = 16.dp,
                                                vertical = 4.dp
                                            )
                                        ) {
                                            StuntionText(
                                                text = "Chat",
                                                color = Color.White,
                                                textStyle = Type.labelLarge(),
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Expert Answer
                        Spacer(modifier = Modifier.height(16.dp))
                        data?.answer?.let {
                            StuntionText(
                                text = it,
                                textStyle = Type.bodyMedium(),
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }

                    // Other Question
                    item {
                        StuntionText(
                            text = "Other Question",
                            textStyle = Type.titleMedium(),
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    when (questionListResponse.value) {
                        is Resource.Loading -> {
                            items(8) {
                                QuestionItemShimmer(modifier = Modifier.fillMaxWidth())
                            }
                        }

                        is Resource.Error -> {

                        }

                        is Resource.Success -> {
                            questionListResponse.value.data?.shuffled()?.let {
                                items(it.take(2)) { item ->
                                    QuestionItem(
                                        question = item,
                                        onClick = {
                                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                                key = "questionId",
                                                value = item.questionId
                                            )
                                            navController.navigate(Screen.AskExpertDetailScreen.route) {
                                                popUpTo(Screen.ConsultScreen.route) {
                                                    inclusive = false
                                                }
                                            }
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }

                        is Resource.Empty -> {

                        }
                    }
                }
            }
        }

        is Resource.Error -> ErrorLayout()
        is Resource.Empty -> ErrorLayout("Something went wrong")
    }
}