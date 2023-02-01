package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun ArticleItem() {

    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            // Image
            AsyncImage(
                model = R.drawable.iv_article_item,
                contentDescription = "Article item",
                modifier = Modifier
                    .width(80.dp)
                    .height(98.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))


            // Text
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            ) {

                // Category
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(color = LightBlue, shape = RoundedCornerShape(16.dp))
                ) {
                    StuntionText(
                        text = "Stunting",
                        textStyle = Type.labelMedium(),
                        color = PrimaryBlue,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }

                // Title
                StuntionText(
                    text = "Nutrition to Prevent Stunted Child Growth",
                    textStyle = Type.titleSmall()
                )

                // Description
                StuntionText(
                    text = "Every parent certainly wants their child's growth and development to run Every parent certainly wants their child's growth and development to run ",
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

@Preview(showBackground = true)
@Composable
fun ArticleItemPreview() {
    ArticleItem()
}