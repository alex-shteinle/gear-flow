package ls.gear.flow.ui.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.ui.graphics.Color

data class GearFlowSnackbarVisuals(
    override val message: String,
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    override val withDismissAction: Boolean = false,
    val type: SnackbarType = SnackbarType.DEFAULT,
    @DrawableRes val drawableResId: Int? = null
) : SnackbarVisuals
