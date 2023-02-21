package com.killjoy.stuntion.features.presentation.screen.video.videos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.features.presentation.utils.components.ArticleItem
import com.killjoy.stuntion.features.presentation.utils.components.QuestionCategoryChip
import com.killjoy.stuntion.features.presentation.utils.components.StuntionSearchField
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar

@Composable
fun VideosScreen(navController: NavController) {
    val viewModel = hiltViewModel<VideosViewModel>()

    val selectedCategory = remember {
        mutableStateOf("All")
    }

    Column(
        Modifier.fillMaxSize().padding(vertical = 24.dp)
    ) {

        // Top Bar
        StuntionTopBar(title = "Article", onBackPressed = {})

        // Search field
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            StuntionSearchField(
                valueState = viewModel.searchState.value,
                onValueChange = { viewModel.searchState.value = it },
                placeholder = "Find a article",
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
                QuestionCategoryChip(
                    category = category,
                    selected = selectedCategory.value,
                    onSelected = {
                        selectedCategory.value = it
                    }
                )
            }
        }

        // Articles
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(10) {
                ArticleItem()
            }
        }
    }
}

@Preview
@Composable
fun ArticlesPreview() {
    VideosScreen(navController = rememberNavController())
}