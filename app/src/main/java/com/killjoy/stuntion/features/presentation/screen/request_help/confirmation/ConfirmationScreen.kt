package com.killjoy.stuntion.features.presentation.screen.request_help.confirmation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.screen.request_help.RequestHelpViewModel
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun ConfirmationScreen() {

    val viewModel = hiltViewModel<RequestHelpViewModel>()

    Column {
        // Indicator
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        )
        {
            Divider(
                thickness = 1.dp,
                color = PrimaryBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        end = (LocalConfiguration.current.screenWidthDp / 8).dp,
                        bottom = 16.dp
                    )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.listOfStep.forEachIndexed { index, step ->
                    val currentStepIndex = index + 2
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .border(width = 0.5.dp, color = PrimaryBlue, shape = CircleShape)
                                .background(color = PrimaryBlue, shape = CircleShape)
                        ) {
                            StuntionText(
                                text = currentStepIndex.toString(),
                                textStyle = Type.titleMedium(),
                                color = Color.White
                            )
                        }
                        StuntionText(
                            text = step,
                            textStyle = Type.labelMedium(),
                            color = PrimaryBlue,
                        )
                    }
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            StuntionText(
                text = "Confirmation",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(top = 32.dp)
            )
            StuntionText(
                text = "Make sure the data you enter is correct so that the process can run quickly",
                textStyle = Type.bodyMedium()
            )

            // 1st checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Checkbox(
                    checked = viewModel.listOfChecked.contains(viewModel.listOfCheck[0]),
                    onCheckedChange = {
                        if (viewModel.listOfChecked.contains(viewModel.listOfCheck[0]))
                            viewModel.apply {
                                listOfChecked.remove(viewModel.listOfCheck[0])
                                formValidationCounter.value--
                            }
                        else
                            viewModel.apply {
                                listOfChecked.add(viewModel.listOfCheck[0])
                                formValidationCounter.value++
                            }
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = PrimaryBlue
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                StuntionText(
                    text = viewModel.listOfCheck[0],
                    textStyle = Type.bodyMedium(),
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.66).dp)
                )
            }

            // 2nd checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Checkbox(
                    checked = viewModel.listOfChecked.contains(viewModel.listOfCheck[1]),
                    onCheckedChange = {
                        if (viewModel.listOfChecked.contains(viewModel.listOfCheck[1]))
                            viewModel.apply {
                                listOfChecked.remove(viewModel.listOfCheck[1])
                                formValidationCounter.value--
                            }
                        else
                            viewModel.apply {
                                listOfChecked.add(viewModel.listOfCheck[1])
                                formValidationCounter.value++
                            }
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = PrimaryBlue
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                StuntionText(
                    text = viewModel.listOfCheck[1],
                    textStyle = Type.bodyMedium(),
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.66).dp)
                )
            }

            // 3rd checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Checkbox(
                    checked = viewModel.listOfChecked.contains(viewModel.listOfCheck[2]),
                    onCheckedChange = {
                        if (viewModel.listOfChecked.contains(viewModel.listOfCheck[2]))
                            viewModel.apply {
                                listOfChecked.remove(viewModel.listOfCheck[2])
                                formValidationCounter.value--
                            }
                        else
                            viewModel.apply {
                                listOfChecked.add(viewModel.listOfCheck[2])
                                formValidationCounter.value++
                            }
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = PrimaryBlue
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                StuntionText(
                    text = viewModel.listOfCheck[2],
                    textStyle = Type.bodyMedium(),
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.66).dp)
                )
            }

            // 4th checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Checkbox(
                    checked = viewModel.listOfChecked.contains(viewModel.listOfCheck[3]),
                    onCheckedChange = {
                        if (viewModel.listOfChecked.contains(viewModel.listOfCheck[3]))
                            viewModel.apply {
                                listOfChecked.remove(viewModel.listOfCheck[3])
                                formValidationCounter.value--
                            }
                        else
                            viewModel.apply {
                                listOfChecked.add(viewModel.listOfCheck[3])
                                formValidationCounter.value++
                            }
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = PrimaryBlue
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                StuntionText(
                    text = viewModel.listOfCheck[3],
                    textStyle = Type.bodyMedium(),
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.66).dp)
                )
            }

            // 5th checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Checkbox(
                    checked = viewModel.listOfChecked.contains(viewModel.listOfCheck[4]),
                    onCheckedChange = {
                        if (viewModel.listOfChecked.contains(viewModel.listOfCheck[4]))
                            viewModel.apply {
                                listOfChecked.remove(viewModel.listOfCheck[4])
                                formValidationCounter.value--
                            }
                        else
                            viewModel.apply {
                                listOfChecked.add(viewModel.listOfCheck[4])
                                formValidationCounter.value++
                            }
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = PrimaryBlue
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                StuntionText(
                    text = viewModel.listOfCheck[4],
                    textStyle = Type.bodyMedium(),
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.66).dp)
                )
            }

            AsyncImage(model = R.drawable.ic_support_terms, contentDescription = "Terms")
        }
    }
}