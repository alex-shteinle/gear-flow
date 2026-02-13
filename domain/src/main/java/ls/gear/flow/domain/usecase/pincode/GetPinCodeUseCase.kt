package ls.gear.flow.domain.usecase.pincode

fun interface GetPinCodeUseCase : suspend () -> Result<String>
