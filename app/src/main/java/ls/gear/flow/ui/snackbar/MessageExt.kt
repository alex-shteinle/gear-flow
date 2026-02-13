package ls.gear.flow.ui.snackbar

import android.content.Context
import ls.gear.flow.ui.screen.base.Message

fun Message.toSnackbarMessage(context: Context) = SnackbarMessage(
    message = context.getString(messageResId),
    type = when (this) {
        is Message.Success -> SnackbarType.DEFAULT
        is Message.Error -> SnackbarType.ERROR
    }
)
