package ls.gear.flow.ui.snackbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ls.gear.flow.log.GearFlowLogger

class SnackbarManagerImpl(private val logger: GearFlowLogger) : SnackbarManager {

    private var snackbarHostState: SnackbarHostState? = null
        get(): SnackbarHostState? = field.also {
            it ?: logger.logError(this, message = "snackbarHostState is null")
        }

    private var snackbarScope: CoroutineScope? = null
        get(): CoroutineScope? = field.also {
            it ?: logger.logError(this, message = "snackbarScope is null")
        }

    private var job: Job? = null

    override fun setup(hostState: SnackbarHostState, scope: CoroutineScope) {
        snackbarHostState = hostState
        snackbarScope = scope
    }

    override fun clear() {
        snackbarHostState = null
        snackbarScope = null
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun showSnackbar(snackbarMessage: SnackbarMessage) {
        cancelIfActive()
        job = snackbarScope?.launch {
            snackbarHostState?.showSnackbar(
                GearFlowSnackbarVisuals(
                    message = snackbarMessage.message,
                    duration = snackbarMessage.duration,
                    type = snackbarMessage.type,
                    drawableResId = snackbarMessage.type.drawableResId
                )
            )
        }
    }

    private fun cancelIfActive() {
        if (job?.isActive == true) {
            job?.cancel()
        }
    }
}
