package br.com.alexf.panucci

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.alexf.panucci.navigation.checkoutScreen
import br.com.alexf.panucci.navigation.loginGraph
import br.com.alexf.panucci.navigation.mainGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginGraph(navController)
        mainGraph(navController)
        checkoutScreen()
    }
}

fun NavController.navigateSingleTopTo(
    route: String
) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

