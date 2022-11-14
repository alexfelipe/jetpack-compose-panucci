package br.com.alexf.panucci

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.alexf.panucci.navigation.loginGraph
import br.com.alexf.panucci.navigation.mainGraph
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.screens.*

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
    }
}