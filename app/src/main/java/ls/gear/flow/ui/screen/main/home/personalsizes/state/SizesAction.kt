package ls.gear.flow.ui.screen.main.home.personalsizes.state

import ls.gear.flow.domain.model.TypicalSize

sealed class SizesAction {

    object SaveSizes : SizesAction()
    object ClearMessage : SizesAction()
    object ClearError : SizesAction()

    sealed class Update : SizesAction() {

        abstract val value: String

        data class Height(override val value: String) : Update()
        data class Sleeve(override val value: String) : Update()
        data class Chest(override val value: String) : Update()
        data class Waist(override val value: String) : Update()
        data class Head(override val value: String) : Update()
        data class Neck(override val value: String) : Update()
        data class Shoe(override val value: String) : Update()
        data class Insole(override val value: String) : Update()
        data class Uniform(override val value: String) : Update()
    }

    data class ShowSizePicker(val typicalSize: TypicalSize) : SizesAction()
    object HideSizePicker : SizesAction()
}
