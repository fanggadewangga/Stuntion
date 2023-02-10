package com.killjoy.stuntion.features.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.features.presentation.screen.general_information.GeneralInformationScreen
import com.killjoy.stuntion.features.presentation.screen.auth.login.LoginScreen
import com.killjoy.stuntion.features.presentation.screen.auth.signup.SignupScreen
import com.killjoy.stuntion.features.presentation.screen.calculator.CalculatorScreen
import com.killjoy.stuntion.features.presentation.screen.consultation.ConsultationScreen
import com.killjoy.stuntion.features.presentation.screen.consultation.ask_expert.questions.AskExpertScreen
import com.killjoy.stuntion.features.presentation.screen.add_question.AddQuestionScreen
import com.killjoy.stuntion.features.presentation.screen.ask_expert_detail.AskExpertDetailScreen
import com.killjoy.stuntion.features.presentation.screen.avatar.AvatarScreen
import com.killjoy.stuntion.features.presentation.screen.chat_expert.ChatExpertsScreen
import com.killjoy.stuntion.features.presentation.screen.expert.ExpertDetailScreen
import com.killjoy.stuntion.features.presentation.screen.location_permission.LocationPermissionScreen
import com.killjoy.stuntion.features.presentation.screen.onboard.OnboardScreen
import com.killjoy.stuntion.features.presentation.screen.profile.ProfileScreen
import com.killjoy.stuntion.features.presentation.screen.splash.SplashScreen
import com.killjoy.stuntion.features.presentation.utils.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AvatarScreen.route) {

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

        composable(route = Screen.GeneralInformationScreen.route) {
            GeneralInformationScreen(navController = navController)
        }

        // Main screen
        composable(route = Screen.HomeScreen.route) {

        }
        composable(route = Screen.ConsultScreen.route) {
            ConsultationScreen(navController = navController)
        }
        composable(route = Screen.CheckScreen.route) {
            CalculatorScreen(navController = navController)
        }
        composable(route = Screen.ActivityScreen.route) {

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
        composable(route = Screen.ArticlesScreen.route) {

        }
        composable(route = Screen.ArticleDetailScreen.route) {

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
        composable(route = Screen.LocationPermission.route) {
            LocationPermissionScreen(navController = navController)
        }
    }
}