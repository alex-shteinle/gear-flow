package ls.gear.flow.ui.screen.main.splash.state

sealed interface SplashAction {
    object CheckIfUserPresent : SplashAction
    object CheckIfPinCodeExists : SplashAction
}
