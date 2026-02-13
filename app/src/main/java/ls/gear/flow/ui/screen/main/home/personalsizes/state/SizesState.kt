package ls.gear.flow.ui.screen.main.home.personalsizes.state

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.model.TypicalSize
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.base.UiState

data class SizesState(
    val height: String = "",
    val sleeve: String = "",
    val chest: String = "",
    val waist: String = "",
    val head: String = "",
    val neck: String = "",
    val shoe: String = "",
    val insole: String = "",
    val uniform: String = "",
    val typicalSizeToShow: TypicalSize? = null,
    val changed: Boolean = false,
    override val error: GearFlowError? = null,
    override val message: Message? = null
) : UiState
