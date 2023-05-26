package com.killjoy.stuntion.features.presentation.screen.maintenance

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.Type
import es.dmoral.toasty.Toasty

@Composable
fun MaintenanceScreen() {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Toasty.error(context, "Please try again next time", Toasty.LENGTH_SHORT).show()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.iv_maintenance),
            contentDescription = "Maintenance Image"
        )
        Spacer(modifier = Modifier.height(8.dp))
        StuntionText(
            text = "Apologies, currently undergoing maintenance",
            textStyle = Type.titleLarge(),
            textAlign = TextAlign.Center
        )
    }
}