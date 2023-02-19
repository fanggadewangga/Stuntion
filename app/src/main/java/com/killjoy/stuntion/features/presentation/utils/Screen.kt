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
    object SupportScreen: Screen("support_screen")
    object ProfileScreen: Screen("profile_screen")

    // Ask
    object AskExpertScreen: Screen("ask_expert_screen")
    object AddQuestionScreen: Screen("add_question_screen")
    object AskExpertDetailScreen : Screen("ask_expert_detail_screen")

    // Chat
    object ChatExpertScreen: Screen("chat_expert_screen")

    // Article
    object ArticlesScreen: Screen("articles_screen")
    object ArticleDetailScreen: Screen("article_detail_screen")

    // Expert
    object ExpertDetailScreen : Screen("expert_detail_screen")

    // Avatar
    object AvatarScreen : Screen("avatar_screen")

    // Location permission
    object LocationPermissionScreen : Screen("location_permission_screen")

    // Child profile
    object ChildProfileScreen : Screen("child_profile_screen")

    // Support detail
    object SupportDetailScreen : Screen("support_detail_screen")

    // Support request
    object SupportRequestTutorialScreen : Screen("support_request_tutorial_screen")
    object RequestHelpSuccessScreen : Screen("request_help_success_screen")

    // Verification
    object IdentityVerificationScreen : Screen("identity_verification_screen")
    object CardVerificationScreen : Screen("card_verification_screen")
    object VerificationSuccessScreen : Screen("verification_success_screen")

    // Question
    object QuestionScreen : Screen("question_screen")
}
