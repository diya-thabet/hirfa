package com.fennec.hirfa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fennec.hirfa.presentation.auth.AuthScreen
import com.fennec.hirfa.presentation.auth.LoginScreen
import com.fennec.hirfa.presentation.auth.RegisterScreen
import com.fennec.hirfa.presentation.onboarding.OnboardingScreen
import com.fennec.hirfa.presentation.profile.ProfileSetupScreen
import com.fennec.hirfa.presentation.splash.SplashScreen
import com.fennec.hirfa.presentation.usertype.UserTypeSelectionScreen

@Composable
fun HirfaNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Splash.route
    ) {
        composable(Destinations.Splash.route) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(Destinations.Onboarding.route) {
                        popUpTo(Destinations.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToAuth = {
                    navController.navigate(Destinations.Auth.route) {
                        popUpTo(Destinations.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Destinations.Onboarding.route) {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(Destinations.Auth.route) {
                        popUpTo(Destinations.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Destinations.Auth.route) {
            AuthScreen(
                onNavigateToLogin = {
                    navController.navigate(Destinations.Login.route)
                },
                onNavigateToRegister = {
                    navController.navigate(Destinations.Register.route)
                }
            )
        }

        composable(Destinations.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Destinations.UserTypeSelection.route) {
                        popUpTo(Destinations.Auth.route) { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Destinations.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Destinations.UserTypeSelection.route) {
                        popUpTo(Destinations.Auth.route) { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Destinations.UserTypeSelection.route) {
            UserTypeSelectionScreen(
                onUserTypeSelected = { userType ->
                    navController.navigate(Destinations.ProfileSetup.createRoute(userType))
                }
            )
        }

        composable(Destinations.ProfileSetup.route) { backStackEntry ->
            val userType = backStackEntry.arguments?.getString("userType") ?: "customer"
            ProfileSetupScreen(
                userType = userType,
                onProfileComplete = {
                    navController.navigate(Destinations.Home.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Destinations.Home.route) {
            // Placeholder for home screen
            androidx.compose.material3.Text("Home Screen - Welcome!")
        }
    }
}