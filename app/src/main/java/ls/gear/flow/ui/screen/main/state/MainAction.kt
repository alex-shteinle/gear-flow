package ls.gear.flow.ui.screen.main.state

sealed class MainAction {
    object ClearMessage : MainAction()
    object HandleOnStart : MainAction()
    object HandleOnStop : MainAction()
    object HandleOnDestroy : MainAction()
}
