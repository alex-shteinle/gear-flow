package ls.gear.flow.domain.usecase.biometric

fun interface SaveUseBiometricUseCase : suspend (Boolean) -> Result<Unit>
