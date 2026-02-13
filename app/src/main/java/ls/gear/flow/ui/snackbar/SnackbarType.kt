package ls.gear.flow.ui.snackbar

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ls.gear.flow.R

enum class SnackbarType {
    DEFAULT, ERROR
}

val SnackbarType.containerColor: Color
    @Composable
    get() = when (this) {
        SnackbarType.DEFAULT -> MaterialTheme.colorScheme.primary
        SnackbarType.ERROR -> MaterialTheme.colorScheme.error
    }

val SnackbarType.contentColor: Color
    @Composable
    get() = when (this) {
        SnackbarType.DEFAULT -> MaterialTheme.colorScheme.onPrimary
        SnackbarType.ERROR -> MaterialTheme.colorScheme.onPrimary
    }

val SnackbarType.drawableResId: Int
    get() = when (this) {
        SnackbarType.DEFAULT -> R.drawable.ic_checked_circle
        SnackbarType.ERROR -> R.drawable.ic_error
    }
