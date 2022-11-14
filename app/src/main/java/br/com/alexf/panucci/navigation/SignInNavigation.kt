package br.com.alexf.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alexf.panucci.AppRoutes
import br.com.alexf.panucci.ui.screens.LoginScreen

fun NavGraphBuilder.signInScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    composable(AppRoutes.SignIn.route) {
        LoginScreen(
            onNavigateToHome = onNavigateToHome,
            onNavigateToSignUp = onNavigateToSignUp
        )
    }
}

fun navigateToSignUp(navController: NavController) {
    navController.navigate(AppRoutes.SignUp.route)
}

fun navigateToHome(navController: NavController) {
    navController.navigate(AppRoutes.Home.route) {
        popUpTo(AppRoutes.Login.route)
    }
}