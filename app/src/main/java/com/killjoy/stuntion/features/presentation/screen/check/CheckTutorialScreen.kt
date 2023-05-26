package com.killjoy.stuntion.features.presentation.screen.check

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

    val guides = remember {
        listOf(
            "Prepare the necessary tools such as a measuring tape, a baby scale, and the child's health book.",
            "Make sure the child is naked or wearing thin clothing.",
            "Weigh the child using an accurate baby scale and record it in the child's health book.",
            "Measure the child's height or weight using a measuring tape. Place the tape flat on top of the child's head and make sure the child is standing straight and looking straight ahead. Read the measurement result and record it in the child's health book.",
            "Calculate the child's height-for-age z-score (HAZ) or weight-for-age z-score (LAZ) using the appropriate formula for the child's age and sex. The result will indicate whether the child is stunted or not.",
        )
    }

    val checks = remember {
        listOf(
            "Enter the child's name.",
            "Enter the child's gender.",
            "Enter the child's date of birth.",
            "Enter the height (in cm) and weight (in kg) of the child",
            "Then press calculate.",
            "The result of baby's condition will be displayed."
        )
    }

    val measureStep = remember {
        listOf(
            "Baby or child scale with an accurate scale to measure weight.",
            "Baby height measurement tool, such as a baby stadiometer or measuring tape."
        )
    }

    val measuringWeightStep = remember {
        listOf(
            "Place the child gently on the baby or child scale, ensuring that the scale is set to zero.",
            "If the child is still a baby, you can hold the baby in a supine position with your hands around their head and buttocks",
            "Read and record the child's weight displayed on the scale. Make sure to read accurately and record precisely."
        )
    }

    val measuringHeightStep = remember {
        listOf(
            "Place the baby in a supine position on a flat table or surface.",
            "Ensure the baby's head is in the proper position and their legs are straight.",
            "Place the baby stadiometer beside the baby's head and adjust it to be directly above the head.",
            "Read and record the baby's height displayed on the baby stadiometer.",
            "Ask the child to stand straight with their back against a flat wall and mark the child's height on the wall with a pencil.",
            "Use the measuring tape to measure the distance from the floor to the marked height on the wall and record the result."
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp)
    ) {
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
                .verticalScroll(rememberScrollState())
        ) {

            // How to check
            StuntionText(
                text = "How To Check Your Child",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            checks.forEachIndexed { index, check ->
                Row {
                    StuntionText(text = "${index + 1}. ", textStyle = Type.bodyMedium())
                    StuntionText(text = check, textStyle = Type.bodyMedium())
                }
            }

            // How to measure
            StuntionText(
                text = "How to measure the Height and Weight of a Child aged 0 - 5 years old",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(top = 16.dp,bottom = 8.dp)
            )
            StuntionText(text = "Prepare the necessary equipment: ", textStyle = Type.bodyMedium())
            measureStep.forEach { measure ->
                Row(modifier = Modifier.padding(start = 2.dp)) {
                    StuntionText(text = "• ", textStyle = Type.bodyMedium())
                    StuntionText(text = measure, textStyle = Type.bodyMedium())
                }
            }

            // Measuring weight
            Row(modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)) {
                StuntionText(text = "Measuring ", textStyle = Type.bodyMedium())
                StuntionText(text = "Weight ", textStyle = Type.titleSmall())
            }
            measuringWeightStep.forEachIndexed { index, check ->
                Row(modifier = Modifier.padding(start = 2.dp)) {
                    StuntionText(text = "${index + 1}. ", textStyle = Type.bodyMedium())
                    StuntionText(text = check, textStyle = Type.bodyMedium())
                }
            }

            // Measuring height
            Row(modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)) {
                StuntionText(text = "Measuring ", textStyle = Type.bodyMedium())
                StuntionText(text = "Height ", textStyle = Type.titleSmall())
            }
            StuntionText(
                text = "For babies who cannot stand yet, you can use a baby stadiometer:",
                textStyle = Type.bodyMedium(),
            )
            for (i in 0..3) {
                Row {
                    StuntionText(text = "${i + 1}. ", textStyle = Type.bodyMedium())
                    StuntionText(text = measuringHeightStep[i], textStyle = Type.bodyMedium())
                }
            }

            StuntionText(
                text = "If the child can already stand, you can use a measuring tape for height measurement:",
                textStyle = Type.bodyMedium(),
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            for (i in 0..1) {
                Row {
                    StuntionText(text = "${i + 1}. ", textStyle = Type.bodyMedium())
                    StuntionText(text = measuringHeightStep[i + 4], textStyle = Type.bodyMedium())
                }
            }


            // Tutorial
            StuntionText(
                text = "Guide",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            StuntionText(
                text = "Stunting is a condition of impaired growth caused by chronic undernutrition during childhood. To measure stunting, anthropometric parameters such as height or weight are used. Here are the steps to measure stunting:\n",
                textStyle = Type.bodyMedium()
            )
            guides.forEachIndexed { index, step ->
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