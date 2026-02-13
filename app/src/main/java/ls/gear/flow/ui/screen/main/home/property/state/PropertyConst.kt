package ls.gear.flow.ui.screen.main.home.property.state

import androidx.annotation.DrawableRes
import ls.gear.flow.R

object PropertyConst {

    object Names {
        const val PANTS = "Білизна"
        const val SHOES = "Взуття"
        const val HATS = "Головні убори"
        const val PROTECTION = "Засоби індивідуального захисту"
        const val UNIFORM = "Обмундирування"
        const val EQUIPMENT = "Спорядження"
    }

    object IconRes {
        @DrawableRes val PANTS = R.drawable.ic_pants

        @DrawableRes val SHOES = R.drawable.ic_shoes

        @DrawableRes val HATS = R.drawable.ic_hat

        @DrawableRes val PROTECTION = R.drawable.ic_protection

        @DrawableRes val UNIFORM = R.drawable.ic_uniform

        @DrawableRes val EQUIPMENT = R.drawable.ic_equipment

        @DrawableRes val ALL_PROPERTY = R.drawable.ic_all_property
    }
}
