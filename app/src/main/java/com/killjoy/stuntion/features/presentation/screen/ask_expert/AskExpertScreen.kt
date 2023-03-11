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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.QuestionCategoryChip
import com.killjoy.stuntion.features.presentation.utils.components.QuestionItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionSearchField
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AskExpertScreen(navController: NavController) {

    val viewModel = hiltViewModel<AskExpertViewModel>()
    val questionCategories = listOf(
        "Stunting",
        "Nutrition Consultation",
        "Pregnant",
        "Child",
    )
    val selectedCategory = remember {
        mutableStateOf("")
    }

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
                onValueChange = { viewModel.searchState.value = it },
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
                items(questionCategories) { category ->
                    QuestionCategoryChip(
                        category = category,
                        selected = selectedCategory.value,
                        onSelected = {
                            selectedCategory.value = it
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
                item {
                    QuestionItem(
                        title = "How to organize a 13 month old baby's feeding schedule?",
                        question = "Good afternoon, I'm a mother of a 13-month-old baby, when the baby is full, my baby is excited to Good afternoon, I'm a mother of a 5-month-old baby, when the baby is full, my baby is excited to...",
                        userName = "Maya Susanti",
                        expertName = "dr. Hanna Hanifah",
                        date = "Just now",
                        userAvatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_1.png?alt=media&token=931a191d-6277-4480-b860-fdf8e0e41dfe",
                        expertAvatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/expert%2Fnational-cancer-institute-byGTytEGjBo-unsplash.jpg?alt=media&token=00d329dc-1360-440d-96ab-acbf727212b2",
                        onClick = { navController.navigate(Screen.AskExpertDetailScreen.route) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    QuestionItem(
                        title = "How to train child stimulation in a safe and fun way?",
                        question = "Good afternoon, I'm a father of a 13-month-old baby, how to play with a 13 month old child to practice stimulation in a safe and fun way?",
                        userName = "Budi",
                        expertName = "dr. Nadia Nurotul Fuadah",
                        date = "1 day ago",
                        userAvatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_7.png?alt=media&token=532b44b4-57c5-49ee-987a-14c270093033",
                        expertAvatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/expert%2Fhumberto-chavez-FVh_yqLR9eA-unsplash.jpg?alt=media&token=34ed3ac3-75dc-44dc-b2e7-6644328da3d2",
                        onClick = { navController.navigate(Screen.AskExpertDetailScreen.route) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    QuestionItem(
                        title = "Tips for dealing with a 2-year-old child who has difficulty",
                        question = "Hello, I have a 2-year-old child, BB is only 12 Kg, my child has been feeling stressed for almost a week ...",
                        userName = "Valenta",
                        expertName = "dr. Leony",
                        date = "5 day ago",
                        userAvatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_4.png?alt=media&token=e7b0aabb-3452-409e-9d37-d34f78fe92b6",
                        expertAvatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/expert%2Fsiednji-leon-lnlSIsiSjjc-unsplash.jpg?alt=media&token=a9403851-2e8f-4c11-9f5e-8d7f157777f5",
                        onClick = { navController.navigate(Screen.AskExpertDetailScreen.route) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    QuestionItem(
                        title = "Foods that can be used to increase a child's height",
                        question = "Afternoon, I am a mother of a 30-month-old toddler.. with a height of only 81 cm and a weight of ...",
                        userName = "Nadine",
                        expertName = "dr. Austin Distel",
                        date = "3 day ago",
                        userAvatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_6.png?alt=media&token=799ebf5b-1584-474b-b155-4e97949c0422",
                        expertAvatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/expert%2Faustin-distel-7bMdiIqz_J4-unsplash.jpg?alt=media&token=5e368d4d-4d2f-434a-a5ba-b7182c1c0df4",
                        onClick = { navController.navigate(Screen.AskExpertDetailScreen.route) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AskExpertPrev() {
    AskExpertScreen(navController = rememberNavController())
}