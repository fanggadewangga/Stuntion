package com.killjoy.stuntion.features.presentation.screen.child_notes.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.ChildProfileSectionItem
import com.killjoy.stuntion.features.presentation.utils.components.ErrorLayout
import com.killjoy.stuntion.features.presentation.utils.components.HealthyTipsItem
import com.killjoy.stuntion.features.presentation.utils.components.HealthyTipsItemShimmer
import com.killjoy.stuntion.features.presentation.utils.components.LoadingAnimation
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun ChildNotesDetailScreen(navController: NavController, noteId: String) {
    val viewModel = hiltViewModel<ChildNotesDetailViewModel>()
    val noteResponse = viewModel.noteResponse.collectAsStateWithLifecycle()
    val taskResponse = viewModel.fetchTaskResponse.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.fetchNoteDetail(noteId)
    }

    when (noteResponse.value) {
        is Resource.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                LoadingAnimation(
                    circleSize = 16.dp,
                    spaceBetweenCircle = 10.dp,
                    travelDistance = 24.dp
                )
            }
        }

        is Resource.Empty -> {
            Log.d("FETCH NOTE DETAIL", "Empty")
            ErrorLayout(errorMessage = noteResponse.value.message.toString())
        }

        is Resource.Error -> {
            Log.d("FETCH NOTE DETAIL", "Error")
            ErrorLayout(errorMessage = noteResponse.value.message.toString())
        }

        is Resource.Success -> {
            Log.d("FETCH NOTE DETAIL", "Success")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 24.dp)
            ) {
                StuntionTopBar(
                    title = "Child Profile",
                    onBackPressed = { navController.navigate(Screen.CheckScreen.route) }
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {

                    // Child name
                    Spacer(modifier = Modifier.height(8.dp))
                    StuntionText(
                        text = "Child Name",
                        textStyle = Type.bodyLarge(),
                        color = Color.Gray
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = LightBlue, shape = RoundedCornerShape(100.dp))
                    ) {
                        StuntionText(
                            text = noteResponse.value.data!!.childName,
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
                            text = noteResponse.value.data!!.gender,
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
                                text = "${(noteResponse.value.data!!.ageYear * 12 + noteResponse.value.data!!.ageMonth)} months",
                                textStyle = Type.titleMedium(),
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    top = 14.dp,
                                    bottom = 14.dp
                                )
                            )
                            StuntionText(
                                text = "(${noteResponse.value.data!!.ageYear} years ${noteResponse.value.data!!.ageMonth} months ${noteResponse.value.data!!.ageDay} days)",
                                textStyle = Type.bodyLarge(),
                                color = Color.Gray,
                                modifier = Modifier.padding(
                                    start = 12.dp,
                                    top = 14.dp,
                                    bottom = 14.dp
                                )
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
                                    text = "${noteResponse.value.data!!.height} cm",
                                    textStyle = Type.titleMedium(),
                                    modifier = Modifier.padding(
                                        start = 16.dp,
                                        top = 14.dp,
                                        bottom = 14.dp
                                    )
                                )
                                StuntionText(
                                    text = "(${noteResponse.value.data!!.heightDescription})",
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
                                    text = "${noteResponse.value.data!!.weight} kg",
                                    textStyle = Type.titleMedium(),
                                    modifier = Modifier.padding(
                                        start = 12.dp,
                                        top = 14.dp,
                                        bottom = 14.dp
                                    )
                                )
                                StuntionText(
                                    text = "(${noteResponse.value.data!!.weightDescription})",
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

                    // Text
                    Spacer(modifier = Modifier.height(8.dp))
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
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    StuntionText(
                                        text = "Ideal height ",
                                        textStyle = Type.titleSmall()
                                    )
                                    StuntionText(text = "around ", textStyle = Type.bodyMedium())
                                    StuntionText(
                                        text = "${noteResponse.value.data!!.idealHeight}",
                                        textStyle = Type.titleSmall()
                                    )
                                }
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    StuntionText(
                                        text = "Ideal weight ",
                                        textStyle = Type.titleSmall()
                                    )
                                    StuntionText(text = "around ", textStyle = Type.bodyMedium())
                                    StuntionText(
                                        text = "${noteResponse.value.data!!.idealWeight} kg",
                                        textStyle = Type.titleSmall()
                                    )
                                }
                            }
                        },
                        invisibleContent = {

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
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                StuntionText(text = "ASI", textStyle = Type.bodyMedium())
                                StuntionText(text = "Family Meal", textStyle = Type.bodyMedium())
                                StuntionText(
                                    text = "Chopped or pureed food if needed",
                                    textStyle = Type.bodyMedium()
                                )
                            }
                        },
                        invisibleContent = {

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
                            Column {

                            }
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
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        }
    }

}