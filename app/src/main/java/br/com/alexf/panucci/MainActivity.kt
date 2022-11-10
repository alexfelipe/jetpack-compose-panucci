package br.com.alexf.panucci

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.LocalBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.alexf.panucci.routes.AppRoute
import br.com.alexf.panucci.ui.components.AppModalDrawerSheet
import br.com.alexf.panucci.ui.navigation.AppNavHost
import br.com.alexf.panucci.ui.screens.CheckoutScreen
import br.com.alexf.panucci.ui.screens.DrinksListScreen
import br.com.alexf.panucci.ui.screens.HighlightsListScreen
import br.com.alexf.panucci.ui.screens.MenuListScreen
import br.com.alexf.panucci.ui.theme.PanucciTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(
                        currentDestination,
                        onRouteChange = {
                            navController.navigate(it) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        onFabClick = {
                            navController.navigate(AppRoute.Checkout.route)
                        }) {
                        AppNavHost(
                            navController = navController,
                            startDestination = AppRoute.Login.route
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    currentDestination: NavDestination? = null,
    onRouteChange: (String) -> Unit = {},
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val currentRoute = currentDestination?.route ?: AppRoute.HighlightsList.route
    val bottomNavRoutes = listOf(
        AppRoute.HighlightsList to Icons.Filled.AutoAwesome,
        AppRoute.Menu to Icons.Filled.RestaurantMenu,
        AppRoute.Drinks to Icons.Outlined.LocalBar
    )
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerContent = {
            AppModalDrawerSheet()
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            topBar = {
                if (bottomNavRoutes.any { it.first.route == currentRoute }
                ) {
                    CenterAlignedTopAppBar(title = {
                        Text(text = "Restorante Panucci")
                    }, navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    }, actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Outlined.AccountCircle, null)
                        }
                    })
                }
            },
            bottomBar = {
                if (bottomNavRoutes.find {
                        it.first.route == currentRoute
                    } != null) {
                    NavigationBar {
                        bottomNavRoutes.forEach { item ->
                            val label = item.first.route
                            val icon = item.second
                            NavigationBarItem(
                                icon = { Icon(icon, contentDescription = label) },
                                label = { Text(label) },
                                selected = currentRoute == label,
                                onClick = {
                                    onRouteChange(label)
                                }
                            )
                        }
                    }
                }
            },
            floatingActionButton = {
                if (
                    currentRoute == AppRoute.Drinks.route ||
                    currentRoute == AppRoute.Menu.route
                ) {
                    FloatingActionButton(
                        onClick = onFabClick
                    ) {
                        Icon(
                            Icons.Filled.PointOfSale,
                            contentDescription = null
                        )
                    }
                }
            }
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    PanucciTheme {
        Surface {
            App {}
        }
    }
}