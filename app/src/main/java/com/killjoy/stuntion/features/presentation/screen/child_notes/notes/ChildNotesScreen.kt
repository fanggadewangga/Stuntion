package com.killjoy.stuntion.features.presentation.screen.child_notes.notes

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.ChildNotesItem
import com.killjoy.stuntion.features.presentation.utils.components.ChildNotesItemShimmer
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar

@Composable
fun ChildNotesScreen(navController: NavController) {
    val viewModel = hiltViewModel<ChildNotesViewModel>()
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = Color.White, darkIcons = true)
        setNavigationBarColor(color = Color.White, darkIcons = true)
    }
    val notesResponse = viewModel.noteResponse.collectAsState()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(vertical = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {

        item {
            StuntionTopBar(
                title = "Child Notes",
                onBackPressed = { navController.popBackStack() },
                isWithDivider = true
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        when (notesResponse.value) {
            is Resource.Loading -> {
                items(8) {
                    ChildNotesItemShimmer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }
            }
            is Resource.Error -> Log.d("FETCH NOTES ERROR", notesResponse.value.message.toString())
            is Resource.Empty -> Log.d("FETCH NOTES ERROR", notesResponse.value.message.toString())
            is Resource.Success -> {
                items(viewModel.noteResponse.value.data!!) {
                    ChildNotesItem(
                        note = it,
                        onClick = {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = "noteId",
                                value = it.noteId
                            )
                            navController.navigate(Screen.ChildNotesDetailScreen.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}