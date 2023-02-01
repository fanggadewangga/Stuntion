package com.killjoy.stuntion.features.presentation.screen.consultation.ask_expert.questions

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
                    contentDescription = "Pen icon"
                )
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
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
            QuestionItem(
                title = "How to organize a 5 month old baby's feeding schedule?",
                question = "Good afternoon, I'm a mother of a 5-month-old baby, when the baby is full, my baby is excited to Good afternoon, I'm a mother of a 5-month-old baby, when the baby is full, my baby is excited to...",
                userName = "User",
                expertName = "dr. Nadia Nurotul Fuadah",
                date = "1 day ago",
                userAvatarUrl = "url",
                expertAvatarUrl = "url"
            )
            QuestionItem(
                title = "How to organize a 5 month old baby's feeding schedule?",
                question = "Good afternoon, I'm a mother of a 5-month-old baby, when the baby is full, my baby is excited to Good afternoon, I'm a mother of a 5-month-old baby, when the baby is full, my baby is excited to...",
                userName = "User",
                expertName = "dr. Nadia Nurotul Fuadah",
                date = "1 day ago",
                userAvatarUrl = "url",
                expertAvatarUrl = "url"
            )
        }
    }
}

@Preview
@Composable
fun AskExpertPrev() {
    AskExpertScreen(navController = rememberNavController())
}