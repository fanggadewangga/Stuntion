package com.killjoy.stuntion.features.presentation.screen.chat_expert

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.ExpertCategoryItem
import com.killjoy.stuntion.features.presentation.utils.components.ExpertChatItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionSearchField
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun ChatExpertsScreen(navController: NavController) {

    val viewModel = hiltViewModel<ChatExpertViewModel>()

    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
            .verticalScroll(rememberScrollState())
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
        ExpertChatItem(
            name = "dr. A. Roni Naning, Sp.A (K)",
            category = "Pediatrician - Respirologist",
            experience = 11,
            rating = 5.0,
            fee = 40000.0,
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/expert%2Fusman-yousaf-pTrhfmj2jDA-unsplash.jpg?alt=media&token=23212350-188e-4555-92ac-34056c48ad26",
            onExpertClicked = { navController.navigate(Screen.ExpertDetailScreen.route) },
            onChatClicked = { navController.navigate(Screen.ExpertDetailScreen.route) },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ExpertChatItem(
            name = "dr. Dimple Gobind Nagrani",
            category = "Pediatrician",
            experience = 6,
            rating = 4.7,
            fee = 55000.0,
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/expert%2Fhumberto-chavez-FVh_yqLR9eA-unsplash.jpg?alt=media&token=34ed3ac3-75dc-44dc-b2e7-6644328da3d2",
            onExpertClicked = { navController.navigate(Screen.ExpertDetailScreen.route) },
            onChatClicked = { navController.navigate(Screen.ChatRoomScreen.route) },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ExpertChatItem(
            name = "Nia Anida Afranida, S.Keb",
            category = "Midwife",
            experience = 4,
            rating = 4.5,
            fee = 35000.0,
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/expert%2Frian-ramirez-rm7rZYdl3rY-unsplash.jpg?alt=media&token=64ccecca-da2d-4265-addb-15e3c0584893",
            onExpertClicked = { navController.navigate(Screen.ExpertDetailScreen.route) },
            onChatClicked = { navController.navigate(Screen.ChatRoomScreen.route) },
            modifier = Modifier.padding(bottom = 8.dp)
        )




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

        // Nutritionist
        Spacer(modifier = Modifier.height(6.dp))
        ExpertCategoryItem(
            category = "Nutritionist",
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        )

        // General Practitioner
        ExpertCategoryItem(
            category = "General Practitioner",
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        )

        // Midwife
        ExpertCategoryItem(
            category = "Midwife",
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        )

        // Pediatrician
        ExpertCategoryItem(
            category = "Pediatrician",
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        )

        // Obgyn
        ExpertCategoryItem(
            category = "Obgyn",
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        )

        // Obstetricians
        ExpertCategoryItem(
            category = "Obstetricians",
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatExpertScreenPrev() {
    ChatExpertsScreen(navController = rememberNavController())
}