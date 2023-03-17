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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionListResponse
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun QuestionItem(
    modifier: Modifier = Modifier,
    question: QuestionListResponse,
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
                    model = question.userAvatarUrl,
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(64.dp)
                )
                if (!question.expertAvatarUrl.isNullOrEmpty())
                    AsyncImage(
                        model = question.expertAvatarUrl,
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
                StuntionText(text = question.title, textStyle = Type.titleSmall())

                // User's name
                Spacer(modifier = Modifier.height(2.dp))
                StuntionText(
                    text = "By: ${question.userName}",
                    textStyle = Type.bodySmall(),
                    maxLine = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Expert's name
                if (!question.expertName.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    StuntionText(
                        text = "Answered by ${question.expertName}",
                        textStyle = Type.bodySmall(),
                        color = PrimaryBlue,
                        maxLine = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        // Question
        Spacer(modifier = Modifier.height(2.dp))
        StuntionText(
            text = question.question,
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
                    text = question.timestamp,
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