package com.killjoy.stuntion.features.presentation.screen.add_question

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.*
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddQuestionScreen(navController: NavController) {

    val viewModel = hiltViewModel<AddQuestionViewModel>()
    val questionResponse = viewModel.questionResponse.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    BackHandler(modalBottomSheetState.isVisible) {
        coroutineScope.launch { modalBottomSheetState.hide() }
    }

    LaunchedEffect(questionResponse.value) {
        when (questionResponse.value) {
            is Resource.Error -> Toasty.error(
                context,
                questionResponse.value.message.toString(),
                Toast.LENGTH_SHORT
            ).show()

            is Resource.Empty -> {}
            is Resource.Loading -> {}
            is Resource.Success -> {
                Toasty.success(context, "Add question success!", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.ConsultScreen.route) {
                    popUpTo(Screen.AddQuestionScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        sheetContent = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = (LocalConfiguration.current.screenHeightDp / 15).dp)
            ) {

                // Line
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Divider(
                        thickness = 3.dp,
                        color = Color.Gray,
                        modifier = Modifier
                            .width(32.dp)
                            .clip(
                                RoundedCornerShape(100.dp)
                            )
                            .align(Alignment.TopCenter)
                    )
                }

                // Question Category
                Spacer(modifier = Modifier.height(16.dp))
                StuntionText(
                    text = "Question Category",
                    textStyle = Type.titleMedium(),
                    modifier = Modifier.padding(start = 20.dp)
                )

                // Categories
                Spacer(modifier = Modifier.height(8.dp))
                viewModel.categories.forEach {
                    CategoryItem(
                        title = it,
                        onSelected = {
                            viewModel.selectedCategoryItems.add(it)
                        },
                        onUnselected = {
                            viewModel.selectedCategoryItems.remove(it)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    ) {
        Scaffold(
            content = {
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 24.dp)
                ) {
                    // Top Bar
                    StuntionTopBar(
                        title = "Ask Expert",
                        onBackPressed = { navController.popBackStack() }
                    )

                    // Consider
                    Spacer(modifier = Modifier.height(24.dp))
                    Column {
                        Box(Modifier.fillMaxWidth()) {
                            Spacer(modifier = Modifier.height(24.dp))
                            StuntionText(
                                text = "Consider Before Asking",
                                textStyle = Type.titleMedium(),
                                modifier = Modifier.padding(start = 16.dp)
                            )
                            Image(
                                imageVector = if (viewModel.isConsiderDescriptionVisible.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = "Arrow icon",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 16.dp)
                                    .clickable {
                                        viewModel.isConsiderDescriptionVisible.value =
                                            !viewModel.isConsiderDescriptionVisible.value
                                    }
                            )
                        }
                        Text(
                            text = buildAnnotatedString {
                                append("Make sure your question is in accordance with the ")
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append("terms")
                                }
                                append(" and")
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append(" condition")
                                }
                            },
                            fontSize = Type.bodyMedium().fontSize,
                            fontFamily = Type.bodyMedium().fontFamily,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )

                        // Consider description
                        if (viewModel.isConsiderDescriptionVisible.value)
                            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                                Spacer(modifier = Modifier.height(8.dp))
                                viewModel.considerDescriptions.forEachIndexed { index, description ->
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        StuntionText(
                                            text = "${index + 1}. ",
                                            textStyle = Type.bodyMedium()
                                        )
                                        StuntionText(
                                            text = description,
                                            textStyle = Type.bodyMedium()
                                        )
                                    }
                                }
                            }
                    }

                    // Headline
                    Spacer(modifier = Modifier.height(24.dp))
                    Divider(Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(8.dp))
                    StuntionText(
                        text = "Make A Question",
                        textStyle = Type.titleMedium(),
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    // Question category
                    Spacer(modifier = Modifier.height(16.dp))
                    StuntionText(
                        text = "Question Category",
                        textStyle = Type.labelLarge(),
                        color = LightGray,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .border(
                                    width = 1.dp,
                                    color = LightGray,
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .clickable {
                                    coroutineScope.launch {
                                        if (modalBottomSheetState.isVisible)
                                            modalBottomSheetState.hide()
                                        else
                                            modalBottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                                    }
                                }
                        ) {
                            StuntionText(
                                text = viewModel.selectedCategory.value.ifEmpty { "Enter Category" },
                                textStyle = Type.bodyLarge(),
                                color = if (viewModel.selectedCategory.value.isEmpty()) LightGray else Color.Black,
                                maxLine = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                            Image(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Arrow icon",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 16.dp)
                                    .clickable {
                                        coroutineScope.launch {
                                            if (modalBottomSheetState.isVisible)
                                                modalBottomSheetState.hide()
                                            else
                                                modalBottomSheetState.animateTo(
                                                    ModalBottomSheetValue.Expanded
                                                )
                                        }
                                    }
                            )
                        }
                    }


                    // Question Title
                    Spacer(modifier = Modifier.height(16.dp))
                    StuntionText(
                        text = "Question Title",
                        textStyle = Type.labelLarge(),
                        color = LightGray,
                        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        StuntionBasicTextField(
                            placeHolder = "Enter Title",
                            value = viewModel.questionTitleState.value,
                            onValueChange = {
                                viewModel.questionTitleState.value = it
                            },
                            textStyle = Type.bodyLarge(),
                            shape = RoundedCornerShape(32.dp),
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }


                    // Question description
                    Spacer(modifier = Modifier.height(16.dp))
                    StuntionText(
                        text = "Your Question",
                        textStyle = Type.labelLarge(),
                        color = LightGray,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        StuntionBasicTextField(
                            placeHolder = "Enter Question",
                            value = viewModel.questionDescriptionState.value,
                            onValueChange = {
                                viewModel.questionDescriptionState.value = it
                            },
                            singleLine = false,
                            textFieldHeight = 240.dp,
                            textStyle = Type.bodyLarge(),
                            shape = RoundedCornerShape(32.dp),
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }


                    // Anonymous checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    ) {
                        Checkbox(
                            checked = viewModel.checkedState.value,
                            onCheckedChange = { viewModel.checkedState.value = it },
                            colors = CheckboxDefaults.colors(
                                checkmarkColor = Color.White,
                                checkedColor = PrimaryBlue
                            )
                        )
                        StuntionText(
                            text = "Keep my name secret (anonymous)",
                            textStyle = Type.bodyMedium()
                        )
                    }

                    // Button
                    Spacer(modifier = Modifier.height(16.dp))
                    if (questionResponse.value is Resource.Loading)
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            LoadingAnimation()
                        }
                    else {
                        StuntionButton(
                            onClick = {
                                viewModel.postNewQuestion()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            StuntionText(
                                text = "Send", color = Color.White, textStyle = Type.labelLarge()
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            },
            modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 15).dp)
        )
    }
}