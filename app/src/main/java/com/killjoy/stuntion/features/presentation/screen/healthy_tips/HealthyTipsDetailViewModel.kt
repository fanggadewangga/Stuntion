package com.killjoy.stuntion.features.presentation.screen.healthy_tips

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HealthyTipsDetailViewModel @Inject constructor(): ViewModel() {
    val instructions = listOf(
        "Start with small steps: Begin by having your child practice climbing up and down just a few stairs at a time. As they get more confident and comfortable, you can gradually increase the number of stairs.",
        "Hold their hand: For younger children, it is important to hold their hand or have them hold onto a stable handrail while they climb. This can help them feel more secure and reduce the risk of falls.",
        "Encourage them to look ahead: Remind your child to look ahead while they climb, rather than down at their feet. This can help them maintain their balance and avoid tripping.",
        "Practice safe stepping: Teach your child to place their entire foot on each step, rather than just the toes or balls of their feet. This can help them maintain better balance and avoid falls.",
        "Be patient and encouraging: Climbing stairs can be challenging for young children, so it is important to be patient and encouraging throughout the process. Offer praise and support for their efforts and accomplishments, and allow them to move at their own pace."
    )
}