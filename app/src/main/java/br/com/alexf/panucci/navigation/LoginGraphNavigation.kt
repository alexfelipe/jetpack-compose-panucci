package br.com.alexf.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import br.com.alexf.panucci.AppRoutes

internal fun NavGraphBuilder.loginGraph(
    navController: NavController
) {
    navigation(
        startDestination = AppRoutes.SignIn.route,
        route = AppRoutes.Login.route
    ) {
        signInScreen(
            onNavigateToHome = {
                navigateToHome(navController)
            }, onNavigateToSignUp = {
                navigateToSignUp(navController)
            })
        signUpScreen(
            onPopBackStack = navController::popBackStack
        )
    }
}