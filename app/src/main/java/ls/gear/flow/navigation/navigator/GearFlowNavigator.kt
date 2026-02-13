package ls.gear.flow.navigation.navigator

import androidx.navigation.NavHostController
import ls.gear.flow.navigation.Destination
import ls.gear.flow.navigation.NavOptionType

interface GearFlowNavigator {
    val bottomNavItems: List<Destination>

    fun setupNavController(navHostController: NavHostController)
    fun clearNavController()
    fun navigate(destination: Destination, navOptionType: NavOptionType = NavOptionType.Default)
    fun back()
}
