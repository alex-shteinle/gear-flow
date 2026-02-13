package ls.gear.flow.ui.screen.main.home.component.bottom

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ls.gear.flow.navigation.NavOptionType
import ls.gear.flow.navigation.navigator.GearFlowNavigator

@Composable
fun HomeBottomNavigation(
    navigator: GearFlowNavigator,
    navController: NavHostController
) {
    val items = navigator.bottomNavItems.mapNotNull { it.toBottomNavItem() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = items.any { it.destination.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar {
            items.forEach { screen ->
                val selected = currentDestination?.hierarchy
                    ?.any { it.route == screen.destination.route } == true
                NavigationBarItem(
                    icon = { BottomIcon(iconResId = screen.iconId) },
                    label = { BottomLabel(stringResId = screen.titleId, selected = selected) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    selected = selected,
                    onClick = {
                        navigator.navigate(screen.destination, NavOptionType.BottomItem)
                    }
                )
            }
        }
    }
}
