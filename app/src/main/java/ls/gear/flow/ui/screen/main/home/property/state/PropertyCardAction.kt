package ls.gear.flow.ui.screen.main.home.property.state

sealed class PropertyCardAction {
    object Reload : PropertyCardAction()
    object ClearMessage : PropertyCardAction()
}
