package com.killjoy.stuntion.features.presentation.screen.ask_expert_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.QuestionItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.Gray
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun AskExpertDetailScreen(navController: NavController) {

    val viewModel = hiltViewModel<AskExpertDetailViewModel>()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 32.dp)) {

        // Top bar
        item {
            StuntionTopBar(title = "Ask Expert", onBackPressed = { navController.popBackStack() })
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
                    model = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_1.png?alt=media&token=931a191d-6277-4480-b860-fdf8e0e41dfe",
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
                    StuntionText(
                        text = "How to organize a 13 month old baby's feeding schedule?",
                        textStyle = Type.titleMedium()
                    )

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
                            StuntionText(
                                text = "Maya Susanti",
                                textStyle = Type.bodySmall(),
                                color = Gray,
                                maxLine = 1,
                                overflow = TextOverflow.Ellipsis
                            )
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
                            StuntionText(
                                text = "1 day ago",
                                textStyle = Type.bodySmall(),
                                color = Gray
                            )
                        }
                    }
                }
            }

            // Description
            StuntionText(
                text = "Good afternoon, doc, I'm a mother of a 13 month old baby, when I get a baby that's already solids, my baby is excited to want to eat too, and he often tries to pull other people's food too. And in the past I had consulted with the DSA who handled my child, said that my child could already solidify, but I was still afraid and doubtful, after seeing my baby was excited, I want to ask how do I set a baby's feeding schedule for 5 months?",
                textStyle = Type.bodyMedium(),
                modifier = Modifier.padding(16.dp)
            )
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
                        model = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/expert%2Fnational-cancer-institute-byGTytEGjBo-unsplash.jpg?alt=media&token=00d329dc-1360-440d-96ab-acbf727212b2",
                        contentDescription = "User Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {

                        // Expert name
                        StuntionText(
                            text = "dr. Hanna Hanifah",
                            textStyle = Type.titleMedium()
                        )

                        // Expert role
                        Spacer(modifier = Modifier.height(6.dp))
                        StuntionText(
                            text = "Pediatrician - Respirologist",
                            textStyle = Type.bodySmall(),
                            color = Gray
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, end = 8.dp)
                        ) {

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
                                    text = "11 year",
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
                                StuntionText(text = "5", textStyle = Type.bodySmall(), color = Gray)
                            }

                            // Chat Button
                            Spacer(modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 4).dp))
                            StuntionButton(
                                onClick = {

                                },
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
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
            StuntionText(
                text = "Hello, thanks for the question.\n" +
                        "\n" +
                        "Ideally, new babies are given MPASI when they are even 6 months old. Because, at this age, the nutritional content in breast milk is considered less than optimal in supporting the activities and growth and development of the baby, so it needs to be supplemented with complementary foods. However, there are also some conditions where babies can be given solids earlier , for example if their body weight is minimal, if milk production is no longer sufficient, and various other reasons. However, for conditions like this, make sure that early MPASI is given on the recommendation of a doctor, OK?\n" +
                        "\n" +
                        "If your baby is currently 5 months old and it is recommended to start introducing complementary foods, you can give them with a frequency of 1-2 times a day. The time can be adjusted according to your baby's activities, for example in the morning and evening (if given 2 times a day), during the day (if given 1 time a day). What needs to be considered, you should not give solids to the baby too close to the schedule for preparing him or if he is too sleepy. Also make sure that the texture, nutritional content, taste, and volume of giving complementary foods are in accordance with their abilities and needs.\n" +
                        "\n" +
                        "Again, in order to be given a proper explanation, consult directly with the nearest doctor or pediatrician .\n" +
                        "\n" +
                        "I hope this helps.",
                textStyle = Type.bodyMedium(),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        // Other Question
        item {
            Spacer(modifier = Modifier.height(32.dp))
            StuntionText(
                text = "Other Question",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AskExpertPreview() {
    AskExpertDetailScreen(navController = rememberNavController())
}