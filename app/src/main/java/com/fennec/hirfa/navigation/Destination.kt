package com.fennec.hirfa.navigation

sealed class Destinations(val route: String) {
    object Splash : Destinations("splash")
    object Onboarding : Destinations("onboarding")
    object Auth : Destinations("auth")
    object Login : Destinations("login")
    object Register : Destinations("register")
    object UserTypeSelection : Destinations("user_type_selection")
    object ProfileSetup : Destinations("profile_setup/{userType}") {
        fun createRoute(userType: String) = "profile_setup/$userType"
    }
    object Home : Destinations("home")
}