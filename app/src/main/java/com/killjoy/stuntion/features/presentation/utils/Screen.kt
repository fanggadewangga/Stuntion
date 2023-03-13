package com.killjoy.stuntion.features.presentation.utils

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object OnboardScreen : Screen("onboard_screen")

    // Auth
    object LoginScreen : Screen("login_screen")
    object SignupScreen : Screen("signup_screen")
    object CreateAccountSuccessScreen : Screen("create_account_success_screen")

    // General information
    object GeneralInformationScreen : Screen("general_information_screen")

    // Main screen
    object HomeScreen : Screen("home_screen")
    object ConsultScreen : Screen("consult_screen")
    object CheckScreen : Screen("check_screen")
    object SupportScreen : Screen("support_screen")
    object ProfileScreen : Screen("profile_screen")

    // Ask
    object AskExpertScreen : Screen("ask_expert_screen")
    object AddQuestionScreen : Screen("add_question_screen")
    object AskExpertDetailScreen : Screen("ask_expert_detail_screen")

    // Chat
    object ChatExpertScreen : Screen("chat_expert_screen")
    object ChatRoomScreen : Screen("chat_room_screen")

    // Article
    object VideosScreen : Screen("videos_screen")
    object VideoDetailScreen : Screen("video_detail_screen")

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
    object RequestHelpScreen : Screen("request_help_screen")
    object SupportRequestTutorialScreen : Screen("support_request_tutorial_screen")
    object RequestHelpSuccessScreen : Screen("request_help_success_screen")
    object DetailInformationScreen : Screen("detail_information_screen")
    object HelpTargetScreen : Screen("help_target_screen")
    object PersonalDataScreen : Screen("personal_data_screen")
    object TitleScreen : Screen("title_screen")
    object ConfirmationScreen : Screen("confirmation_screen")

    // Verification
    object IdentityVerificationScreen : Screen("identity_verification_screen")
    object CardVerificationScreen : Screen("card_verification_screen")
    object DataVerificationScreen : Screen("data_verification_screen")
    object VerificationSuccessScreen : Screen("verification_success_screen")

    // Question
    object QuestionScreen : Screen("question_screen")

    // Camera
    object CardCameraScreen : Screen("card_camera_screen")
    object FaceCameraScreen : Screen("face_camera_screen")

    // Healthy tips
    object HealthyTipsDetailScreen: Screen("healthy_tips_detail_screen")

    // Child Notes
    object ChildNotesScreen : Screen("child_notes_screen")

    // Activity List
    object ActivityListScreen : Screen("activity_list_screen")

    // My Healthy Tips
    object MyHealthyTipsScreen : Screen("my_healthy_tips_screen")

    // Check Tutorial
    object CheckTutorialScreen : Screen("check_tutorial_screen")

    // Reward
    object RewardScreen : Screen("reward_screen")

    // Notification
    object NotificationScreen : Screen("notification_screen")
}
