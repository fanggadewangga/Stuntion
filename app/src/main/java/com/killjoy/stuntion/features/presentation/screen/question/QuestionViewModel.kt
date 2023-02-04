package com.killjoy.stuntion.features.presentation.screen.question

import androidx.lifecycle.ViewModel
import com.killjoy.stuntion.features.domain.model.StuntingQuestion

class QuestionViewModel : ViewModel() {
    val listOfQuestion = listOf(
        StuntingQuestion(question = "Are children diligently taken to posyandu?"),
        StuntingQuestion(question = "Has the child received complete immunizations?"),
        StuntingQuestion(question = "Does the child get breast milk or good nutrition?"),
        StuntingQuestion(
            question = "What do parents do when their child is sick?",
            isMultipleChoices = true,
            listOfAnswer = listOf(
                "Providing child with prescription medications",
                "Keeping child well-hydrated and fed",
                "Ensuring child get plenty of rest",
                "Take the child to the doctor"
            )
        ),
        StuntingQuestion(question = "Is there always a variety of, nutritious, balanced and safe food available at home?"),
        StuntingQuestion(question = "Have parents ever been given knowledge about preparing Diverse, Nutritious, Balanced and Safe food when taking their children to the posyandu?"),
    )
}