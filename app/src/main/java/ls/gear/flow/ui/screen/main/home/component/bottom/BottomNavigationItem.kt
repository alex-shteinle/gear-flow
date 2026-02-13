package ls.gear.flow.ui.screen.main.home.component.bottom

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ls.gear.flow.R
import ls.gear.flow.navigation.Destination

sealed class BottomNavigationItem(
    val destination: Destination,
    @DrawableRes val iconId: Int,
    @StringRes val titleId: Int
) {
    object PropertyCard : BottomNavigationItem(
        destination = Destination.PropertyCard,
        iconId = R.drawable.ic_property_card,
        titleId = R.string.property_card
    )
    object PersonalSizes : BottomNavigationItem(
        destination = Destination.PersonalSizes,
        iconId = R.drawable.ic_personal_sizes,
        titleId = R.string.personal_sizes
    )
    object Order : BottomNavigationItem(
        destination = Destination.Order,
        iconId = R.drawable.ic_order,
        titleId = R.string.order
    )
    object Settings : BottomNavigationItem(
        destination = Destination.Settings,
        iconId = R.drawable.ic_settings,
        titleId = R.string.settings
    )
}
