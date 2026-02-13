package ls.gear.flow.ui.screen.main.home.personalsizes.select.state

sealed class SizePickerAction {
    data class Select(val selectedSize: String) : SizePickerAction()
    object Clear : SizePickerAction()
}
