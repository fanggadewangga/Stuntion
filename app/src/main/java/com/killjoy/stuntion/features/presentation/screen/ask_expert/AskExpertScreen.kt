package com.killjoy.stuntion.features.presentation.screen.ask_expert

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.QuestionCategoryChip
import com.killjoy.stuntion.features.presentation.utils.components.QuestionItem
import com.killjoy.stuntion.features.presentation.utils.components.QuestionItemShimmer
import com.killjoy.stuntion.features.presentation.utils.components.StuntionSearchField
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AskExpertScreen(navController: NavController) {

    val viewModel = hiltViewModel<AskExpertViewModel>()
    val questionResponse = viewModel.questionResponse.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddQuestionScreen.route)
                },
                shape = CircleShape,
                backgroundColor = PrimaryBlue
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_pen),
                    contentDescription = "Pen icon",
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        },
        modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 15).dp)
    ) {
        Column(
            Modifier.padding(horizontal = 16.dp)
        ) {

            // Search field
            Spacer(modifier = Modifier.height(24.dp))
            StuntionSearchField(
                valueState = viewModel.searchState.value,
                onValueChange = {
                    viewModel.searchState.value = it
                    viewModel.searchQuestion()
                },
                placeholder = "Find a question",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            )

            // Chips
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow {
                items(viewModel.questionCategories) { category ->
                    QuestionCategoryChip(
                        category = category,
                        selected = viewModel.selectedCategory.value,
                        onSelected = {
                            viewModel.selectedCategory.value = it
                        }
                    )
                }
            }

            // Headline
            Spacer(modifier = Modifier.height(16.dp))
            StuntionText(text = "The Latest Health Discussion", textStyle = Type.titleMedium())

            // Chats
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                when (questionResponse.value) {
                    is Resource.Loading -> {
                        items(8) {
                            QuestionItemShimmer(modifier = Modifier.fillMaxWidth())
                        }
                    }
                    is Resource.Error -> {

                    }
                    is Resource.Success -> {
                        items(
                            if (viewModel.selectedCategory.value == "All") questionResponse.value.data!!.shuffled()
                            else questionResponse.value.data!!
                                .filter {
                                    it.categories.contains(viewModel.selectedCategory.value)
                                }
                                .shuffled()
                        ) {
                            QuestionItem(
                                question = it,
                                onClick = {
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        key = "questionId",
                                        value = it.questionId
                                    )
                                    navController.navigate(Screen.AskExpertDetailScreen.route)
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    is Resource.Empty -> {

                    }
                }
            }
        }
    }
}