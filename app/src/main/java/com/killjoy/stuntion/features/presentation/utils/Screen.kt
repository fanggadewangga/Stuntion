package com.killjoy.stuntion.features.presentation.utils

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object OnboardScreen : Screen("onboard_screen")

    // Auth
    object LoginScreen : Screen("login_screen")
    object SignupScreen : Screen("signup_screen")

    // General information
    object GeneralInformationScreen: Screen("general_information_screen")

    // Main screen
    object HomeScreen: Screen("home_screen")
    object ConsultScreen: Screen("consult_screen")
    object CheckScreen: Screen("check_screen")
    object ActivityScreen: Screen("activity_screen")
    object ProfileScreen: Screen("profile_screen")

    // Ask
    object AskExpertScreen: Screen("ask_expert_screen")
    object AddQuestionScreen: Screen("add_question_screen")
    object AskExpertDetailScreen : Screen("ask_expert_detail_screen")

    // Chat
    object ChatExpertScreen: Screen("chat_expert_screen")

    // Article
    object ArticlesScreen: Screen("articles_screen")
    object ArticleDetailScreen: Screen("article_deetail_screen")

    // Expert
    object ExpertDetailScreen : Screen("expert_detail_screen")

    // Avatar
    object AvatarScreen : Screen("avatar_screen")

    // Location permission
    object LocationPermission : Screen("location_permission_screen")
}
