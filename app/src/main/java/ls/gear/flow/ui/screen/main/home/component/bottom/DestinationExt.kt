package ls.gear.flow.ui.screen.main.home.component.bottom

import ls.gear.flow.navigation.Destination

fun Destination.toBottomNavItem() = when (this) {
    Destination.PropertyCard -> BottomNavigationItem.PropertyCard
    Destination.Order -> BottomNavigationItem.Order
    Destination.PersonalSizes -> BottomNavigationItem.PersonalSizes
    Destination.Settings -> BottomNavigationItem.Settings
    else -> null
}
