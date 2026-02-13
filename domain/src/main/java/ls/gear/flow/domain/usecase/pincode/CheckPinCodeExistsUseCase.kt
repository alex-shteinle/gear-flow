package ls.gear.flow.domain.usecase.pincode

class CheckPinCodeExistsUseCase(private val getPinCodeUseCase: GetPinCodeUseCase) {

    suspend operator fun invoke(): Boolean {
        return !getPinCodeUseCase().getOrNull().isNullOrEmpty()
    }
}
