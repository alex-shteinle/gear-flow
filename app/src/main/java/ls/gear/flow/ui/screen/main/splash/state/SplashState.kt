package ls.gear.flow.ui.screen.main.splash.state

sealed interface SplashState {
    object Initial : SplashState
    object NoUser : SplashState
    object UserIsPresent : SplashState
    object PinCodeIsPresent : SplashState
    object NoPinCode : SplashState
}
