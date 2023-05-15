package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.source.remote.api.response.note.NoteResponse
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun ChildNotesItem(modifier: Modifier = Modifier, note: NoteResponse, onClick: () -> Unit) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.clickable { onClick() }
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
            ) {
                AsyncImage(
                    model = R.drawable.ic_age,
                    contentDescription = "Age icon",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                // Name
                StuntionText(text = note.childName, textStyle = Type.titleSmall())

                // Date
                StuntionText(
                    text = note.timestamp,
                    textStyle = Type.bodySmall(),
                    color = Color.Gray
                )
            }
        }
    }
}