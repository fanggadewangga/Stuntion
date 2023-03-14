package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
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
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.source.remote.api.response.note.NoteResponse
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.*

@Composable
fun ArticleItemShimmer(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            // Image
            Box {
                AsyncImage(
                    model = "",
                    contentDescription = "Article item",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(80.dp)
                        .height(98.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }

            Spacer(modifier = Modifier.width(16.dp))


            // Text
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            ) {

                // Category
                LazyRow {
                    items(2) {
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier
                                .wrapContentWidth()
                                .background(color = LightBlue, shape = RoundedCornerShape(16.dp))
                                .placeholder(
                                    visible = true,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(100.dp),
                                    highlight = PlaceholderHighlight
                                        .shimmer(highlightColor = Color.White),
                                )
                        ) {
                            StuntionText(
                                text = "Category",
                                textStyle = Type.labelMedium(),
                                color = PrimaryBlue,
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                                    .placeholder(
                                        visible = true,
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(100.dp),
                                        highlight = PlaceholderHighlight
                                            .shimmer(highlightColor = Color.White),
                                    )
                            )
                        }
                    }
                }

                // Title
                StuntionText(
                    text = "Video Title",
                    textStyle = Type.titleSmall(),
                    modifier = Modifier
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(100.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )

                // Description
                StuntionText(
                    text = "This is video description",
                    maxLine = 2,
                    overflow = TextOverflow.Ellipsis,
                    textStyle = Type.bodyMedium(),
                    modifier = Modifier
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(100.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }
        }
    }
}

@Composable
fun HomeArticleItemShimmer(modifier: Modifier = Modifier) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .width(280.dp)
            .height(200.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Box {
                AsyncImage(
                    model = "",
                    contentDescription = "Article image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(98.dp)
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(16.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }


            // Title
            StuntionText(
                text = "This is video title",
                maxLine = 2,
                overflow = TextOverflow.Ellipsis,
                textStyle = Type.titleSmall(),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(100.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
            )

            // Category
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .background(
                        color = LightBlue,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(100.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
            ) {
                StuntionText(
                    text = "Stunting",
                    textStyle = Type.labelMedium(),
                    color = PrimaryBlue,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(100.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }

            // Timestamp
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    StuntionText(
                        text = "January 31, 2022",
                        textStyle = Type.bodySmall(),
                        color = Gray,
                        modifier = Modifier
                            .placeholder(
                                visible = true,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(100.dp),
                                highlight = PlaceholderHighlight
                                    .shimmer(highlightColor = Color.White),
                            )
                    )
                }
                StuntionText(
                    text = "Read more",
                    textStyle = Type.labelMedium(),
                    color = PrimaryBlue,
                    modifier = Modifier
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(100.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }

        }
    }
}

@Composable
fun AvatarItemShimmer() {
    Surface(
        shape = CircleShape,
        modifier = Modifier
            .wrapContentSize()
    ) {
        AsyncImage(
            model = "",
            contentDescription = "Avatar icon",
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp)
                .clip(CircleShape)
                .placeholder(
                    visible = true,
                    color = Color.LightGray,
                    shape = CircleShape,
                    highlight = PlaceholderHighlight
                        .shimmer(highlightColor = Color.White),
                )
        )
    }
}

@Composable
fun DonationItemShimmer(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {

            // Image
            AsyncImage(
                model = "imageUrl",
                contentDescription = "Donation image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(84.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {

                // Title
                StuntionText(
                    text = "This is donation title",
                    textStyle = Type.titleMedium(),
                    maxLine = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .width((LocalConfiguration.current.screenWidthDp * 0.65).dp)
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(16.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )

                // Location
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    StuntionText(
                        text = "This is donation location",
                        textStyle = Type.bodyMedium(),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .width((LocalConfiguration.current.screenWidthDp * 0.65).dp)
                            .placeholder(
                                visible = true,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(16.dp),
                                highlight = PlaceholderHighlight
                                    .shimmer(highlightColor = Color.White),
                            )
                    )
                }

                // Progress Bar
                LinearProgressIndicator(
                    backgroundColor = Color.LightGray,
                    color = PrimaryBlue,
                    modifier = Modifier
                        .width((LocalConfiguration.current.screenWidthDp * 0.65).dp)
                        .height(8.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(16.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.65).dp)
                ) {
                    // Fee
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        StuntionText(
                            text = "IDR fee",
                            textStyle = Type.titleSmall(),
                            modifier = Modifier
                                .placeholder(
                                    visible = true,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(16.dp),
                                    highlight = PlaceholderHighlight
                                        .shimmer(highlightColor = Color.White),
                                )
                        )
                    }

                    // Deadline
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        StuntionText(
                            text = "This is donation date",
                            textStyle = Type.titleSmall(),
                            modifier = Modifier
                                .placeholder(
                                    visible = true,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(16.dp),
                                    highlight = PlaceholderHighlight
                                        .shimmer(highlightColor = Color.White),
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeDonationItemShimmer(modifier: Modifier = Modifier) {

    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = "imageUrl",
                    contentDescription = "Donation item image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(98.dp)
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(16.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }

            // Title
            StuntionText(
                text = "Donation title",
                textStyle = Type.labelMedium(),
                maxLine = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
            )

            // Location
            StuntionText(
                text = "Donation location",
                textStyle = Type.bodySmall(),
                color = Gray,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
            )

            // Progress Bar
            LinearProgressIndicator(
                backgroundColor = Color.LightGray,
                color = PrimaryBlue,
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp * 0.65).dp)
                    .height(8.dp)
                    .padding(horizontal = 8.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
            )

            // Collected
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
            ) {
                StuntionText(
                    text = "IDR fee",
                    textStyle = Type.labelMedium(),
                    modifier = Modifier.placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
                )
            }
        }
    }
}

@Composable
fun ExpertChatItemShimmer(modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Box {
                // Image
                AsyncImage(
                    model = "avatarUrl",
                    contentDescription = "Expert Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(80.dp)
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = CircleShape,
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                // Name
                StuntionText(
                    text = "Expert name",
                    textStyle = Type.labelLarge(),
                    modifier = Modifier.placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
                )

                // Role
                StuntionText(
                    text = "Expert category",
                    textStyle = Type.bodySmall(),
                    color = LightGray,
                    modifier = Modifier
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(16.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Experience
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        StuntionText(
                            text = "Experience year",
                            textStyle = Type.bodySmall(),
                            modifier = Modifier
                                .placeholder(
                                    visible = true,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(16.dp),
                                    highlight = PlaceholderHighlight
                                        .shimmer(highlightColor = Color.White),
                                )
                        )
                    }

                    // Rating
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .wrapContentWidth()
                            .placeholder(
                                visible = true,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(16.dp),
                                highlight = PlaceholderHighlight
                                    .shimmer(highlightColor = Color.White),
                            )
                    ) {
                        StuntionText(
                            text = "Rating",
                            textStyle = Type.bodySmall(),
                            color = Gray
                        )
                    }
                }

                // Price
                StuntionText(
                    text = "IDR fee",
                    textStyle = Type.titleSmall(),
                    color = PrimaryBlue,
                    modifier = Modifier
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(16.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }

            // Button chat
            Spacer(modifier = Modifier.width(24.dp))
            StuntionButton(
                onClick = {

                },
                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 40.dp)
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
            ) {
                StuntionText(
                    text = "Chat",
                    textStyle = Type.labelLarge(),
                    color = Color.White,
                    modifier = Modifier
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(16.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }
        }
    }
}

@Composable
fun QuestionItemShimmer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            // Image
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(72.dp)
            ) {
                AsyncImage(
                    model = "userAvatarUrl",
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(64.dp)
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = CircleShape,
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }

            Column {
                // Title
                StuntionText(
                    text = "Question title",
                    textStyle = Type.titleSmall(),
                    modifier = Modifier.placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
                )

                // User's name
                Spacer(modifier = Modifier.height(2.dp))
                StuntionText(
                    text = "By: userName",
                    modifier = Modifier
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(16.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )

                // Expert's name
                Spacer(modifier = Modifier.height(2.dp))
                StuntionText(
                    text = "Answered by expertName",
                    modifier = Modifier.placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
                )
            }
        }

        // Question
        Spacer(modifier = Modifier.height(2.dp))
        StuntionText(
            text = "question",
            modifier = Modifier.placeholder(
                visible = true,
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp),
                highlight = PlaceholderHighlight
                    .shimmer(highlightColor = Color.White),
            )
        )

        // Date
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            contentAlignment = Alignment.CenterEnd, modifier = Modifier
                .fillMaxWidth()
                .placeholder(
                    visible = true,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp),
                    highlight = PlaceholderHighlight
                        .shimmer(highlightColor = Color.White),
                )
        ) {
            Row {
                Spacer(modifier = Modifier.width(4.dp))
                StuntionText(
                    text = "Question date",
                    textStyle = Type.bodySmall(),
                    color = LightGray
                )
            }
        }
    }
}

@Composable
fun ChildNotesItemShimmer(modifier: Modifier = Modifier) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            // Icon
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .background(color = LightBlue, shape = RoundedCornerShape(8.dp))
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
            ) {
                AsyncImage(
                    model = R.drawable.ic_age,
                    contentDescription = "Age icon",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(16.dp),
                            highlight = PlaceholderHighlight
                                .shimmer(highlightColor = Color.White),
                        )
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                // Name
                StuntionText(
                    text = "Child name",
                    textStyle = Type.titleSmall(),
                    modifier = Modifier.placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
                )

                // Date
                StuntionText(
                    text = "Notes timestamp",
                    textStyle = Type.bodySmall(),
                    color = Color.Gray,
                    modifier = Modifier.placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(16.dp),
                        highlight = PlaceholderHighlight
                            .shimmer(highlightColor = Color.White),
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun ShimmerPreview() {
    QuestionItemShimmer()
}