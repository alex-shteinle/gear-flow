package ls.gear.flow.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import ls.gear.flow.navigation.Destination
import ls.gear.flow.ui.screen.main.home.groupdetail.ItemGroupDetailScreen
import ls.gear.flow.ui.screen.main.home.order.OrderScreen
import ls.gear.flow.ui.screen.main.home.personalsizes.SizesScreen
import ls.gear.flow.ui.screen.main.home.property.PropertyCardScreen
import ls.gear.flow.ui.screen.main.home.setting.SettingsScreen

fun NavGraphBuilder.homeNavGraph() {
    navigation(
        route = GraphConst.HOME,
        startDestination = Destination.PropertyCard.route
    ) {
        propertyCardScreen()
        personalSizesScreen()
        orderScreen()
        itemGroupDetailScreen()
        settingsScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.personalSizesScreen() {
    composable(
        route = Destination.PersonalSizes.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        SizesScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.propertyCardScreen() {
    composable(
        route = Destination.PropertyCard.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        PropertyCardScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.orderScreen() {
    composable(
        route = Destination.Order.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        OrderScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.itemGroupDetailScreen() {
    composable(
        route = GraphConst.ROUTE_ITEM_DETAILS,
        arguments = listOf(
            navArgument(GraphConst.KEY_GROUP_TYPE) { type = NavType.StringType }
        ),
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        ItemGroupDetailScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.settingsScreen() {
    composable(
        route = Destination.Settings.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        SettingsScreen()
    }
}
