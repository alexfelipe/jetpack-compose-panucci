package br.com.alexf.panucci

import android.os.Bundle
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alexf.panucci.routes.AppRoute
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
            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App { route ->
                        when (route) {
                            AppRoute.HighlightsList -> {
                                HighlightsListScreen()
                            }

                            AppRoute.Menu -> {
                                MenuListScreen()
                            }

                            AppRoute.Drinks -> {
                                DrinksListScreen()
                            }

                            AppRoute.Checkout -> {
                                CheckoutScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(content: @Composable (AppRoute) -> Unit) {
    val routes = listOf(
        AppRoute.HighlightsList,
        AppRoute.Menu,
        AppRoute.Drinks,
        AppRoute.Checkout
    )
    var selectedRoute: AppRoute by remember {
        mutableStateOf(AppRoute.HighlightsList)
    }
    val bottomNavRoutes = listOf(
        AppRoute.HighlightsList to Icons.Filled.AutoAwesome,
        AppRoute.Menu to Icons.Filled.RestaurantMenu,
        AppRoute.Drinks to Icons.Outlined.LocalBar
    )
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                bottomNavRoutes.forEach { item ->
                    val route = item.first
                    val name = route.route
                    val icon = item.second
                    NavigationDrawerItem(
                        icon = { Icon(icon, contentDescription = null) },
                        label = { Text(name) },
                        selected = route == selectedRoute,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedRoute = route
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            topBar = {
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
            },
            bottomBar = {
                if (bottomNavRoutes.find {
                        it.first == selectedRoute
                    } != null)
                    NavigationBar {
                        bottomNavRoutes.forEach { item ->
                            val route = item.first
                            val label = route.route
                            val icon = item.second
                            NavigationBarItem(
                                icon = { Icon(icon, contentDescription = label) },
                                label = { Text(label) },
                                selected = selectedRoute == route,
                                onClick = { selectedRoute = route }
                            )
                        }
                    }
            },
            floatingActionButton = {
                if (selectedRoute == AppRoute.Drinks || selectedRoute == AppRoute.Menu) {
                    FloatingActionButton(
                        onClick = { selectedRoute = AppRoute.Checkout }) {
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
                content(selectedRoute)
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