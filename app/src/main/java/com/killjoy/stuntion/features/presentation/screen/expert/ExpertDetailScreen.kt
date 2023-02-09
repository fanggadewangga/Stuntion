package com.killjoy.stuntion.features.presentation.screen.expert

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun ExpertDetailScreen(navController: NavController) {
    val viewModel = hiltViewModel<ExpertDetailViewModel>()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        // Image
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = R.drawable.iv_expert_detail,
                contentDescription = "Expert image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.height(280.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Image(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back icon",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.TopStart)
                        .clickable {

                        }
                )
                Image(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share icon",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .align(Alignment.TopEnd)
                        .clickable { }
                )
            }

            // Online status
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(
                            color = LightBlue,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    AsyncImage(
                        model = R.drawable.ic_green_circle,
                        contentDescription = "Green circle icon",
                        modifier = Modifier.size(9.dp)
                    )
                    StuntionText(
                        text = "Online",
                        textStyle = Type.labelSmall(),
                        color = Color.Green
                    )
                }
            }
        }

        // Name
        Spacer(modifier = Modifier.height(24.dp))
        StuntionText(text = "dr. A. Roni Naning, Sp.A (K)", textStyle = Type.titleMedium())

        // Role
        Spacer(modifier = Modifier.height(8.dp))
        StuntionText(
            text = "Pediatrician - Respirologist",
            textStyle = Type.bodyLarge(),
            color = Color.Gray
        )

        // Rating and experience
        Spacer(modifier = Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_star,
                    contentDescription = "Star icon",
                    modifier = Modifier.size(38.dp)
                )
                Column {
                    StuntionText(text = "5.0 Out of 5.0", textStyle = Type.titleSmall())
                    StuntionText(text = "500 Patiens review", textStyle = Type.bodySmall())
                }
            }

            // Experience
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_bag,
                    contentDescription = "Star icon",
                    modifier = Modifier.size(38.dp)
                )
                Column {
                    StuntionText(text = "11 Year", textStyle = Type.titleSmall())
                    StuntionText(text = "Experience", textStyle = Type.bodySmall())
                }
            }
        }

        // STR
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    AsyncImage(
                        model = R.drawable.ic_garuda,
                        contentDescription = "Garuda icon",
                        modifier = Modifier.size(24.dp)
                    )
                    StuntionText(text = "STR Number", textStyle = Type.titleMedium())
                }
                StuntionText(text = "7231234567890987", textStyle = Type.bodyMedium())
            }
        }

        // Education
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(16.dp)
            ) {

                // Title
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_education,
                            contentDescription = "Garuda icon",
                            modifier = Modifier.size(24.dp)
                        )
                        StuntionText(text = "Education", textStyle = Type.titleMedium())
                    }

                    Image(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Arrow down icon",
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterEnd)
                    )
                }

                // Value
                Box(modifier = Modifier.fillMaxWidth()) {
                    StuntionText(text = "University of Indonesia", textStyle = Type.bodyMedium())
                    StuntionText(
                        text = "2018", textStyle = Type.bodyMedium(), modifier = Modifier.align(
                            Alignment.CenterEnd
                        )
                    )
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    StuntionText(text = "Brawijaya University", textStyle = Type.bodyMedium())
                    StuntionText(
                        text = "2012", textStyle = Type.bodyMedium(), modifier = Modifier.align(
                            Alignment.CenterEnd
                        )
                    )
                }
            }
        }

        // Workplace
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(16.dp)
            ) {

                // Title
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_workplace,
                            contentDescription = "Workplace icon",
                            modifier = Modifier.size(24.dp)
                        )
                        StuntionText(text = "Workplace", textStyle = Type.titleMedium())
                    }

                    Image(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Arrow down icon",
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterEnd)
                    )
                }

                // Value
                StuntionText(
                    text = "Brawijaya University Hospital",
                    textStyle = Type.bodyMedium()
                )
                StuntionText(text = "RSIA Mutiara Bunda", textStyle = Type.bodyMedium())
                StuntionText(
                    text = "Puri Bunda Mother and Child Hospital",
                    textStyle = Type.bodyMedium()
                )
            }
        }
    }
}

@Preview
@Composable
fun ExpertDetailPreview() {
    ExpertDetailScreen(navController = rememberNavController())
}