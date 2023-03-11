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
                onValueChange = { viewModel.searchState.value = it },
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
            item {
                ArticleItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Nutrition to Prevent Stunted Child Growth",
                    description = "Every parent certainly wants their child's growth and development to run ...",
                    category = listOf("Stunting", "Nutrition Consultation"),
                    thumbnailUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/smartstun%2Fthumbnail%2Fanh-nguyen-kcA-c3f_3FE-unsplash.jpg?alt=media&token=993be102-da54-4934-8100-a878843b7307",
                    onClick = { }
                )
            }
            item {
                ArticleItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = "5 Important Nutrients for Child Growth",
                    description = "Which parent doesn't want their child to grow up healthy with ideal weight and ...",
                    category = listOf("Nutrition"),
                    thumbnailUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/smartstun%2Fthumbnail%2Fhui-sang-FKwGPzwaGqc-unsplash.jpg?alt=media&token=d3d450cf-fd23-48a2-943b-4a2d5d6292a7",
                    onClick = { }
                )
            }
            item {
                ArticleItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Pregnant Women's Nutrition Is Not Idealh",
                    description = "Meeting the nutritional needs of pregnant women is the best thing you ...",
                    category = listOf("Pregnant"),
                    thumbnailUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/smartstun%2Fthumbnail%2Ffilipp-romanovski-2BAgpU2pKdY-unsplash.jpg?alt=media&token=2c3f9c08-4d80-434e-90a4-20508feb7445",
                    onClick = { }
                )
            }
            item {
                ArticleItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Impact of Early Marriage on Teenagers",
                    description = "Early marriage is still rife in Indonesia. One of the causes of early marriage is ...",
                    category = listOf("Early marriage"),
                    thumbnailUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/smartstun%2Fthumbnail%2Fsamantha-gades-N1CZNuM_Fd8-unsplash.jpg?alt=media&token=da3abcf8-dd33-49da-8196-8b6b75af8427",
                    onClick = { }
                )
            }
            item {
                ArticleItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Importance of Nutritional for Pregnant ",
                    description = "The physical and hormonal changes that the body experiences during ...",
                    category = listOf("Pregnant", "Nutrition"),
                    thumbnailUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/smartstun%2Fthumbnail%2Fchristin-noelle-zHMhEampZuI-unsplash.jpg?alt=media&token=cc9f70db-cd98-4ebc-b664-134ff63f5c47",
                    onClick = { }
                )
            }
        }
    }
}

@Preview
@Composable
fun ArticlesPreview() {
    VideosScreen(navController = rememberNavController())
}