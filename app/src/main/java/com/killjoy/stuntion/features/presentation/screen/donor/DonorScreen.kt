package com.killjoy.stuntion.features.presentation.screen.donor

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.components.CategoryChip
import com.killjoy.stuntion.features.presentation.utils.components.DonorItem
import com.killjoy.stuntion.features.presentation.utils.components.DonorItemShimmer
import com.killjoy.stuntion.features.presentation.utils.components.ErrorLayout
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar

@Composable
fun DonorScreen(navController: NavController, donationId: String) {
    val viewModel = hiltViewModel<DonorViewModel>()
    val systemUiController = rememberSystemUiController()
    val donorResponse = viewModel.donorResponse.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(color = Color.Transparent, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
        viewModel.fetchDonationDonors(donationId)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {
        when (donorResponse.value) {
            is Resource.Loading -> {
                items(10) {
                    DonorItemShimmer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }

            is Resource.Error -> item { ErrorLayout("Something went wrong") }
            is Resource.Empty -> item { ErrorLayout() }
            is Resource.Success -> {
                // Top Bar
                item {
                    StuntionTopBar(
                        title = "Support (${donorResponse.value.data?.size})",
                        onBackPressed = { navController.popBackStack() },
                        isWithDivider = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    ) {
                        viewModel.sortBy.forEach { category ->
                            CategoryChip(
                                category = category,
                                selected = viewModel.selectedSortState.value,
                                onSelected = { viewModel.selectedSortState.value = it }
                            )
                        }
                    }
                }

                items(
                    when(viewModel.selectedSortState.value) {
                        viewModel.sortBy[0] -> {
                            donorResponse.value.data!!.sortedBy { it.dayPeriod }
                        }
                        viewModel.sortBy[1] -> {
                            donorResponse.value.data!!.sortedByDescending { it.nominal }
                        }
                        else -> {
                            donorResponse.value.data!!
                        }
                    }
                ) {
                    DonorItem(
                        donorResponse = it, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}