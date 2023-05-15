package com.killjoy.stuntion.features.presentation.screen.check

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun CheckTutorialScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = Color.White, darkIcons = true)
        setNavigationBarColor(color = Color.White, darkIcons = true)
    }

    val steps = remember {
        listOf(
            "Prepare the necessary tools such as a measuring tape, a baby scale, and the child's health book.",
            "Make sure the child is naked or wearing thin clothing.",
            "Weigh the child using an accurate baby scale and record it in the child's health book.",
            "Measure the child's height or weight using a measuring tape. Place the tape flat on top of the child's head and make sure the child is standing straight and looking straight ahead. Read the measurement result and record it in the child's health book.",
            "Calculate the child's height-for-age z-score (HAZ) or weight-for-age z-score (LAZ) using the appropriate formula for the child's age and sex. The result will indicate whether the child is stunted or not.",
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        StuntionTopBar(
            title = "How To Use StunCheck",
            onBackPressed = { navController.popBackStack() },
            isWithDivider = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
        ) {
            // Tutorial
            StuntionText(
                text = "Stunting is a condition of impaired growth caused by chronic undernutrition during childhood. To measure stunting, anthropometric parameters such as height or weight are used. Here are the steps to measure stunting:\n",
                textStyle = Type.bodyMedium()
            )
            steps.forEachIndexed { index, step ->
                Row {
                    StuntionText(text = "${index + 1}. ", textStyle = Type.bodyMedium())
                    StuntionText(text = step)
                }
            }

            StuntionText(
                text = "\nTo interpret the z-score measurement result, WHO standards are used as follows:",
                textStyle = Type.bodyMedium()
            )
            Column(modifier = Modifier.padding(start = 12.dp)) {
                StuntionText(text = "•  Z-score ≤ -2: the child is stunted")
                StuntionText(text = "•  Z-score > -2: the child is not stunted")
            }

            StuntionText(
                text = "\nStunting measurement can be done periodically to monitor the child's growth and determine whether the child's nutritional status is improving or declining. In addition, pay attention to the child's nutritional intake and health status to prevent stunting.",
                textStyle = Type.bodyMedium()
            )
        }
    }
}

@Preview
@Composable
fun TutorialPreview() {
    CheckTutorialScreen(navController = rememberNavController())
}