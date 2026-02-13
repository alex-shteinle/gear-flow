package ls.gear.flow.ui.screen.main.state

import ls.gear.flow.ui.screen.base.Message

const val MAX_BACKGROUND_TIME_IN_MILLISECONDS = 5000L
const val INITIAL_BACKGROUND_TIME = 0L

data class MainState(
    val message: Message? = null,
    val startBackgroundTime: Long = INITIAL_BACKGROUND_TIME,
    val pinCodeExists: Boolean = false
)
