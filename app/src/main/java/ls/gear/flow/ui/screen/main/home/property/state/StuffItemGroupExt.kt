package ls.gear.flow.ui.screen.main.home.property.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ls.gear.flow.R
import ls.gear.flow.domain.model.StuffItemGroup
import ls.gear.flow.domain.model.StuffItemGroupType

val StuffItemGroup.generalQuantity get() = items.sumOf { it.quantity }

val StuffItemGroupType.nameRes
    @StringRes
    get() = when (this) {
        StuffItemGroupType.UNDERWEAR -> R.string.underwear
        StuffItemGroupType.FOOTWEAR -> R.string.shoes
        StuffItemGroupType.HEADWEAR -> R.string.headwear
        StuffItemGroupType.PROTECTION -> R.string.protection
        StuffItemGroupType.UNIFORM -> R.string.uniform
        StuffItemGroupType.EQUIPMENT -> R.string.equipment
        StuffItemGroupType.ALL_PROPERTY -> R.string.all_property
    }

val StuffItemGroupType.iconRes
    @DrawableRes
    get() = when (this) {
        StuffItemGroupType.UNDERWEAR -> R.drawable.ic_pants
        StuffItemGroupType.FOOTWEAR -> R.drawable.ic_shoes
        StuffItemGroupType.HEADWEAR -> R.drawable.ic_hat
        StuffItemGroupType.PROTECTION -> R.drawable.ic_protection
        StuffItemGroupType.UNIFORM -> R.drawable.ic_uniform
        StuffItemGroupType.EQUIPMENT -> R.drawable.ic_equipment
        StuffItemGroupType.ALL_PROPERTY -> R.drawable.ic_all_property
    }
