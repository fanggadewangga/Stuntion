package com.killjoy.stuntion.features.presentation.screen.child_profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.domain.model.child.Child
import com.killjoy.stuntion.features.presentation.utils.*
import com.killjoy.stuntion.features.presentation.utils.components.*
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import es.dmoral.toasty.Toasty

@Composable
fun ChildProfileScreen(navController: NavController, child: Child) {
    val context = LocalContext.current
    val viewModel = hiltViewModel<ChildProfileViewModel>()
    val postNoteResponse = viewModel.postNoteResponse.collectAsState()
    val taskResponse = viewModel.fetchTaskResponse.collectAsState()

    LaunchedEffect(postNoteResponse.value) {
        when (postNoteResponse.value) {
            is Resource.Loading -> {}
            is Resource.Error -> Toasty.error(context, postNoteResponse.value.message.toString(), Toast.LENGTH_SHORT).show()
            is Resource.Empty -> {}
            is Resource.Success -> {
                Toasty.success(context, "Successfully added child note!", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.CheckScreen.route) {
                    popUpTo(Screen.ChildProfileScreen.route) {
                        inclusive = true
                    }
                }
            }
        }

        viewModel.apply {
            fetchTask()

            ageInYear.value = countPeriod(child.birthDate)
            ageInMonth.value = countPeriod(child.birthDate, showMonth = true)
            ageInDay.value = countPeriod(child.birthDate, showDay = true)
            idealWeight.value = countIdealWeight(child.birthDate)
            idealHeight.value = countIdealHeight(birthDate = child.birthDate, gender = child.gender)
            heightDescription.value = countZScoreByHeight(
                context = context,
                birthDate = child.birthDate,
                gender = child.gender,
                height = child.height
            )
            weightDescription.value = countZScoreByWeight(
                context = context,
                birthDate = child.birthDate,
                gender = child.gender,
                weight = child.weight
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 24.dp)
    ) {
        StuntionTopBar(
            title = "Child Profile",
            onBackPressed = { navController.popBackStack() }
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            // Child name
            Spacer(modifier = Modifier.height(8.dp))
            StuntionText(text = "Child Name", textStyle = Type.bodyLarge(), color = Color.Gray)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = LightBlue, shape = RoundedCornerShape(100.dp))
            ) {
                StuntionText(
                    text = child.name,
                    textStyle = Type.titleMedium(),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)
                )
            }

            // Gender
            StuntionText(text = "Gender", textStyle = Type.bodyLarge(), color = Color.Gray)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = LightBlue, shape = RoundedCornerShape(100.dp))
            ) {
                StuntionText(
                    text = child.gender,
                    textStyle = Type.titleMedium(),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)
                )
            }

            // Age
            StuntionText(text = "Age", textStyle = Type.bodyLarge(), color = Color.Gray)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = LightBlue, shape = RoundedCornerShape(100.dp))
            ) {
                Row {
                    StuntionText(
                        text = "${(viewModel.ageInYear.value * 12 + viewModel.ageInMonth.value)} months",
                        textStyle = Type.titleMedium(),
                        modifier = Modifier.padding(start = 16.dp, top = 14.dp, bottom = 14.dp)
                    )
                    StuntionText(
                        text = "(${viewModel.ageInYear.value} years ${viewModel.ageInMonth.value} months ${viewModel.ageInDay.value} days)",
                        textStyle = Type.bodyLarge(),
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 12.dp, top = 14.dp, bottom = 14.dp)
                    )
                }
            }

            // Height and Weight
            Spacer(modifier = Modifier.height(2.dp))

            // Height
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                StuntionText(
                    text = "Height (cm)",
                    textStyle = Type.bodyLarge(),
                    color = Color.Gray
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = LightBlue, shape = RoundedCornerShape(100.dp))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        StuntionText(
                            text = "${child.height} cm",
                            textStyle = Type.titleMedium(),
                            modifier = Modifier.padding(
                                start = 16.dp,
                                top = 14.dp,
                                bottom = 14.dp
                            )
                        )
                        StuntionText(
                            text = "(${viewModel.heightDescription.value})",
                            textStyle = Type.bodyLarge(),
                            color = Color.Gray,
                            modifier = Modifier.padding(
                                start = 6.dp,
                                top = 14.dp,
                                bottom = 14.dp
                            )
                        )
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                // Weight
                StuntionText(
                    text = "Weight (kg)",
                    textStyle = Type.bodyLarge(),
                    color = Color.Gray
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = LightBlue, shape = RoundedCornerShape(100.dp))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        StuntionText(
                            text = "${child.weight} kg",
                            textStyle = Type.titleMedium(),
                            modifier = Modifier.padding(
                                start = 12.dp,
                                top = 14.dp,
                                bottom = 14.dp
                            )
                        )
                        StuntionText(
                            text = "(${viewModel.weightDescription.value})",
                            textStyle = Type.bodyLarge(),
                            color = Color.Gray,
                            modifier = Modifier.padding(
                                start = 6.dp,
                                top = 14.dp,
                                bottom = 14.dp
                            )
                        )
                    }
                }
            }

            // Buttons
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Retry
                StuntionButton(
                    backgroundColor = Color.White,
                    borderColor = PrimaryBlue,
                    rippleColor = PrimaryBlue,
                    borderWidth = 1.dp,
                    contentPadding = PaddingValues(vertical = 12.dp),
                    onClick = {
                        navController.navigate(Screen.CheckScreen.route) {
                            popUpTo(Screen.ChildProfileScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.44).dp)
                ) {
                    StuntionText(
                        text = "Retry",
                        textStyle = Type.labelLarge(),
                        color = PrimaryBlue,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }

                // Save
                StuntionButton(
                    backgroundColor = PrimaryBlue,
                    contentPadding = PaddingValues(vertical = 12.dp),
                    onClick = {
                        viewModel.postNewNote(
                            childName = child.name,
                            gender = child.gender,
                            height = child.height,
                            weight = child.weight,
                            birthday = child.birthDate
                        )
                    },
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.44).dp)
                ) {
                    StuntionText(
                        text = "Save",
                        textStyle = Type.labelLarge(),
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }

            // Text
            Row {
                StuntionText(
                    text = "* ",
                    textStyle = Type.bodySmall(),
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 3.dp)
                )
                StuntionText(
                    text = "By save, healthy tips that you have got and data that you have entered will be stored in Child Notes",
                    textStyle = Type.bodySmall(),
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 24.dp)
                )
            }

            // Notes
            ChildProfileSectionItem(
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_notes),
                        contentDescription = "Notes icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                title = "Notes",
                visibleContent = {

                },
                invisibleContent = {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            StuntionText(text = "Ideal height ", textStyle = Type.titleSmall())
                            StuntionText(text = "around ", textStyle = Type.bodyMedium())
                            StuntionText(
                                text = "${viewModel.idealHeight.value} cm",
                                textStyle = Type.titleSmall()
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            StuntionText(text = "Ideal weight ", textStyle = Type.titleSmall())
                            StuntionText(text = "around ", textStyle = Type.bodyMedium())
                            StuntionText(
                                text = "${viewModel.idealWeight.value} kg",
                                textStyle = Type.titleSmall()
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // Nutritional
            ChildProfileSectionItem(
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_nutritional),
                        contentDescription = "Nutritional icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                title = "Nutritional",
                visibleContent = {

                },
                invisibleContent = {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        StuntionText(text = "ASI", textStyle = Type.bodyMedium())
                        StuntionText(text = "Family Meal", textStyle = Type.bodyMedium())
                        StuntionText(
                            text = "Chopped or pureed food if needed",
                            textStyle = Type.bodyMedium()
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            // Healthy tips
            ChildProfileSectionItem(
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = "Child icon",
                        colorFilter = ColorFilter.tint(PrimaryBlue),
                        modifier = Modifier.size(24.dp)
                    )
                },
                title = "Healthy Tips",
                visibleContent = {

                },
                invisibleContent = {
                    when (taskResponse.value) {
                        is Resource.Loading -> {
                            Log.d("FETCH TASK", "LOADING")
                            for (i in 1..5) {
                                HealthyTipsItemShimmer()
                            }
                        }
                        is Resource.Error -> Log.d(
                            "FETCH TASK",
                            taskResponse.value.message.toString()
                        )
                        is Resource.Empty -> Log.d(
                            "FETCH TASK",
                            taskResponse.value.message.toString()
                        )
                        is Resource.Success -> {
                            Log.d("FETCH TASK", "SUCCESS")
                            Column {
                                val taskList = taskResponse.value.data!!.take(5)
                                taskList.forEach {
                                    HealthyTipsItem(
                                        tips = it,
                                        onClick = {
                                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                                key = "taskId",
                                                value = it.taskId
                                            )
                                            navController.navigate(Screen.HealthyTipsDetailScreen.route)
                                        },
                                    )
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            // Complete all task
            StuntionText(
                text = "* Complete all tasks to get lots of rewards",
                textStyle = Type.bodySmall(),
                color = Color.Gray,
                modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
            )
        }
    }
}

