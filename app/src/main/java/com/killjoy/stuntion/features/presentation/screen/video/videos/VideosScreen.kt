package com.killjoy.stuntion.features.presentation.screen.video.videos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.*

@Composable
fun VideosScreen(navController: NavController) {
    val viewModel = hiltViewModel<VideosViewModel>()
    val smartstuns = viewModel.smartstunResponse.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp)
    ) {

        // Top Bar
        StuntionTopBar(title = "SmartStun", onBackPressed = {})

        // Search field
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            StuntionSearchField(
                valueState = viewModel.searchState.value,
                onValueChange = {
                    viewModel.searchState.value = it
                    viewModel.searchSmartstuns()
                },
                placeholder = "Find a content",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            )
        }


        // Chips
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(viewModel.videosCategory) { category ->
                CategoryChip(
                    category = category,
                    selected = viewModel.selectedCategory.value,
                    onSelected = {
                        viewModel.selectedCategory.value = it
                    }
                )
            }
        }

        // Articles
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            when (smartstuns.value) {
                is Resource.Loading -> {
                    items(6) {
                        ArticleItemShimmer(
                            modifier = Modifier.padding(end = 16.dp),
                        )
                    }
                }
                is Resource.Empty -> {

                }
                is Resource.Success -> {
                    items(
                        if (viewModel.selectedCategory.value == "All") smartstuns.value.data!!
                        else smartstuns.value.data!!.filter {
                            it.categories.contains(viewModel.selectedCategory.value)
                        }
                    ) {
                        ArticleItem(
                            article = it,
                            onClick = {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = "smartstunId",
                                    value = it.articleId
                                )
                                navController.navigate(Screen.VideoDetailScreen.route)
                            },
                            modifier = Modifier.padding(end = 16.dp),
                        )
                    }
                }
                is Resource.Error -> {

                }
            }
        }
    }
}

@Preview
@Composable
fun ArticlesPreview() {
    VideosScreen(navController = rememberNavController())
}