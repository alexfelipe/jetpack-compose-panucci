package br.com.alura.panucci

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.alura.panucci.navigation.AppDestination
import br.com.alura.panucci.navigation.bottomAppBarItems
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.components.BottomAppBarItem
import br.com.alura.panucci.ui.components.PanucciBottomAppBar
import br.com.alura.panucci.ui.screens.*
import br.com.alura.panucci.ui.theme.PanucciTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val currentBackStack by navController.currentBackStackEntryAsState()
            LaunchedEffect(Unit) {
                navController.addOnDestinationChangedListener { _, _, _ ->
                    val backStackDestinations = navController.backQueue.map { it.destination.route }
                    Log.i(
                        "MainActivity", "onCreate: back stack destinations: $backStackDestinations"
                    )
                }
            }
            val currentDestination = currentBackStack?.destination
            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedItem by remember(currentDestination) {
                        val item = currentDestination?.let { destination ->
                            bottomAppBarItems.find { destination.route == it.destination.route }
                        } ?: bottomAppBarItems.first()
                        mutableStateOf(item)
                    }
                    PanucciApp(
                        currentDestination = currentDestination?.route,
                        bottomAppBarItemSelected = selectedItem,
                        onBottomAppBarItemSelectedChange = {
                            selectedItem = it
                            val startDestination = navController.graph.findStartDestination().id
                            navController.navigate(it.destination.route) {
                                popUpTo(startDestination) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        onFabClick = {
                            navController.navigate(AppDestination.Checkout.route)
                        }) {
                        NavHost(
                            navController = navController,
                            startDestination = AppDestination.Highlight.route
                        ) {
                            composable(AppDestination.Highlight.route) {
                                HighlightsListScreen(
                                    products = sampleProducts,
                                    onNavigateProductDetails = {
                                        navController.navigate(AppDestination.ProductDetails.route)
                                    }
                                ) {
                                    navController.navigate(AppDestination.Checkout.route)
                                }
                            }
                            composable(AppDestination.Menu.route) {
                                MenuListScreen(
                                    products = sampleProducts,
                                    onNavigateToProductDetails = {
                                        navController.navigate(AppDestination.ProductDetails.route)
                                    })
                            }
                            composable(AppDestination.Drinks.route) {
                                DrinksListScreen(
                                    products = sampleProducts,
                                    onNavigateProductDetails = {
                                        navController.navigate(AppDestination.ProductDetails.route)
                                    }
                                )
                            }
                            composable(AppDestination.ProductDetails.route) {
                                ProductDetailsScreen(product = sampleProducts.first())
                            }
                            composable(AppDestination.Checkout.route) {
                                CheckoutScreen(products = sampleProducts)
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
fun PanucciApp(
    currentDestination: String?,
    bottomAppBarItemSelected: BottomAppBarItem = bottomAppBarItems.first(),
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val isShowFab = when (currentDestination) {
        AppDestination.Menu.route,
        AppDestination.Drinks.route -> true
        else -> false
    }
    val containsInBottomAppBarItems = bottomAppBarItems.find {
        it.destination.route == currentDestination
    } != null
    Scaffold(
        topBar = {
            if (containsInBottomAppBarItems) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Restorante Panucci")
                    },
                )
            }
        },
        bottomBar = {
            if (containsInBottomAppBarItems) {
                PanucciBottomAppBar(
                    item = bottomAppBarItemSelected,
                    items = bottomAppBarItems,
                    onItemChange = onBottomAppBarItemSelectedChange,
                )
            }
        },
        floatingActionButton = {
            if (isShowFab) {
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

@Preview
@Composable
private fun PanucciAppPreview() {
    PanucciTheme {
        Surface {
            PanucciApp(currentDestination = null) {}
        }
    }
}