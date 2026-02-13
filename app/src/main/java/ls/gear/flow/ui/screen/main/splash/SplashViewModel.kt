package ls.gear.flow.ui.screen.main.splash

import ls.gear.flow.domain.usecase.pincode.CheckPinCodeExistsUseCase
import ls.gear.flow.domain.usecase.user.GetUserUseCase
import ls.gear.flow.ui.screen.base.BaseViewModel
import ls.gear.flow.ui.screen.main.splash.state.SplashAction
import ls.gear.flow.ui.screen.main.splash.state.SplashState

class SplashViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val checkPinCodeExistsUseCase: CheckPinCodeExistsUseCase,
) : BaseViewModel<SplashAction, SplashState>(initialValue = SplashState.Initial) {

    override suspend fun reduce(action: SplashAction): SplashState = when (action) {
        SplashAction.CheckIfUserPresent -> checkIfUserPresent()
        SplashAction.CheckIfPinCodeExists -> checkIfPinCodeExists()
    }

    private suspend fun checkIfUserPresent(): SplashState = getUserUseCase().fold(
        onSuccess = { SplashState.UserIsPresent },
        onFailure = { SplashState.NoUser }
    )

    private suspend fun checkIfPinCodeExists(): SplashState {
        return if (checkPinCodeExistsUseCase()) {
            SplashState.PinCodeIsPresent
        } else {
            SplashState.NoPinCode
        }
    }
}
