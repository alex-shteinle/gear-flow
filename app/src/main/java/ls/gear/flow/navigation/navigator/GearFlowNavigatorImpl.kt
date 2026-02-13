package ls.gear.flow.navigation.navigator

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import ls.gear.flow.log.GearFlowLogger
import ls.gear.flow.navigation.Destination
import ls.gear.flow.navigation.DestinationWithArgs
import ls.gear.flow.navigation.NavOptionType

/**
 * Implementation of [GearFlowNavigator] and wrapper for [NavHostController].
 * */
class GearFlowNavigatorImpl(private val logger: GearFlowLogger) : GearFlowNavigator {

    override val bottomNavItems: List<Destination> = listOf(
        Destination.PropertyCard,
        Destination.Order,
        Destination.PersonalSizes,
        Destination.Settings
    )

    private var navController: NavHostController? = null
        get(): NavHostController? = field.also {
            it ?: logger.logError(this, message = "navController is null")
        }

    private val defaultNavOptions = NavOptions.Builder().build()

    private val clearStackOptions = NavOptions.Builder()
        .setPopUpTo(destinationId = 0, inclusive = true)
        .build()

    private val bottomNavOptions = NavOptions.Builder()
        .setPopUpTo(
            route = bottomNavItems.first().route,
            inclusive = false,
            saveState = true
        )
        .setLaunchSingleTop(true)
        .setRestoreState(true)
        .build()

    override fun setupNavController(navHostController: NavHostController) {
        navController = navHostController
    }

    override fun clearNavController() {
        navController = null
    }

    override fun navigate(destination: Destination, navOptionType: NavOptionType) {
        when (navOptionType) {
            NavOptionType.BottomItem -> navigate(destination, bottomNavOptions)
            NavOptionType.ClearStack -> navigate(destination, clearStackOptions)
            NavOptionType.Default -> navigate(destination, defaultNavOptions)
        }
    }

    override fun back() {
        navController?.popBackStack()
    }

    private fun navigate(destination: Destination, navOptions: NavOptions) {
        navController?.navigate(
            route = when (destination) {
                is DestinationWithArgs -> destination.routeWithArgs
                else -> destination.route
            },
            navOptions = navOptions
        )
    }
}
