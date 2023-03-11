package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleListResponse
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.Gray
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: ArticleListResponse,
    onClick: () -> Unit,
) {
    Column(modifier = modifier.clickable { onClick() }) {
        Row(Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            // Image
            Box {
                AsyncImage(
                    model = article.thumbnailUrl,
                    contentDescription = "Article item",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(80.dp)
                        .height(98.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                AsyncImage(
                    model = R.drawable.ic_button_play,
                    contentDescription = "Play icon",
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center)
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
                    items(article.categories) {
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier
                                .wrapContentWidth()
                                .background(color = LightBlue, shape = RoundedCornerShape(16.dp))
                        ) {
                            StuntionText(
                                text = it,
                                textStyle = Type.labelMedium(),
                                color = PrimaryBlue,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                // Title
                StuntionText(
                    text = article.title,
                    textStyle = Type.titleSmall(),
                    maxLine = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                // Description
                StuntionText(
                    text = article.description,
                    maxLine = 2,
                    overflow = TextOverflow.Ellipsis,
                    textStyle = Type.bodyMedium()
                )
            }
        }

        // Divider
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun HomeArticleItem(
    modifier: Modifier = Modifier,
    article: ArticleListResponse,
    onClick: () -> Unit,
) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .width(280.dp)
            .height(200.dp)
            .clickable { onClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Box {
                AsyncImage(
                    model = article.thumbnailUrl,
                    contentDescription = "Article image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(98.dp)
                )
                AsyncImage(
                    model = R.drawable.ic_button_play,
                    contentDescription = "Play icon",
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center)
                )
            }


            // Title
            StuntionText(
                text = article.title,
                maxLine = 2,
                overflow = TextOverflow.Ellipsis,
                textStyle = Type.titleSmall(),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Category
            LazyRow {
                items(article.categories) {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .background(
                                color = LightBlue,
                                shape = RoundedCornerShape(100.dp)
                            )
                    ) {
                        StuntionText(
                            text = it,
                            textStyle = Type.labelMedium(),
                            color = PrimaryBlue,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                        )
                    }
                }
            }


            // Timestamp
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    AsyncImage(
                        model = R.drawable.ic_clock,
                        contentDescription = "Clock icon",
                        modifier = Modifier.size(16.dp)
                    )
                    StuntionText(
                        text = article.timestamp,
                        textStyle = Type.bodySmall(),
                        color = Gray
                    )
                }
                StuntionText(
                    text = "Read more",
                    textStyle = Type.labelMedium(),
                    color = PrimaryBlue
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleItemPreview() {
}