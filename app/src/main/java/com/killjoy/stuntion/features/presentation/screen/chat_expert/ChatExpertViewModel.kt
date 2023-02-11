package com.killjoy.stuntion.features.presentation.screen.chat_expert

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.killjoy.stuntion.features.domain.model.expert.ExpertCategory
import javax.inject.Inject

class ChatExpertViewModel @Inject constructor() : ViewModel() {
    val searchState = mutableStateOf("")
    val listOfExpertCategory = listOf(
        ExpertCategory(title = "Nutritionist"),
        ExpertCategory(title = "General Practitioner"),
        ExpertCategory(title = "Midwife"),
        ExpertCategory(title = "Pediatrician"),
        ExpertCategory(title = "Obgyn"),
        ExpertCategory(title = "Obstetricians")
    )
}