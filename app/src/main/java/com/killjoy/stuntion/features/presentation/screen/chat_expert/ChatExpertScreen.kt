package com.killjoy.stuntion.features.presentation.screen.chat_expert

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.features.presentation.utils.components.ExpertChatItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionSearchField
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun ChatExpertsScreen(navController: NavController) {

    val viewModel = hiltViewModel<ChatExpertViewModel>()
    val listOfExpertCategory = viewModel.listOfExpertCategory

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
            placeholder = "Find an expert",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                StuntionText(text = "Expert Recommendation", textStyle = Type.titleMedium())
                StuntionText(
                    text = "Online consultation with our standby experts",
                    textStyle = Type.bodySmall(),
                    color = Color.LightGray
                )
            }

            StuntionText(
                text = "View All",
                textStyle = Type.labelMedium(),
                color = PrimaryBlue,
                modifier = Modifier.clickable {

                }
            )
        }

        // Experts recommendation
        Spacer(modifier = Modifier.height(16.dp))
        ExpertChatItem(modifier = Modifier.padding(bottom = 8.dp))
        ExpertChatItem(modifier = Modifier.padding(bottom = 8.dp))
        ExpertChatItem(modifier = Modifier.padding(bottom = 8.dp))


        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                StuntionText(text = "Find our expert", textStyle = Type.titleMedium())
                StuntionText(
                    text = "Choose a category according to your needs",
                    textStyle = Type.bodySmall(),
                    color = Color.Gray
                )
            }

            StuntionText(
                text = "View All",
                textStyle = Type.labelMedium(),
                color = PrimaryBlue,
                modifier = Modifier.clickable {

                }
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(items = listOfExpertCategory) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = it.image,
                            contentDescription = "Category icon",
                            modifier = Modifier
                                .size(88.dp)
                                .clip(
                                    CircleShape
                                )

                        )
                        StuntionText(text = it.title, textStyle = Type.bodyMedium())
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatExpertScreenPrev() {
    ChatExpertsScreen(navController = rememberNavController())
}