package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun QuestionItem(
    modifier: Modifier = Modifier,
    title: String,
    question: String,
    userName: String,
    expertName: String,
    date: String,
    userAvatarUrl: String,
    expertAvatarUrl: String,
    onClick: () -> Unit,
) {
    Column(modifier = modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        .clickable { onClick() }
    ) {
        Row(Modifier.fillMaxWidth()) {
            // Image
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(72.dp)
            ) {
                AsyncImage(
                    model = userAvatarUrl,
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(64.dp)
                )
                AsyncImage(
                    model = expertAvatarUrl,
                    contentDescription = "Expert Avatar",
                    modifier = Modifier
                        .border(width = 4.dp, color = Color.White, shape = CircleShape)
                        .clip(CircleShape)
                        .size(40.dp)
                        .align(Alignment.BottomEnd)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                // Title
                StuntionText(text = title, textStyle = Type.titleSmall())

                // User's name
                Spacer(modifier = Modifier.height(2.dp))
                StuntionText(
                    text = "By: $userName",
                    textStyle = Type.bodySmall(),
                    maxLine = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Expert's name
                Spacer(modifier = Modifier.height(2.dp))
                StuntionText(
                    text = "Answered by $expertName",
                    textStyle = Type.bodySmall(),
                    color = PrimaryBlue,
                    maxLine = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // Question
        Spacer(modifier = Modifier.height(2.dp))
        StuntionText(
            text = question,
            textStyle = Type.bodyMedium(),
            maxLine = 2,
            overflow = TextOverflow.Ellipsis
        )

        // Date
        Spacer(modifier = Modifier.height(6.dp))
        Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "Clock icon"
                )
                Spacer(modifier = Modifier.width(4.dp))
                StuntionText(
                    text = date,
                    textStyle = Type.bodySmall(),
                    color = LightGray
                )
            }
        }

        // Divider
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
fun QuestionItemPreview() {
    QuestionItem(
        title = "How to organize a 5 month old baby's feeding schedule?",
        question = "Good afternoon, I'm a mother of a 5-month-old baby, when the baby is full, my baby is excited to Good afternoon, I'm a mother of a 5-month-old baby, when the baby is full, my baby is excited to...",
        userName = "User",
        expertName = "dr. Nadia Nurotul Fuadah",
        date = "1 day ago",
        userAvatarUrl = "url",
        expertAvatarUrl = "url",
        onClick = {}
    )
}