package com.killjoy.stuntion.features.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.killjoy.stuntion.features.domain.model.child.Child
import com.killjoy.stuntion.features.presentation.screen.account_management.AccountManagementScreen
import com.killjoy.stuntion.features.presentation.screen.activity_list.ActivityListScreen
import com.killjoy.stuntion.features.presentation.screen.add_question.AddQuestionScreen
import com.killjoy.stuntion.features.presentation.screen.ask_expert.AskExpertScreen
import com.killjoy.stuntion.features.presentation.screen.ask_expert_detail.AskExpertDetailScreen
import com.killjoy.stuntion.features.presentation.screen.auth.CreateAccountSuccessScreen
import com.killjoy.stuntion.features.presentation.screen.auth.login.LoginScreen
import com.killjoy.stuntion.features.presentation.screen.auth.signup.SignupScreen
import com.killjoy.stuntion.features.presentation.screen.avatar.AvatarScreen
import com.killjoy.stuntion.features.presentation.screen.camera.CardCameraScreen
import com.killjoy.stuntion.features.presentation.screen.camera.FaceCameraScreen
import com.killjoy.stuntion.features.presentation.screen.chat.ChatRoomScreen
import com.killjoy.stuntion.features.presentation.screen.chat_expert.ChatExpertsScreen
import com.killjoy.stuntion.features.presentation.screen.check.CheckScreen
import com.killjoy.stuntion.features.presentation.screen.check.CheckTutorialScreen
import com.killjoy.stuntion.features.presentation.screen.child_notes.detail.ChildNotesDetailScreen
import com.killjoy.stuntion.features.presentation.screen.child_notes.notes.ChildNotesScreen
import com.killjoy.stuntion.features.presentation.screen.child_profile.ChildProfileScreen
import com.killjoy.stuntion.features.presentation.screen.consultation.ConsultationScreen
import com.killjoy.stuntion.features.presentation.screen.donor.DonorScreen
import com.killjoy.stuntion.features.presentation.screen.expert.ExpertDetailScreen
import com.killjoy.stuntion.features.presentation.screen.general_information.GeneralInformationScreen
import com.killjoy.stuntion.features.presentation.screen.healthy_tips.HealthyTipsDetailScreen
import com.killjoy.stuntion.features.presentation.screen.home.HomePaymentSharedViewModel
import com.killjoy.stuntion.features.presentation.screen.home.HomeScreen
import com.killjoy.stuntion.features.presentation.screen.location_permission.LocationPermissionScreen
import com.killjoy.stuntion.features.presentation.screen.my_healthy_tips.MyHealthyTipsScreen
import com.killjoy.stuntion.features.presentation.screen.notification.NotificationScreen
import com.killjoy.stuntion.features.presentation.screen.onboard.OnboardScreen
import com.killjoy.stuntion.features.presentation.screen.payment.PaymentMethodScreen
import com.killjoy.stuntion.features.presentation.screen.payment.instruction.PaymentInstructionScreen
import com.killjoy.stuntion.features.presentation.screen.profile.ProfileScreen
import com.killjoy.stuntion.features.presentation.screen.question.QuestionScreen
import com.killjoy.stuntion.features.presentation.screen.redirect.RedirectScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.RequestHelpScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.RequestHelpSuccessScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.confirmation.ConfirmationScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.detail_information.DetailInformationScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.help_target.HelpTargetScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.personal_data.PersonalDataScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.title.TitleScreen
import com.killjoy.stuntion.features.presentation.screen.reward.RewardScreen
import com.killjoy.stuntion.features.presentation.screen.splash.SplashScreen
import com.killjoy.stuntion.features.presentation.screen.support.detail.SupportDetailScreen
import com.killjoy.stuntion.features.presentation.screen.support.food.AdditionalFoodScreen
import com.killjoy.stuntion.features.presentation.screen.support.food.details.AdditionalFoodDetailScreen
import com.killjoy.stuntion.features.presentation.screen.support.payment.SupportPaymentMethodScreen
import com.killjoy.stuntion.features.presentation.screen.support.payment.SupportPaymentSharedViewModel
import com.killjoy.stuntion.features.presentation.screen.support.status.SupportPaymentStatusScreen
import com.killjoy.stuntion.features.presentation.screen.support.supports.SupportScreen
import com.killjoy.stuntion.features.presentation.screen.support_tutorial.SupportTutorialScreen
import com.killjoy.stuntion.features.presentation.screen.verification.VerificationSuccessScreen
import com.killjoy.stuntion.features.presentation.screen.verification.card.CardVerificationScreen
import com.killjoy.stuntion.features.presentation.screen.verification.data.DataVerificationScreen
import com.killjoy.stuntion.features.presentation.screen.verification.identity.IdentityVerificationScreen
import com.killjoy.stuntion.features.presentation.screen.video.detail.VideoDetailScreen
import com.killjoy.stuntion.features.presentation.screen.video.videos.VideosScreen
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.navigation_util.ChildArgType

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val homePaymentSharedViewModel: HomePaymentSharedViewModel = hiltViewModel()
    val supportPaymentSharedViewModel: SupportPaymentSharedViewModel = hiltViewModel()

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
            HomeScreen(navController = navController, homePaymentSharedViewModel)
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
            val questionId = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                key = "questionId"
            )
            questionId?.let { it1 ->
                AskExpertDetailScreen(
                    navController = navController,
                    questionId = it1
                )
            }
        }


        // Chat

        composable(route = Screen.ChatExpertScreen.route) {
            ChatExpertsScreen(navController = navController)
        }
        composable(route = Screen.ChatRoomScreen.route) {
            ChatRoomScreen(navController = navController)
        }

        // Article
        composable(route = Screen.VideosScreen.route) {
            VideosScreen(navController = navController)
        }
        composable(route = Screen.VideoDetailScreen.route) {
            val smartstunId = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                key = "smartstunId"
            )
            Log.d("NAVIGATION", smartstunId.toString())
            smartstunId?.let { it1 ->
                VideoDetailScreen(
                    navController = navController,
                    smartstunId = it1
                )
            }
        }

        // Expert
        composable(route = Screen.ExpertDetailScreen.route) {
            val expertId = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                key = "expertId"
            )
            expertId?.let { it1 ->
                ExpertDetailScreen(
                    navController = navController,
                    expertId = it1
                )
            }
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
            val donationId = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                key = "donationId"
            )
            donationId?.let { it1 ->
                SupportDetailScreen(
                    navController = navController,
                    donationId = it1,
                    sharedViewModel = supportPaymentSharedViewModel
                )
            }
        }

        // Support Request
        composable(route = Screen.RequestHelpScreen.route) {
            RequestHelpScreen(navController = navController)
        }
        composable(route = Screen.SupportRequestTutorialScreen.route) {
            SupportTutorialScreen(navController = navController)
        }
        composable(route = Screen.RequestHelpSuccessScreen.route) {
            RequestHelpSuccessScreen(navController = navController)
        }
        composable(route = Screen.PersonalDataScreen.route) {
            PersonalDataScreen()
        }
        composable(route = Screen.HelpTargetScreen.route) {
            HelpTargetScreen()
        }
        composable(route = Screen.TitleScreen.route) {
            TitleScreen()
        }
        composable(route = Screen.DetailInformationScreen.route) {
            DetailInformationScreen()
        }
        composable(route = Screen.ConfirmationScreen.route) {
            ConfirmationScreen()
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
        composable(route = Screen.DataVerificationScreen.route) {
            DataVerificationScreen(navController = navController)
        }

        // Question
        composable(
            route = "${Screen.QuestionScreen.route}/{child}",
            arguments = listOf(
                navArgument("child") {
                    type = ChildArgType()
                }
            )
        ) { navBackStackEntry ->
            @Suppress("DEPRECATION") val child =
                navBackStackEntry.arguments?.getParcelable<Child>("child")
            child?.let { QuestionScreen(navController = navController, child = child) }
        }

        // Camera
        composable(route = Screen.CardCameraScreen.route) {
            CardCameraScreen(navController = navController)
        }

        composable(route = Screen.FaceCameraScreen.route) {
            FaceCameraScreen(navController = navController)
        }

        // Healthy tips
        composable(route = Screen.HealthyTipsDetailScreen.route) {
            val taskId = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                key = "taskId"
            )
            taskId?.let { it1 ->
                HealthyTipsDetailScreen(
                    navController = navController,
                    taskId = it1
                )
            }
        }

        // Activity List
        composable(route = Screen.ActivityListScreen.route) {
            ActivityListScreen(navController = navController)
        }

        // Child Notes
        composable(route = Screen.ChildNotesScreen.route) {
            ChildNotesScreen(navController = navController)
        }
        composable(route = Screen.ChildNotesDetailScreen.route) {
            val noteId = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                key = "noteId"
            )
            noteId?.let { it1 ->
                ChildNotesDetailScreen(
                    navController = navController,
                    noteId = it1
                )
            }
        }

        // My Healthy Tips
        composable(route = Screen.MyHealthyTipsScreen.route) {
            MyHealthyTipsScreen(navController = navController)
        }

        // Check Tutorial
        composable(route = Screen.CheckTutorialScreen.route) {
            CheckTutorialScreen(navController = navController)
        }

        // Reward
        composable(route = Screen.RewardScreen.route) {
            RewardScreen(navController = navController)
        }

        // Notification
        composable(route = Screen.NotificationScreen.route) {
            NotificationScreen(navController = navController)
        }

        // Account Management
        composable(route = Screen.AccountManagementScreen.route) {
            AccountManagementScreen(navController = navController)
        }

        // Redirect
        composable(route = Screen.RedirectScreen.route) {
            RedirectScreen(navController = navController)
        }

        // Payment
        composable(route = Screen.PaymentScreen.route) {
            PaymentMethodScreen(navController = navController, homePaymentSharedViewModel)
        }
        composable(route = Screen.PaymentInstructionScreen.route) {
            PaymentInstructionScreen(
                navController = navController,
                sharedViewModel = homePaymentSharedViewModel
            )
        }

        // Support payment
        composable(route = Screen.SupportPaymentScreen.route) {
            SupportPaymentMethodScreen(
                navController = navController,
                viewModel = supportPaymentSharedViewModel
            )
        }

        composable(route = Screen.SupportPaymentStatusScreen.route) {
            SupportPaymentStatusScreen(
                navController = navController,
                sharedViewModel = supportPaymentSharedViewModel
            )
        }

        // Donor
        composable(route = Screen.DonorScreen.route) {
            val donationId = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                key = "donationId"
            )
            donationId?.let { it1 -> DonorScreen(navController = navController, donationId = it1) }
        }

        // Additional food
        composable(route = Screen.AdditionalFoodScreen.route) {
            AdditionalFoodScreen(navController = navController)
        }
        composable(route = Screen.AdditionalFoodDetailScreen.route) {
            AdditionalFoodDetailScreen(navController = navController)
        }
    }
}