package com.killjoy.stuntion.features.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.killjoy.stuntion.features.domain.model.child.Child
import com.killjoy.stuntion.features.presentation.screen.add_question.AddQuestionScreen
import com.killjoy.stuntion.features.presentation.screen.video.detail.VideoDetailScreen
import com.killjoy.stuntion.features.presentation.screen.video.videos.VideosScreen
import com.killjoy.stuntion.features.presentation.screen.ask_expert.AskExpertScreen
import com.killjoy.stuntion.features.presentation.screen.ask_expert_detail.AskExpertDetailScreen
import com.killjoy.stuntion.features.presentation.screen.auth.CreateAccountSuccessScreen
import com.killjoy.stuntion.features.presentation.screen.auth.login.LoginScreen
import com.killjoy.stuntion.features.presentation.screen.auth.signup.SignupScreen
import com.killjoy.stuntion.features.presentation.screen.avatar.AvatarScreen
import com.killjoy.stuntion.features.presentation.screen.chat_expert.ChatExpertsScreen
import com.killjoy.stuntion.features.presentation.screen.check.CheckScreen
import com.killjoy.stuntion.features.presentation.screen.child_profile.ChildProfileScreen
import com.killjoy.stuntion.features.presentation.screen.consultation.ConsultationScreen
import com.killjoy.stuntion.features.presentation.screen.expert.ExpertDetailScreen
import com.killjoy.stuntion.features.presentation.screen.general_information.GeneralInformationScreen
import com.killjoy.stuntion.features.presentation.screen.home.HomeScreen
import com.killjoy.stuntion.features.presentation.screen.location_permission.LocationPermissionScreen
import com.killjoy.stuntion.features.presentation.screen.onboard.OnboardScreen
import com.killjoy.stuntion.features.presentation.screen.profile.ProfileScreen
import com.killjoy.stuntion.features.presentation.screen.question.QuestionScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.RequestHelpSuccessScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.detail_information.DetailInformationScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.help_target.HelpTargetScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.personal_data.PersonalDataScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.title.TitleScreen
import com.killjoy.stuntion.features.presentation.screen.splash.SplashScreen
import com.killjoy.stuntion.features.presentation.screen.support.SupportScreen
import com.killjoy.stuntion.features.presentation.screen.support.detail.SupportDetailScreen
import com.killjoy.stuntion.features.presentation.screen.support_tutorial.SupportTutorialScreen
import com.killjoy.stuntion.features.presentation.screen.verification.VerificationSuccessScreen
import com.killjoy.stuntion.features.presentation.screen.verification.card.CardVerificationScreen
import com.killjoy.stuntion.features.presentation.screen.verification.identity.IdentityVerificationScreen
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.navigation_util.ChildArgType

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.OnboardScreen.route) {
            OnboardScreen(navController = navController)
        }

        // Auth
        composable(route = Screen.SignupScreen.route) {
            SignupScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.CreateAccountSuccessScreen.route) {
            CreateAccountSuccessScreen(navController = navController)
        }

        composable(route = Screen.GeneralInformationScreen.route) {
            GeneralInformationScreen(navController = navController)
        }

        // Main screen
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.ConsultScreen.route) {
            ConsultationScreen(navController = navController)
        }
        composable(route = Screen.CheckScreen.route) {
            CheckScreen(navController = navController)
        }
        composable(route = Screen.SupportScreen.route) {
            SupportScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }

        // Ask
        composable(route = Screen.AskExpertScreen.route) {
            AskExpertScreen(navController = navController)
        }

        composable(route = Screen.AddQuestionScreen.route) {
            AddQuestionScreen(navController = navController)
        }

        composable(route = Screen.AskExpertDetailScreen.route) {
            AskExpertDetailScreen(navController = navController)
        }


        // Chat

        composable(route = Screen.ChatExpertScreen.route) {
            ChatExpertsScreen(navController = navController)
        }

        // Article
        composable(route = Screen.VideosScreen.route) {
            VideosScreen(navController = navController)
        }
        composable(route = Screen.VideoDetailScreen.route) {
            VideoDetailScreen(navController = navController)
        }

        // Expert
        composable(route = Screen.ExpertDetailScreen.route) {
            ExpertDetailScreen(navController = navController)
        }

        // Avatar
        composable(route = Screen.AvatarScreen.route) {
            AvatarScreen(navController = navController)
        }

        // Location Permission
        composable(route = Screen.LocationPermissionScreen.route) {
            LocationPermissionScreen(navController = navController)
        }

        // Child Profile
        composable(
            route = "${Screen.ChildProfileScreen.route}/{child}",
            arguments = listOf(
                navArgument("child") {
                    type = ChildArgType()
                }
            )
        ) { navBackStackEntry ->
            @Suppress("DEPRECATION") val child =
                navBackStackEntry.arguments?.getParcelable<Child>("child")
            child?.let { ChildProfileScreen(navController = navController, child = it) }
        }

        // Support detail
        composable(route = Screen.SupportDetailScreen.route) {
            SupportDetailScreen(navController = navController)
        }

        // Support Request
        composable(route = Screen.SupportRequestTutorialScreen.route) {
            SupportTutorialScreen(navController = navController)
        }
        composable(route = Screen.RequestHelpSuccessScreen.route) {
            RequestHelpSuccessScreen(navController = navController)
        }
        composable(route = Screen.PersonalDataScreen.route) {
            PersonalDataScreen(navController = navController)
        }
        composable(route = Screen.HelpTargetScreen.route) {
            HelpTargetScreen(navController = navController)
        }
        composable(route = Screen.TitleScreen.route) {
            TitleScreen(navController = navController)
        }
        composable(route = Screen.DetailInformationScreen.route) {
            DetailInformationScreen(navController = navController)
        }
        composable(route = Screen.ConfirmationScreen.route) {

        }

        // Verification success
        composable(route = Screen.VerificationSuccessScreen.route) {
            VerificationSuccessScreen(navController = navController)
        }
        composable(route = Screen.IdentityVerificationScreen.route) {
            IdentityVerificationScreen(navController = navController)
        }
        composable(route = Screen.CardVerificationScreen.route) {
            CardVerificationScreen(navController = navController)
        }

        // Question
        composable(route = Screen.QuestionScreen.route) {
            QuestionScreen(navController = navController)
        }
    }
}