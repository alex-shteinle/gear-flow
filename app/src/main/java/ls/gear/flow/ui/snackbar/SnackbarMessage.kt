package ls.gear.flow.ui.snackbar

import androidx.compose.material3.SnackbarDuration

data class SnackbarMessage(
    val message: String,
    val type: SnackbarType = SnackbarType.DEFAULT,
    val duration: SnackbarDuration = SnackbarDuration.Short
)
