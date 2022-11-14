package br.com.alexf.panucci

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.alexf.panucci.navigation.navigateToCheckoutScreen
import br.com.alexf.panucci.ui.components.PanucciBottomAppBar
import br.com.alexf.panucci.ui.components.PanucciTopAppBar
import br.com.alexf.panucci.ui.theme.PanucciTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val destinations = navController.backQueue.map {
                "${it.destination.route} "
            }
            Log.i("MainActivity", "onCreate: $destinations")
            val currentDestination = navBackStackEntry?.destination
            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PanucciApp(
                        currentDestination,
                        onItemSelectedChange = { route ->
                            navController.navigateSingleTopTo(route)
                        },
                        onFabClick = {
                            navController.navigateToCheckoutScreen()
                        }) {
                        AppNavHost(
                            navController = navController,
                            startDestination = AppRoutes.Login.route
                        )
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanucciApp(
    currentDestination: NavDestination? = null,
    onItemSelectedChange: (String) -> Unit = {},
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val currentRoute = currentDestination?.route ?: AppRoutes.HighlightsList.route
    val isShowScaffoldBars = panucciBottomAppBarScreens.any {
        it.first.route == currentRoute
    }
    Scaffold(
        topBar = {
            if (isShowScaffoldBars
            ) {
                PanucciTopAppBar(onProfileClick = {
                })
            }
        },
        bottomBar = {
            if (isShowScaffoldBars) {
                PanucciBottomAppBar(
                    currentRoute = currentRoute,
                    onItemChange = onItemSelectedChange,
                    items = panucciBottomAppBarScreens
                )
            }
        },
        floatingActionButton = {
            if (
                panucciCheckoutFabScreens.any {
                    it.route == currentRoute
                }
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

@Preview
@Composable
private fun PanucciAppPreview() {
    PanucciTheme {
        Surface {
            PanucciApp {}
        }
    }
}