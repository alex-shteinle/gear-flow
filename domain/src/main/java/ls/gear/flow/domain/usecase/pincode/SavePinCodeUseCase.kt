package ls.gear.flow.domain.usecase.pincode

fun interface SavePinCodeUseCase : suspend (String) -> Result<Unit>
