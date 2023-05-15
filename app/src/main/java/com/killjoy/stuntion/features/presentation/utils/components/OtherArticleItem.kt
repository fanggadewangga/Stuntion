package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleListResponse
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun OtherArticleItem(
    modifier: Modifier = Modifier,
    video: ArticleListResponse,
    onClick: () -> Unit,
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .clickable {
                onClick()
            }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Image
            AsyncImage(
                model = video.thumbnailUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = "Article image",
                modifier = Modifier.height(80.dp)
            )

            // Category
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                video.categories.take(2).forEach {
                    Box(
                        contentAlignment = Alignment.TopCenter,
                        modifier = Modifier
                            .wrapContentWidth()
                            .background(color = LightBlue, shape = RoundedCornerShape(16.dp))
                            .padding(horizontal = 2.dp)
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
                text = video.title,
                textStyle = Type.titleSmall(),
                maxLine = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )

            // Description
            StuntionText(
                text = video.description,
                textStyle = Type.labelSmall(),
                maxLine = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 16.dp)
            )
        }
    }
}