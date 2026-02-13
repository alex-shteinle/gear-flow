package ls.gear.flow.ui.screen.main.home.personalsizes.state

import androidx.annotation.StringRes
import ls.gear.flow.domain.model.TypicalSize

data class UiSize(
    @StringRes val labelRes: Int,
    val value: String,
    val typicalSize: TypicalSize,
    @StringRes val measureUnitRes: Int? = null,
)
