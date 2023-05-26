package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.theme.Type
import es.dmoral.toasty.Toasty

@Composable
fun ErrorLayout(errorMessage: String = "No Internet Connection") {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Toasty.error(context, errorMessage, Toasty.LENGTH_SHORT).show()
    }

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.iv_error),
            contentDescription = "Error Image"
        )
        Spacer(modifier = Modifier.height(8.dp))
        StuntionText(text = "Oops an Error Occurred, $errorMessage", textStyle = Type.titleLarge(), textAlign = TextAlign.Center)
        StuntionText(text = "Please try again", textStyle = Type.bodyMedium(), color = Color.LightGray)

    }
}

@Preview
@Composable
fun ErrorPreview() {
    ErrorLayout()
}