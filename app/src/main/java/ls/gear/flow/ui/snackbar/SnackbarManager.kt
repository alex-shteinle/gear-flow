package ls.gear.flow.ui.snackbar

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope

interface SnackbarManager {
    fun setup(hostState: SnackbarHostState, scope: CoroutineScope)
    fun clear()
    fun showSnackbar(snackbarMessage: SnackbarMessage)
}
