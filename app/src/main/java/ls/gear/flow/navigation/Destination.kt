package ls.gear.flow.navigation

import ls.gear.flow.domain.model.StuffItemGroupType
import ls.gear.flow.navigation.graph.GraphConst

/**
 * Class to define list of available routes in app
 */
sealed class Destination(val route: String) {
    // region Auth destinations
    object Splash : Destination("splash")
    object PinCodeSetup : Destination("pin_setup")
    object PinCodeLogin : Destination("pin_login")
    object NoUser : Destination("no_user")
    object HomeGraph : Destination(GraphConst.HOME)
    // end region Auth destinations

    // region Home destinations
    object PropertyCard : Destination("property_card")
    object PersonalSizes : Destination("personal_sizes")
    object Order : Destination("order")
    object Settings : Destination("settings")

    data class ItemGroupDetails(
        val groupType: StuffItemGroupType
    ) : Destination(GraphConst.ROUTE_ITEM_DETAILS), DestinationWithArgs {

        override val routeWithArgs: String get() = "item_group_details/${groupType.name}"
    }
    // end region Home destinations
}
